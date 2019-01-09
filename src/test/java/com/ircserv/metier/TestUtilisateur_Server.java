package com.ircserv.metier;


import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestUtilisateur_Server implements Serializable {
    @Test
    public void contructor_casNull(){
        UtilisateurServer utilisateurServer= new UtilisateurServer();
        assertNull(utilisateurServer.getServer());
        assertNull(utilisateurServer.getUser());
        assertEquals(utilisateurServer.getId(),-1);
    }

    @Test
    public void getId(){
        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setId(2);
        assertEquals(utilisateurServer.getId(), 2);
    }

    /**___Utilisateur___**/
    @Test
    public void getUtilisateur_casNull(){
        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setUser(null);
        assertNull(utilisateurServer.getUser());
    }

    @Test
    public void getUtilisateur_casVide(){
        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setUser(new Utilisateur());
        assertEquals(utilisateurServer.getUser(), new Utilisateur());
    }

    @Test
    public void getUtilisateur_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("dd");

        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setUser(utilisateur);
        assertEquals(utilisateurServer.getUser(), utilisateur);
    }

    /**___Server___**/
    @Test
    public void getServer_casNull(){
        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setServer(null);
        assertNull(utilisateurServer.getServer());
    }

    @Test
    public void getServer_casVide(){
        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setServer(new Server());
        assertEquals(utilisateurServer.getServer(), new Server());
    }

    @Test
    public void getServer_casDonnee(){
        Server server = new Server();
        server.setName("dd");

        UtilisateurServer utilisateurServer= new UtilisateurServer();
        utilisateurServer.setServer(server);
        assertEquals(utilisateurServer.getServer(), server);
    }
}
