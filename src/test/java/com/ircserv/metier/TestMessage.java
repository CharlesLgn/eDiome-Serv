package com.ircserv.metier;

import org.junit.Test;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestMessage implements Serializable {

    @Test
    public void contructor_casNull(){
        Message message = new Message();
        assertNull(message.getContenu());
        assertNull(message.getDate());
        assertNull(message.getPieceJointe());
        assertNull(message.getServer());
        assertNull(message.getUser());
        assertEquals(message.getId(), -1);
    }

    @Test
    public void contructor_casAll(){
        Timestamp time = Timestamp.from(Instant.now());
        Message message = new Message(1, new Utilisateur(), time, "test", new Server(), new PieceJointe());
        assertEquals(message.getContenu(), "test");
        assertEquals(message.getDate(), time);
        assertEquals(message.getPieceJointe(), new PieceJointe());
        assertEquals(message.getServer(), new Server());
        assertEquals(message.getUser(), new Utilisateur());
        assertEquals(message.getId(), 1);
    }

    @Test
    public void getId(){
        Message message = new Message();
        message.setId(2);
        assertEquals(message.getId(), 2);
    }

    /**___Contenu___**/
    @Test
    public void getContenu_casNull(){
        Message message = new Message();
        message.setContenu(null);
        assertNull(message.getContenu());
    }

    @Test
    public void getContenu_casVide(){
        Message message = new Message();
        message.setContenu("");
        assertEquals(message.getContenu(), "");
    }

    @Test
    public void getContenu_casDonnee(){
        Message message = new Message();
        message.setContenu("test");
        assertEquals(message.getContenu(), "test");
    }


    /**___Server___**/
    @Test
    public void getServer_casNull(){
        Message message = new Message();
        message.setServer(null);
        assertNull(message.getServer());
    }

    @Test
    public void getServer_casVide(){
        Message message = new Message();
        message.setServer(new Server());
        assertEquals(message.getServer(), new Server());
    }

    @Test
    public void getServer_casDonnee(){
        Server server = new Server("test", new Utilisateur());

        Message message = new Message();
        message.setServer(server);
        assertEquals(message.getServer(), server);
    }

    /**___Date___**/
    @Test
    public void getDate_casNull(){
        Message message = new Message();
        message.setDate(null);
        assertNull(message.getDate());
    }

    @Test
    public void getDate_casVide(){
        Message message = new Message();
        message.setDate(new Timestamp(10));
        assertEquals(message.getDate(), new Timestamp(10));
    }

    @Test
    public void getDate_casDonnee(){
        Timestamp time = Timestamp.from(Instant.now());
        Message message = new Message();
        message.setDate(time);
        assertEquals(message.getDate(), time);
    }

    /**___User___**/
    @Test
    public void getUser_casNull(){
        Message message = new Message();
        message.setUser(null);
        assertNull(message.getUser());
    }

    @Test
    public void getUser_casVide(){
        Message message = new Message();
        message.setUser(new Utilisateur());
        assertEquals(message.getUser(), new Utilisateur());
    }

    @Test
    public void getUser_casDonnee(){
        Utilisateur user = new Utilisateur();
        user.setIdentifiant("djhkddh");

        Message message = new Message();
        message.setUser(user);
        assertEquals(message.getUser(), user);
    }

    /**___Piece Jointe___**/
    @Test
    public void getPJ_casNull(){
        Message message = new Message();
        message.setPieceJointe(null);
        assertNull(message.getPieceJointe());
    }

    @Test
    public void getPJ_casVide(){
        Message message = new Message();
        message.setPieceJointe(new PieceJointe());
        assertEquals(message.getPieceJointe(), new PieceJointe());
    }

    @Test
    public void getPJ_casDonnee(){
        PieceJointe pj = new PieceJointe();
        pj.setChemin("djhkddh");

        Message message = new Message();
        message.setPieceJointe(pj);
        assertEquals(message.getPieceJointe(), pj);
    }
}
