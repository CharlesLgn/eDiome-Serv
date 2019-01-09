package com.ircserv.metier;


import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestUtilisateur_Server implements Serializable {
    @Test
    public void contructor_casNull(){
        utilisateurServer utilisateurServer= new utilisateurServer();
        assertNull(utilisateurServer.getServer());
        assertNull(utilisateurServer.getUser());
        assertEquals(utilisateurServer.getId(),-1);
    }

    @Test
    public void getId(){
        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setId(2);
        assertEquals(utilisateurServer.getId(), 2);
    }

    /**___Utilisateur___**/
    @Test
    public void getUtilisateur_casNull(){
        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setUser(null);
        assertNull(utilisateurServer.getUser());
    }

    @Test
    public void getUtilisateur_casVide(){
        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setUser(new Utilisateur());
        assertEquals(utilisateurServer.getUser(), new Utilisateur());
    }

    @Test
    public void getUtilisateur_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("dd");

        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setUser(utilisateur);
        assertEquals(utilisateurServer.getUser(), utilisateur);
    }

    /**___Server___**/
    @Test
    public void getServer_casNull(){
        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setServer(null);
        assertNull(utilisateurServer.getServer());
    }

    @Test
    public void getServer_casVide(){
        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setServer(new Server());
        assertEquals(utilisateurServer.getServer(), new Server());
    }

    @Test
    public void getServer_casDonnee(){
        Server server = new Server();
        server.setName("dd");

        utilisateurServer utilisateurServer= new utilisateurServer();
        utilisateurServer.setServer(server);
        assertEquals(utilisateurServer.getServer(), server);
    }
}
