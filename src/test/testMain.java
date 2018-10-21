package test;

import inter.MenuInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class testMain {

    public static void main(String[] args){
        int port = 8000;
        MenuInterface obj;
        try {
            obj = (MenuInterface) Naming.lookup("rmi://localhost:" + port + "/menu");
            obj.createNewServer();
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
