package com.ircserv;

import com.ircserv.contstante.Constante;
import com.ircserv.inter.ServerInterface;
import com.ircserv.metier.Message;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class testServer {
    public static void main(String[] args){
        int port = Constante.PORT;
        ServerInterface obj;
        try {
            obj = (ServerInterface) Naming.lookup("//"+ Constante.IP+":" + port + "/serv0");
            ArrayList<Message> messages = obj.getMessages();
            System.out.println(messages);
            int x = obj.getMessages().size()+1;



        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }
}
