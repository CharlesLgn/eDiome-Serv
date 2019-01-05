package com.ircserv.manager;

import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import com.ircserv.metier.Utilisateur_Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Utilisateur_ServerManager {
    protected SessionFactory sessionFactory;

    public void setup() {
        HibernateUtils.setup(sessionFactory);
    }

    protected void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }


    public void create(Utilisateur_Server userver) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(userver);

        session.getTransaction().commit();
        session.close();
    }

    public List<Server> getServerByUser(int userId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select serveur.code_serveur, serveur.nom_serveur from serveur, utilisateur_serveur where utilisateur_serveur.code_serveur = serveur.code_serveur and utilisateur_server.no_utilisateur = :user");
        query.setParameter("user", userId);
        List serv = query.list();
        return serv;
    }

    public void getUserByServer(Utilisateur user) {

    }

    public static void main(String[] args) {
        ServerManager sm = new ServerManager();
        sm.setup();
        Server server = sm.readServer(1);
        UtilisateurManager um = new UtilisateurManager();

        um.setup();

        Utilisateur user = um.readUser(17);
        Utilisateur_ServerManager usm = new Utilisateur_ServerManager();
        Utilisateur_Server us = new Utilisateur_Server();
        us.setCode_serveur(server);
        us.setNo_utilisateur(user);
        usm.setup();
        usm.create(us);
        sm.create(server);


    }

}
