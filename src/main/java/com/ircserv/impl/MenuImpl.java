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

    private int numServ;

    public MenuImpl(int port) throws RemoteException {
        super(port);
        this.numServ = 0;
    }

    @Override
    public int createNewServer() {
        System.err.println("preparation creation serv");
        return createNewServer(numServ++);
    }

    @Override
    public Server createNewServer(String name, int userId) {
        try {
            UtilisateurManager um = new UtilisateurManager();
            ServerManager sm = new ServerManager();
            um.setup();
            sm.setup();

            Utilisateur user = um.readUser(userId);
            Server serv = new Server(name, user);
            Server noServ = sm.create(serv);
            createNewServer(noServ.getId());
            return noServ;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public int createNewServer(int numServ) {
        try {
            System.out.println("creation of the server "+numServ);
            System.setProperty("java.rmi.server.hostname","home.rscharff.fr");
            String ip = "localhost";
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);
            LocateRegistry.getRegistry("home.rscharff.fr");
            Naming.rebind("//" + ip + ":" + port + "/serv" + numServ, new ServerImpl(port, numServ));

            System.err.println("server "+numServ+" created !!");
            return numServ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Server> getAllServ(){
        ServerManager serverManager = new ServerManager();
        serverManager.setup();
        return serverManager.getAllServ();
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
        try {
            UtilisateurServerManager manager = new UtilisateurServerManager();
            UtilisateurManager uManager = new UtilisateurManager();
            manager.setup();
            uManager.setup();

            Utilisateur user = uManager.readUser(userId);

            return manager.getServerByUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return XMLDataFinder.getServByUser(userId);
        }
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
        return manager.read(id);

    }

    @Override
    public int createUser(Utilisateur user) {
        try {
            UtilisateurManager manager = new UtilisateurManager();
            manager.setup();
            manager.create(user);
            ServerManager sm = new ServerManager();
            sm.setup();

            UtilisateurServerManager usm = new UtilisateurServerManager();
            usm.setup();
            UtilisateurServer us = new UtilisateurServer();
            us.setUser(user);
            us.setServer(sm.readServer(1));
            usm.create(us);

            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
