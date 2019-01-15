package com.ircserv.impl;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.MenuInterface;
import com.ircserv.manager.*;
import com.ircserv.metier.*;
import com.ircserv.utils.XMLDataFinder;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MenuImpl extends UnicastRemoteObject implements MenuInterface {

    public MenuImpl(int port) throws RemoteException {
        super(port);
    }

    @Override
    public Server createNewServer(String name, int userId) {
        try {
            //find creator
            UtilisateurManager um = new UtilisateurManager();
            Utilisateur user = um.read(userId);
            Server serv = new Server(name, user);
            //create serv
            ServerManager sm = new ServerManager();
            Server noServ = sm.create(serv);
            //create access to serv
            UtilisateurServerManager usm = new UtilisateurServerManager();
            UtilisateurServer utilisateurServer = new UtilisateurServer(user, noServ);
            usm.create(utilisateurServer);
            //set as admin
            DroitManager dm = new DroitManager();
            Droit droit = dm.read(0); //Admin
            //create right
            UtilisateurDroitServerManager udsm = new UtilisateurDroitServerManager();
            UtilisateurDroitServer usertDroitServer = new UtilisateurDroitServer();
            usertDroitServer.setDroit(droit);
            usertDroitServer.setServeur(serv);
            usertDroitServer.setUser(user);
            udsm.create(usertDroitServer);
            //creat the real server
            createNewServer(noServ.getId());
            return noServ;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteServer(int nbServ) {
        try {
            ServerManager sm = new ServerManager();
            Server server = sm.read(nbServ);
            sm.delete(server);

            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);
            Naming.unbind("//" + Constante.IP + ":" + port + "/serv" + nbServ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Server> findServerByUser(int userId) {
        UtilisateurManager uManager = new UtilisateurManager();
        Utilisateur user = uManager.read(userId);

        UtilisateurServerManager manager = new UtilisateurServerManager();
        return manager.getServerByUser(user);
    }

    @Override
    public int connect(String user, String psw) {
        UtilisateurManager manager = new UtilisateurManager();
        return manager.connexionCHeck(user, psw);
    }

    @Override
    public Utilisateur getUser(int id) {
        UtilisateurManager manager = new UtilisateurManager();
        return manager.read(id);
    }

    @Override
    public int createUser(Utilisateur user) {
        try {
            //create user
            UtilisateurManager manager = new UtilisateurManager();
            user = manager.create(user);
            //get the general server
            ServerManager sm = new ServerManager();
            Server server = sm.read(1);
            //link the user to the general user
            UtilisateurServerManager usm = new UtilisateurServerManager();
            UtilisateurServer utilisateurServer = new UtilisateurServer();
            utilisateurServer.setUser(user);
            utilisateurServer.setServer(server);
            usm.create(utilisateurServer);

            UtilisateurDroitServer utilisateurDroitServer = new UtilisateurDroitServer();
            UtilisateurDroitServerManager udsm = new UtilisateurDroitServerManager();
            DroitManager droitManager = new DroitManager();
            utilisateurDroitServer.setDroit(droitManager.read(4));
            utilisateurDroitServer.setServeur(server);
            utilisateurDroitServer.setUser(user);
            udsm.create(utilisateurDroitServer);

            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Droit> getDroit() {
        DroitManager droitManager = new DroitManager();
        return droitManager.getall();
    }

    /**
     * @param numServ the id of the server
     * @return the id of the server if the server is up, else -1
     */
    public int createNewServer(int numServ) {
        try {
            System.out.println("creation of the server " + numServ);
            System.setProperty("java.rmi.server.hostname", "localhost");
            //System.setProperty("java.rmi.server.hostname", "home.rscharff.fr");
            String ip = "localhost";
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);
            Naming.rebind("//" + ip + ":" + port + "/serv" + numServ, new ServerImpl(port, numServ));

            System.err.println("server " + numServ + " created !!");
            return numServ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @return the list of all server (id, name, creator)
     */
    public List<Server> getAllServ() {
        ServerManager serverManager = new ServerManager();
        return serverManager.getAllServ();
    }
}
