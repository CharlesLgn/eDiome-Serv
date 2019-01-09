package com.ircserv.manager;

import com.ircserv.metier.Droit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DroitManager {

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


    protected void delete(int id) {

        // code to remove a book
        Droit droit = new Droit();
        droit.setId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(droit);

        session.getTransaction().commit();
        session.close();
    }
    public static void main(String[] args) {
       try{
           DroitManager dm = new DroitManager();
           dm.setup();
           Droit droit = new Droit();
           droit.setDescription("testDesc");
           droit.setLibelle("testLib");
           dm.create(droit);
           dm.exit();
       } catch (Exception e){
           e.printStackTrace();
        }
    }

}

