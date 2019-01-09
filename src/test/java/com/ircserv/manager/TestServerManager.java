package com.ircserv.manager;

import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestServerManager {
    private ServerManager serverManager;
    private int id;
    private Server server;

    @Before
    public void initData(){
        serverManager = new ServerManager();
        serverManager.setup();
    }

    @Test
    public void TestCreate(){
        UtilisateurManager utilisateurManager = new UtilisateurManager();
        utilisateurManager.setup();
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("test");
        utilisateur.setPrenom("test");
        utilisateur.setIdentifiant("test");
        utilisateur.setMailPro("");
        utilisateur.setPassword("test");
        utilisateur.setTelephonePro("");
        id = utilisateurManager.create(utilisateur);
        utilisateur = utilisateurManager.readUser(id);
        server = new Server("test", utilisateur);
        Server serverTest = serverManager.create(server);

        assertEquals(serverTest.getCreateur(), server.getCreateur());
        assertEquals(serverTest.getName(), server.getName());
    }

    @Test
    public void testDelete(){}

}
