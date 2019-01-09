package com.ircserv.metier;


import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestUtilisateur_Server implements Serializable {
    @Test
    public void contructor_casNull(){
        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        assertNull(utilisateurServer.getServer());
        assertNull(utilisateurServer.getUser());
        assertEquals(utilisateurServer.getId(),-1);
    }

    @Test
    public void getId(){
        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setId(2);
        assertEquals(utilisateurServer.getId(), 2);
    }

    /**___Utilisateur___**/
    @Test
    public void getUtilisateur_casNull(){
        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setUser(null);
        assertNull(utilisateurServer.getUser());
    }

    @Test
    public void getUtilisateur_casVide(){
        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setUser(new Utilisateur());
        assertEquals(utilisateurServer.getUser(), new Utilisateur());
    }

    @Test
    public void getUtilisateur_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("dd");

        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setUser(utilisateur);
        assertEquals(utilisateurServer.getUser(), utilisateur);
    }

    /**___Server___**/
    @Test
    public void getServer_casNull(){
        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setServer(null);
        assertNull(utilisateurServer.getServer());
    }

    @Test
    public void getServer_casVide(){
        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setServer(new Server());
        assertEquals(utilisateurServer.getServer(), new Server());
    }

    @Test
    public void getServer_casDonnee(){
        Server server = new Server();
        server.setName("dd");

        Utilisateur_Server utilisateurServer= new Utilisateur_Server();
        utilisateurServer.setServer(server);
        assertEquals(utilisateurServer.getServer(), server);
    }
}
