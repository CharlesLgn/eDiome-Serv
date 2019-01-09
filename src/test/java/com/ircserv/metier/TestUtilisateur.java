package com.ircserv.metier;


import org.junit.Test;

import java.io.Serializable;
import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestUtilisateur implements Serializable {
    @Test
    public void contructor_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        assertNull(utilisateur.getIdentifiant());
        assertNull(utilisateur.getRegistrationDate());
        assertNull(utilisateur.getBirthDate());
        assertNull(utilisateur.getMailPro());
        assertNull(utilisateur.getPassword());
        assertNull(utilisateur.getNom());
        assertNull(utilisateur.getPrenom());
        assertNull(utilisateur.getTelephonePro());
        assertEquals(utilisateur.getId(),-1);
    }

    @Test
    public void getId(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(2);
        assertEquals(utilisateur.getId(), 2);
    }

    /**___Name___**/
    @Test
    public void getName_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(null);
        assertNull(utilisateur.getNom());
    }

    @Test
    public void getName_casVide(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("");
        assertEquals(utilisateur.getNom(), "");
    }

    @Test
    public void getName_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("ddd");
        assertEquals(utilisateur.getNom(), "ddd");
    }

    /**___Prenom___**/
    @Test
    public void getPrenom_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom(null);
        assertNull(utilisateur.getPrenom());
    }

    @Test
    public void getPrenom_casVide(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom("");
        assertEquals(utilisateur.getPrenom(), "");
    }

    @Test
    public void getPrenom_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom("ddd");
        assertEquals(utilisateur.getPrenom(), "ddd");
    }

    /**___Identifiant___**/
    @Test
    public void getIdentifiant_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant(null);
        assertNull(utilisateur.getIdentifiant());
    }

    @Test
    public void getIdentifiant_casVide(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("");
        assertEquals(utilisateur.getIdentifiant(), "");
    }

    @Test
    public void getIdentifiant_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdentifiant("ddd");
        assertEquals(utilisateur.getIdentifiant(), "ddd");
    }

    /**___Password___**/
    @Test
    public void getPassword_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPassword(null);
        assertNull(utilisateur.getPassword());
    }

    @Test
    public void getPassword_casVide(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPassword("");
        assertEquals(utilisateur.getPassword(), "");
    }

    @Test
    public void getPassword_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPassword("ddd");
        assertEquals(utilisateur.getPassword(), "ddd");
    }

    /**___MailPro___**/
    @Test
    public void getMailPro_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMailPro(null);
        assertNull(utilisateur.getMailPro());
    }

    @Test
    public void getMailPro_casVide(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMailPro("");
        assertEquals(utilisateur.getMailPro(), "");
    }

    @Test
    public void getMailPro_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setMailPro("ddd");
        assertEquals(utilisateur.getMailPro(), "ddd");
    }

    /**___TelephonePro___**/
    @Test
    public void getTelephonePro_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setTelephonePro(null);
        assertNull(utilisateur.getTelephonePro());
    }

    @Test
    public void getTelephonePro_casVide(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setTelephonePro("");
        assertEquals(utilisateur.getTelephonePro(), "");
    }

    @Test
    public void getTelephonePro_casDonnee(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setTelephonePro("ddd");
        assertEquals(utilisateur.getTelephonePro(), "ddd");
    }

    /**___BirthDate___**/
    @Test
    public void getBirthDate_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setBirthDate(null);
        assertNull(utilisateur.getBirthDate());
    }

    @Test
    public void getBirthDate_casDonne(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setBirthDate(new Date(1));
        assertEquals(utilisateur.getBirthDate(), new Date(1));
    }

    /**___RegistrationDate___**/
    @Test
    public void getRegistrationDate_casNull(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRegistrationDate(null);
        assertNull(utilisateur.getRegistrationDate());
    }

    @Test
    public void getRegistrationDate_casDonne(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setRegistrationDate(new Date(1));
        assertEquals(utilisateur.getRegistrationDate(), new Date(1));
    }
}

