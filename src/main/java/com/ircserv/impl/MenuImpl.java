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
    public int createNewServer() throws RemoteException {
        try {
            int port = Constante.PORT;
            LocateRegistry.getRegistry(port);

            Naming.rebind("//"+ Constante.IP+":"+port+"/serv"+numServ, new ServerImpl(port));
            return numServ++;
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

            Naming.unbind("//"+Constante.IP+":"+port+"/serv"+nbServ);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("echec : " + e);
        }
    }

    @Override
    public ArrayList<Server> findServerByUser(int userId) throws RemoteException {


        return XMLDataFinder.getServByUser(userId);
    }

    @Override
    public int connect(String user, String psw) throws RemoteException {
        UtilisateurManager manager = new UtilisateurManager();
        int res =  manager.connexionCHeck(user, psw);

        return res;
    }

    @Override
    public String getUserName(int id) throws RemoteException {
        UtilisateurManager manager = new UtilisateurManager();
        return manager.read(id);

    }

    @Override
    public int createUser(Utilisateur user) throws RemoteException {

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
