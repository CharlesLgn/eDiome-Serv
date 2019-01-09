package com.ircserv.manager;

import com.ircserv.metier.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Utilisateur_Droit_ServerManager {

    protected SessionFactory sessionFactory;

    public void setup() {
        // code to load Hibernate Session factory
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
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




    public void create(Utilisateur_Droit_Server uds) {


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(uds);

        session.getTransaction().commit();
        session.close();
    }

    protected void delete(int id) {

        // code to remove a book
        Utilisateur_Droit_Server uds = new Utilisateur_Droit_Server();
        uds.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(uds);

        session.getTransaction().commit();
        session.close();
    }



    public static void main(String[] args) {

        Utilisateur_Droit_ServerManager udsm = new Utilisateur_Droit_ServerManager();
        udsm.setup();
        udsm.delete(1);

    }

}