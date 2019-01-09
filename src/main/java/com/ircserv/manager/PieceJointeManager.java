package com.ircserv.manager;

import com.ircserv.metier.PieceJointe;
import com.ircserv.metier.TypePieceJointe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PieceJointeManager {

    protected SessionFactory sessionFactory;
    public void setup() {
        // code to load Hibernate Session factory
        HibernateUtils hibernateUtils = new HibernateUtils();
        sessionFactory = hibernateUtils.setup(sessionFactory);
    }

    protected void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }

    public PieceJointe readPJ(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();

        PieceJointe pj = session.get(PieceJointe.class, id);

        return pj;


    }


    public void create(PieceJointe pj) {


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(pj);

        session.getTransaction().commit();
        session.close();
    }

    protected void delete(int id) {

        // code to remove a book
        PieceJointe pj = new PieceJointe();
        pj.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(pj);

        session.getTransaction().commit();
        session.close();
    }



    public static void main(String[] args) {
        TypePieceJointe tpj;
        TypePieceJointeManager tpjm = new TypePieceJointeManager();
        tpjm.setup();
        tpj = tpjm.readTPJ(1);
        PieceJointeManager pjm = new PieceJointeManager();
        pjm.setup();
        PieceJointe pj = new PieceJointe();
        pj.setChemin("path");
        pj.setTypePieceJointe(tpj);
        pjm.create(pj);
    }


}
