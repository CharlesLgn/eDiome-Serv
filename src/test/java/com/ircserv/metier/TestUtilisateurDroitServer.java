package com.ircserv.metier;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestUtilisateurDroitServer implements Serializable {
    @Test
    public void contructor_casNull(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        assertNull(utilisateurDroitServer.getDroit());
        assertNull(utilisateurDroitServer.getServeur());
        assertNull(utilisateurDroitServer.getUser());
        assertEquals(utilisateurDroitServer.getId(),-1);
    }

    @Test
    public void getId(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setId(2);
        assertEquals(utilisateurDroitServer.getId(), 2);
    }

    /**___Droit___**/
    @Test
    public void getDroit_casNull(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setDroit(null);
        assertNull(utilisateurDroitServer.getDroit());
    }

    @Test
    public void getDroit_casVide(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setDroit(new Droit());
        assertEquals(utilisateurDroitServer.getDroit(), new Droit());
    }

    @Test
    public void getDroit_casDonnee(){
        Droit droit = new Droit();
        droit.setLibelle("dd");

        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setDroit(droit);
        assertEquals(utilisateurDroitServer.getDroit(), droit);
    }


    /**___Utilisateur___**/
    @Test
    public void getUtilisateur_casNull(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setUser(null);
        assertNull(utilisateurDroitServer.getUser());
    }

    @Test
    public void getUtilisateur_casVide(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setUser(new Utilisateur());
        assertEquals(utilisateurDroitServer.getUser(), new Utilisateur());
    }

    @Test
    public void getUtilisateur_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("dd");

        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setUser(utilisateur);
        assertEquals(utilisateurDroitServer.getUser(), utilisateur);
    }

    /**___Server___**/
    @Test
    public void getServer_casNull(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setServeur(null);
        assertNull(utilisateurDroitServer.getServeur());
    }

    @Test
    public void getServer_casVide(){
        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setServeur(new Server());
        assertEquals(utilisateurDroitServer.getServeur(), new Server());
    }

    @Test
    public void getServer_casDonnee(){
        Server server = new Server();
        server.setName("dd");

        Utilisateur_Droit_Server utilisateurDroitServer = new Utilisateur_Droit_Server();
        utilisateurDroitServer.setServeur(server);
        assertEquals(utilisateurDroitServer.getServeur(), server);
    }
}
