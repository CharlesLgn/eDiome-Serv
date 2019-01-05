package com.ircserv.inter;

import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface MenuInterface extends Remote {
    int createNewServer() throws RemoteException;
    void deleteServer(int nbServ) throws RemoteException;
    List<Server> findServerByUser(int userId) throws RemoteException;
    int connect(String user, String psw) throws RemoteException;
    String getUserName(int id) throws RemoteException;
    int createUser(Utilisateur utilisateur) throws RemoteException;
}
