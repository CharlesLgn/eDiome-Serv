package com.ircserv.manager;

import com.ircserv.metier.PieceJointe;
import com.ircserv.metier.Server;
import com.ircserv.metier.TypePieceJointe;
import com.ircserv.metier.Utilisateur_Droit_Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TypePieceJointeManager {
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

    public TypePieceJointe readTPJ(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();
        TypePieceJointe pj = session.get(TypePieceJointe.class, id);
        return pj;
    }

    public TypePieceJointe getPJbyExtension(String extention) {
        // code to get a book
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from TypePieceJointe as TypePieceJointe where TypePieceJointe.extension = :ext");
        query.setParameter("ext", extention);
        List<TypePieceJointe> pj = query.list();
        return pj.size() == 0 ? new TypePieceJointe() : pj.get(0);
    }

    public void create(TypePieceJointe pj) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pj);
        session.getTransaction().commit();
        session.close();
    }

    protected void delete(int id) {

        // code to remove a book
        TypePieceJointe tpj = new TypePieceJointe();
        tpj.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(tpj);

        session.getTransaction().commit();
        session.close();
    }


    public static void main(String[] args) {
        TypePieceJointe tpj = new TypePieceJointe();
        TypePieceJointeManager tpjm = new TypePieceJointeManager();
        tpjm.setup();
        tpj.setLibelle("Photo");
        tpj.setExtension(".jpg");
        tpjm.create(tpj);


    }

}



