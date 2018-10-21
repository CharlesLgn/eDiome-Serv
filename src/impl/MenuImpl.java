package impl;

import inter.MenuInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class MenuImpl extends UnicastRemoteObject implements MenuInterface {

    private int numServ;

    public MenuImpl() throws RemoteException {
        super();
        this.numServ = 0;
    }

    @Override
    public void createNewServer() throws RemoteException {
        try {
            int port = 8000;
            LocateRegistry.createRegistry(port);

            Naming.rebind("rmi://localhost:"+port+"/serv"+numServ, new ServerImpl());
            numServ++;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("echec : " + e);
        }
    }

    @Override
    public void deleteServer(int nbServ) {
        try {
            int port = 8000;
            LocateRegistry.createRegistry(port);

            Naming.unbind("rmi://localhost:"+port+"/serv"+nbServ);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("echec : " + e);
        }
    }
}
