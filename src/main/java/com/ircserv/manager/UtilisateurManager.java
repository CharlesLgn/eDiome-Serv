package com.ircserv.manager;
import com.ircserv.metier.Utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Date;


public class UtilisateurManager {
    protected SessionFactory sessionFactory;

    protected void setup() {
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

    protected void create() {
        // code to save a book
        Utilisateur user = new Utilisateur();
        user.setNom("Cuoco");
        user.setPrenom("Lucas");
        user.setIdentifiant("LucasCuoco");
        user.setDate_inscription(new Date(System.currentTimeMillis()));
        user.setDate_naissance(new Date(System.currentTimeMillis()));
        user.setMot_de_passe("test");
        user.setMail_pro("test");
        user.setTelephone_pro("test");


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    protected void read() {
       /** // code to get a book
        Session session = sessionFactory.openSession();

        long bookId = 2;
        Book book = session.get(Book.class, bookId);

        System.out.println("Title: " + book.getTitre());
        System.out.println("Author: " + book.getAuteur());
        System.out.println("Price: " + book.getPrix());

        session.close();**/
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

    public static void main(String[] args) {
        // code to run the program
        UtilisateurManager manager = new UtilisateurManager();
        manager.setup();
        manager.create();
        manager.exit();
    }
}


