package com.ircserv.manager;

import com.ircserv.metier.Message;
import com.ircserv.metier.Server;
import com.ircserv.metier.UtilisateurServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class ServerManager {
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

    public Server readServer(int id) {
        // code to get a book
        Session session = sessionFactory.openSession();
        Server server = session.get(Server.class, id);
        return server;
    }


    public Server create(Server server) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int noServer = (int)session.save(server);

        session.getTransaction().commit();
        session.close();

        UtilisateurServerManager usm = new UtilisateurServerManager();
        UtilisateurServer utilisateur_server = new UtilisateurServer();
        utilisateur_server.setServer(readServer(noServer));
        utilisateur_server.setUser(server.getCreateur());

        usm.setup();
        usm.create(utilisateur_server);

        return readServer(noServer);
    }

    public void delete(int id) {
       UtilisateurServerManager usm = new UtilisateurServerManager();
       usm.setup();

       Server server = readServer(id);
       usm.deleteAllByServ(server);
       Session session = sessionFactory.openSession();
       session.beginTransaction();
       session.delete(server);
       session.getTransaction().commit();
       session.close();
    }

    public List<Server> getAllServ(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Server as serv");
        return query.list();
    }

    public static void main(String[] args) {
        ServerManager sm = new ServerManager();
        sm.setup();
        sm.delete(4);
    }

}


