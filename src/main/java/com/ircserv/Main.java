package com.ircserv;

import com.ircserv.contstante.Constante;
import com.ircserv.impl.MenuImpl;
import com.ircserv.metier.Server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("java.rmi.server.hostname",args[0]);
        Constante.IP = args[0];
        System.out.println("Server Starting...");
        try {
            int port = Constante.PORT;
            MenuImpl obj = new MenuImpl(port);
            LocateRegistry.createRegistry(port);
            Naming.rebind("//"+"localhost"+":" + port + "/menu", obj);
            System.out.println("server ready");

            List<Server> servers = obj.getAllServ();

            for (Server server : servers){
                new Thread(new ServCraft(server, obj)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
