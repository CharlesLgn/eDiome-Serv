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
        HibernateUtils.setup(sessionFactory);
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


    public void create(Server server) {


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(server);

        session.getTransaction().commit();
        session.close();
    }



    public static void main(String[] args) {
    ServerManager sm = new ServerManager();
    sm.setup();
    Server server = new Server();
    server.setName("testu");
    UtilisateurManager um = new UtilisateurManager();

    um.setup();

    server.setCreateur(um.readUser(14));
    sm.create(server);


    }

}


