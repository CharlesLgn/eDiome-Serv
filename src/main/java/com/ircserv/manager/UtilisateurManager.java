package com.ircserv.manager;

import com.ircserv.metier.Utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;


public class UtilisateurManager {
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

    public void create(Utilisateur user) {
        // code to save a book
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setBirthDate(new Date(System.currentTimeMillis()));


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    public String read(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();
        Utilisateur user = session.get(Utilisateur.class, id);
        return user.getIdentifiant();
    }

    public Utilisateur readUser(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();
        return session.get(Utilisateur.class, id);
    }

    public int connexionCHeck(String pseudo, String mdp) {
        // code to get a book
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select user from Utilisateur as user where identifiant = :pseudo and password = :mdp");
        query.setParameter("pseudo", pseudo);
        query.setParameter("mdp", mdp);
        List users = query.list();
        for (int i = 0; i < users.size(); i++) {
            try {
                Utilisateur user = (Utilisateur) (users.get(i));
                int id = user.getId();
                return id;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;


    }

    protected void update() {
/**
 // code to modify a book
 Book book = new Book();
 book.setId(2);
 book.setTitre("Prise en main de eDiome");
 book.setAuteur("Charles Ligony");
 book.setPrix(19.99f);

 Session session = sessionFactory.openSession();
 session.beginTransaction();

 session.update(book);

 session.getTransaction().commit();
 session.close();**/
    }

    protected void delete() {
        /**
         // code to remove a book
         Book book = new Book();
         book.setId(2);

         Session session = sessionFactory.openSession();
         session.beginTransaction();

         session.delete(book);

         session.getTransaction().commit();
         session.close();**/
    }

}


