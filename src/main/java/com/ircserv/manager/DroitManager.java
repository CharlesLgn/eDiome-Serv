package com.ircserv.manager;

import com.ircserv.metier.Droit;
import com.ircserv.metier.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DroitManager {

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

    public Droit readDroit(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();

        Droit droit = session.get(Droit.class, id);

        return droit;


    }


    public void create(Droit droit) {


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(droit);

        session.getTransaction().commit();
        session.close();
    }



    public static void main(String[] args) {
       DroitManager dm = new DroitManager();
       dm.setup();
       Droit droit = new Droit();
       droit.setDescription("testDesc");
       droit.setLibelle("testLib");
       dm.create(droit);
       dm.exit();



    }

}

