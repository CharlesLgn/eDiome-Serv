package com.ircserv.manager;

import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;


public class ServerManager {
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

    public Server readServer(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();

        Server server = session.get(Server.class, id);

        return server;


    }


    public Server create(Server server) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Server server1 = readServer((int)session.save(server));

        session.getTransaction().commit();
        session.close();
        return server1;
    }


    public static void main(String[] args) {
        ServerManager sm = new ServerManager();
        sm.setup();
        Server server = new Server();
        server.setName("Profs");
        UtilisateurManager um = new UtilisateurManager();

        um.setup();

        server.setCreateur(um.readUser(14));
        sm.create(server);
    }

}


