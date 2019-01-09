package com.ircserv.manager;

import com.ircserv.metier.Server;
import com.ircserv.metier.utilisateurServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


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
        int noServer = (int)session.save(server);

        session.getTransaction().commit();
        session.close();

        UtilisateurServerManager usm = new UtilisateurServerManager();
        utilisateurServer utilisateur_server = new utilisateurServer();
        utilisateur_server.setServer(readServer(noServer));
        utilisateur_server.setUser(server.getCreateur());

        usm.setup();
        usm.create(utilisateur_server);

        return readServer(noServer);
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


