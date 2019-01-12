package com.ircserv.manager;

import com.ircserv.metier.Server;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class ServerManager extends HibernateFactory<Server>{

    public ServerManager(){
        super(Server.class);
    }

    public List<Server> getAllServ(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Server as serv");
        return query.list();
    }
}


