package com.ircserv;

import com.ircserv.impl.MenuImpl;
import com.ircserv.metier.Server;

public class ServCraft implements Runnable{
    private Server nbServ;
    private MenuImpl obj;

    public ServCraft(Server nbServ, MenuImpl obj) {
        this.nbServ = nbServ;
        this.obj = obj;
    }

    @Override
    public void run() {
        obj.createNewServer(nbServ.getId());
    }
}
