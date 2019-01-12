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
import java.util.List;


public class UtilisateurManager extends HibernateFactory<Utilisateur> {

    public UtilisateurManager(){
        super(Utilisateur.class);
    }

    public int connexionCHeck(String pseudo, String mdp) {
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

    public List<Utilisateur> readAllUser() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select user from Utilisateur as user");
        List users = query.list();
        return users;
    }

    public List<Utilisateur> readAllUserNotInServer(Server server) {
        return readSpecificUser(server, "select user from Utilisateur as user where user not in" +
                    "(select userServer.user from UtilisateurServer  as userServer where userServer.server = :serv)");
    }

    public List<Utilisateur> readAllUserInServer(Server server) {
        return readSpecificUser(server,
                "select userServer.user from UtilisateurServer  as userServer where userServer.server = :serv");
    }

    private List<Utilisateur> readSpecificUser(Server server, String request){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(request);
        query.setParameter("serv", server);
        List users = query.list();
        return users;
    }
}


