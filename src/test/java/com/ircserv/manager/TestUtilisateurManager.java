package com.ircserv.manager;

import com.ircserv.metier.Utilisateur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUtilisateurManager {
    private UtilisateurManager utilisateurManager;
    private int id;

    @Before
    public void initData() {
        utilisateurManager = new UtilisateurManager();
    }

    @Test
    public void TestCreate() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("test");
        utilisateur.setPrenom("test");
        utilisateur.setIdentifiant("test");
        utilisateur.setMailPro("");
        utilisateur.setPassword("test");
        utilisateur.setTelephonePro("");
        Utilisateur userTest = utilisateurManager.create(utilisateur);

        assertEquals(userTest.getPrenom(), utilisateur.getPrenom());
        assertEquals(userTest.getNom(), utilisateur.getNom());
        assertEquals(userTest.getIdentifiant(), utilisateur.getIdentifiant());
        assertEquals(userTest.getMailPro(), utilisateur.getMailPro());
        assertEquals(userTest.getPassword(), utilisateur.getPassword());
        assertEquals(userTest.getTelephonePro(), utilisateur.getTelephonePro());
    }

    @Test
    public void testSelect() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("test");
        utilisateur.setPrenom("test");
        utilisateur.setIdentifiant("test");
        utilisateur.setMailPro("");
        utilisateur.setPassword("test");
        utilisateur.setTelephonePro("");

        Utilisateur userTest = utilisateurManager.read(id);

        assertEquals(userTest.getPrenom(), utilisateur.getPrenom());
        assertEquals(userTest.getNom(), utilisateur.getNom());
        assertEquals(userTest.getIdentifiant(), utilisateur.getIdentifiant());
        assertEquals(userTest.getMailPro(), utilisateur.getMailPro());
        assertEquals(userTest.getPassword(), utilisateur.getPassword());
        assertEquals(userTest.getTelephonePro(), utilisateur.getTelephonePro());
    }

    @Test
    public void testDelete() {
    }
}
