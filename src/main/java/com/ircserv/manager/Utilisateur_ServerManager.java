package com.ircserv.manager;

import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import com.ircserv.metier.Utilisateur_Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur_ServerManager {
    protected SessionFactory sessionFactory;

    public void setup() {
        // code to load Hibernate Session factory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        session.close();
    }

    protected void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }


    public void create(Utilisateur_Server userver) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(userver);

        session.getTransaction().commit();
        session.close();
    }

    public List<Server> getServerByUser(Utilisateur user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT userServer.code_serveur from Utilisateur_Server as userServer where userServer.no_utilisateur = :user");
        query.setParameter("user", user);
        List<Server> servers = query.list();
        return new ArrayList<>(servers);
    }

    public void getUserByServer(Server server) {

    }

    public static void main(String[] args) {
        ServerManager sm = new ServerManager();
        sm.setup();
        Server server = sm.readServer(5);
        UtilisateurManager um = new UtilisateurManager();

        um.setup();

        Utilisateur user = um.readUser(14);
        Utilisateur_ServerManager usm = new Utilisateur_ServerManager();
        Utilisateur_Server us = new Utilisateur_Server();
        us.setCode_serveur(server);
        us.setNo_utilisateur(user);
        usm.setup();
        usm.create(us);
        sm.create(server);


    }

}
