package com.ircserv;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.MenuInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class testMenu {

    public static void main(String[] args){
        //System.setProperty("java.rmi.server.hostname",Constante.IP);
        int port = Constante.PORT;
        MenuInterface obj;
        try {
            obj = (MenuInterface) Naming.lookup("//"+Constante.IP+":" + port + "/menu");
            for (int i = 0 ; i < 5 ; i++){
                int x = obj.createNewServer();
                System.out.println(x);
                if (x == -1){
                    System.out.println("désolé, votre server n'a pas pu ce créer");
                } else {
                    System.out.println("numéro du server : " + x);
                }
            }

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
