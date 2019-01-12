package com.ircserv.manager;

import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import com.ircserv.metier.UtilisateurServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurServerManager extends HibernateFactory<UtilisateurServer>{

    public UtilisateurServerManager(){
        super(UtilisateurServer.class);
    }

    public List<Server> getServerByUser(Utilisateur user) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT userServer.server from UtilisateurServer as userServer where userServer.user = :user");
        query.setParameter("user", user);
        List<Server> servers = query.list();
        return new ArrayList<>(servers);
    }

    public void getUserByServer(Server server) {

    }

    protected void deleteAllByServ(Server server) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UtilisateurServer as userServer where userServer.server = :server");
        query.setParameter("server", server);
        List<UtilisateurServer> utilisateurServers = query.list();
        for (UtilisateurServer user : utilisateurServers){
            delete(user.getId());
        }
    }



}
