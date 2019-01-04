package com.ircserv.impl;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.MenuInterface;
import com.ircserv.manager.UtilisateurManager;
import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import com.ircserv.utils.XMLDataFinder;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class MenuImpl extends UnicastRemoteObject implements MenuInterface {

    private int numServ;

    public MenuImpl(int port) throws RemoteException {
        super(port);
        this.numServ = 0;
    }

    @Override
    public int createNewServer() {
        try {
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);

            Naming.rebind("//"+ Constante.IP+":"+port+"/serv"+numServ, new ServerImpl(port, numServ));
            return numServ++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void deleteServer(int nbServ) {
        try {
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);

            Naming.unbind("//"+Constante.IP+":"+port+"/serv"+nbServ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int connect(String user, String psw) {
        return XMLDataFinder.getUserId(user, psw);
    }

    @Override
    public ArrayList<Server> findServerByUser(int userId) {
        return XMLDataFinder.getServByUser(userId);
    }

    @Override
    public String getUserName(int id) {
        return XMLDataFinder.getUserName(id);
    }

    @Override
    public int createUser(Utilisateur user) {

        try {
            UtilisateurManager manager = new UtilisateurManager();
            manager.setup();
            manager.create(user);
            return user.getNoUtilisateur();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
