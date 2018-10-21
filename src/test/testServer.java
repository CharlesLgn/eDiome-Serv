package test;

import inter.ServerInterface;
import metier.Message;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class testServer {
    public static void main(String[] args){
        int port = 8000;
        ServerInterface obj;
        try {
            obj = (ServerInterface) Naming.lookup("rmi://localhost:" + port + "/serv0");
            ArrayList<Message> messages = obj.getMessages();
            System.out.println(messages);
            int x = obj.getMessages().size()+1;

            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);
            obj.send("moi", "message " + x);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
