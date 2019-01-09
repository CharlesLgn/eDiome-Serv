package com.ircserv.manager;

import com.ircserv.metier.Droit;
import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import com.ircserv.metier.UtilisateurDroitServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class utilisateurDroitServerManager {

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




    public void create(UtilisateurDroitServer uds) {


        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(uds);

        session.getTransaction().commit();
        session.close();
    }



    public static void main(String[] args) {
        UtilisateurManager um =  new UtilisateurManager();
        ServerManager sm = new ServerManager();
        DroitManager dm = new DroitManager();
        utilisateurDroitServerManager udsm = new utilisateurDroitServerManager();
        um.setup();
        sm.setup();
        dm.setup();
        Utilisateur user = um.readUser(14);
        Server server = sm.readServer(1);
        Droit droit = dm.readDroit(1);
        UtilisateurDroitServer usd = new UtilisateurDroitServer();
        usd.setUser(user);
        usd.setServeur(server);
        usd.setDroit(droit);
        udsm.setup();
        udsm.create(usd);

    }

}