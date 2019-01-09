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

    private int createNewServer(int numServ) {
        try {
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);

            Naming.rebind("//" + Constante.IP + ":" + port + "/serv" + numServ, new ServerImpl(port, numServ));
            return numServ;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("echec : " + e);
        }
        return -1;
    }

    @Override
    public void deleteServer(int nbServ) {
        try {
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);

            Naming.unbind("//" + Constante.IP + ":" + port + "/serv" + nbServ);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("echec : " + e);
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
