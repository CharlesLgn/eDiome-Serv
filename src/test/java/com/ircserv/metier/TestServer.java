package com.ircserv.metier;


import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestServer implements Serializable {
    @Test
    public void contructor_casNull(){
        Server server = new Server();
        assertNull(server.getCreateur());
        assertNull(server.getName());
        assertEquals(server.getId(),-1);
    }

    @Test
    public void contructor_casAll(){
        Server server = new Server("cc", new Utilisateur());
        assertEquals(server.getName(), "cc");
        assertEquals(server.getCreateur(), new Utilisateur());
        assertEquals(server.getId(), -1);
    }

    @Test
    public void getId(){
        Server server = new Server();
        server.setId(2);
        assertEquals(server.getId(), 2);
    }

    /**___Name___**/
    @Test
    public void getName_casNull(){
        Server server = new Server();
        server.setName(null);
        assertNull(server.getName());
    }

    @Test
    public void getName_casVide(){
        Server server = new Server();
        server.setName("");
        assertEquals(server.getName(), "");
    }

    @Test
    public void getName_casDonnee(){
        Server server = new Server();
        server.setName("ddd");
        assertEquals(server.getName(), "ddd");
    }


    /**___Createur___**/
    @Test
    public void getCreateur_casNull(){
        Server server = new Server();
        server.setCreateur(null);
        assertNull(server.getCreateur());
    }

    @Test
    public void getCreateur_casVide(){
        Server server = new Server();
        server.setCreateur(new Utilisateur());
        assertEquals(server.getCreateur(),  new Utilisateur());
    }

    @Test
    public void getCreateur_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("ddd");

        Server server = new Server();
        server.setCreateur(utilisateur);
        assertEquals(server.getCreateur(), utilisateur);
    }
}
