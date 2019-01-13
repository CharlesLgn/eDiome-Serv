package com.ircserv.manager;

import com.ircserv.metier.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UtilisateurDroitServerManager extends HibernateFactory<UtilisateurDroitServer>{

    public UtilisateurDroitServerManager() {
        super(UtilisateurDroitServer.class);
    }

    @Override
    public UtilisateurDroitServer read(int id) {
        return null;
    }


    public Droit getDroit(Server server, Utilisateur utilisateur){
            try {
                return get(server,utilisateur).get(0).getDroit();
            } catch (Exception e) { return new Droit(); }
    }

    public UtilisateurDroitServer read(Server server, Utilisateur utilisateur){
        List<UtilisateurDroitServer> userServer = get(server,utilisateur);
        for (int i = 0; i < userServer.size(); i++) {
            try {
                UtilisateurDroitServer user = userServer.get(i);
                return user;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new UtilisateurDroitServer();
    }

    public List<UtilisateurDroitServer> getDroit(Server server){
        Session session = getSession();
        Query query = session.createQuery("SELECT userServDroit from UtilisateurDroitServer as userServDroit where userServDroit.serveur = :serv");
        query.setParameter("serv", server);
        List<UtilisateurDroitServer> userServer = query.list();
        return userServer;
    }


    private List<UtilisateurDroitServer> get(Server server, Utilisateur utilisateur){
        Session session = getSession();
        Query query = session.createQuery("from UtilisateurDroitServer as userServDroit where userServDroit.user = :user and userServDroit.serveur = :serv");
        query.setParameter("user", utilisateur);
        query.setParameter("serv", server);
        List<UtilisateurDroitServer> userServer = query.list();
        return userServer;
    }
}