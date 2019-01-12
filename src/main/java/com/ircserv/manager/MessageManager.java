package com.ircserv.manager;

import com.ircserv.metier.Message;
import com.ircserv.metier.Server;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MessageManager extends HibernateFactory<Message>{

    public MessageManager() {
        super(Message.class);
    }

    public List<Message> getMessagesByServ(Server server){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Message as message where message.server = :server");
        query.setParameter("server", server);
        List messages = query.list();
        return new ArrayList<>(messages);
    }

}
