package test;

import inter.MenuInterface;
import inter.ServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class testMenu {

    public static void main(String[] args){
        int port = 8000;
        MenuInterface obj;
        try {
            obj = (MenuInterface) Naming.lookup("rmi://localhost:" + port + "/menu");
            int x = obj.createNewServer();
            System.out.println(x);
            if (x == -1){
                System.out.println("désolé, votre server n'a pas pu ce créer");
            } else {
               System.out.println("numéro du server : " + x);
            }

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
