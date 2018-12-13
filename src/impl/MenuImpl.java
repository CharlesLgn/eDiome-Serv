package impl;

import contstante.Constante;
import inter.MenuInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

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
}
