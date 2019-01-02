package com.ircserv.inter;

import com.ircserv.metier.Server;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface MenuInterface extends Remote {
    int createNewServer() throws RemoteException;
    void deleteServer(int nbServ) throws RemoteException;
    ArrayList<Server> findServerByUser(int userId) throws RemoteException;
    int connect(String user, String psw) throws RemoteException;
    String getUserName(int id) throws RemoteException;
    int createUser(String name, String psw) throws RemoteException;
}
