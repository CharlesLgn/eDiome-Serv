package com.ircserv.impl;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.MenuInterface;
import com.ircserv.manager.ServerManager;
import com.ircserv.manager.UtilisateurManager;
import com.ircserv.manager.UtilisateurServerManager;
import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import com.ircserv.metier.UtilisateurServer;
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
            um.setup();
            Utilisateur user = um.read(userId);

            Server serv = new Server(name, user);
            //create serv
            ServerManager sm = new ServerManager();
            sm.setup();
            Server noServ = sm.create(serv);

            //create access to serv
            UtilisateurServerManager usm = new UtilisateurServerManager();
            usm.setup();
            UtilisateurServer utilisateurServer = new UtilisateurServer(user, noServ);
            usm.create(utilisateurServer);
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
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);
            ServerManager sm = new ServerManager();
            sm.setup();
            sm.delete(nbServ);
            Naming.unbind("//" + Constante.IP + ":" + port + "/serv" + nbServ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Server> findServerByUser(int userId) {
        UtilisateurManager uManager = new UtilisateurManager();
        uManager.setup();
        Utilisateur user = uManager.read(userId);

        UtilisateurServerManager manager = new UtilisateurServerManager();
        manager.setup();
        return manager.getServerByUser(user);
    }

    @Override
    public int connect(String user, String psw) {
        UtilisateurManager manager = new UtilisateurManager();
        manager.setup();
        return manager.connexionCHeck(user, psw);
    }

    @Override
    public String getUserName(int id) {
        UtilisateurManager manager = new UtilisateurManager();
        manager.setup();
        return manager.read(id).getIdentifiant();

    }

    @Override
    public int createUser(Utilisateur user) {
        try {
            //create user
            UtilisateurManager manager = new UtilisateurManager();
            manager.setup();
            manager.create(user);
            //get the general server
            ServerManager sm = new ServerManager();
            sm.setup();
            Server server = sm.read(1);
            //link the user to the general user
            UtilisateurServerManager usm = new UtilisateurServerManager();
            usm.setup();
            UtilisateurServer utilisateurServer = new UtilisateurServer();
            utilisateurServer.setUser(user);
            utilisateurServer.setServer(server);
            usm.create(utilisateurServer);

            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @param numServ the id of the server
     * @return the id of the server if the server is up, else -1
     */
    public int createNewServer(int numServ) {
        try {
            System.out.println("creation of the server " + numServ);
            System.setProperty("java.rmi.server.hostname", "home.rscharff.fr");
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
        serverManager.setup();
        return serverManager.getAllServ();
    }
}
