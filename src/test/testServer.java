package test;

import inter.MenuInterface;
import inter.ServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class testServer {
    public static void main(String[] args){
        int port = 8000;
        ServerInterface obj;
        try {
            obj = (ServerInterface) Naming.lookup("rmi://localhost:" + port + "/serv0");
            System.out.println(obj.getMessages());
            int x = obj.getMessages().size()+1;

            obj.send("ce mesage est le numéro " + x);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
