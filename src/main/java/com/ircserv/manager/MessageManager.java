package com.ircserv.manager;

import com.ircserv.metier.Message;
import com.ircserv.metier.PieceJointe;
import com.ircserv.metier.Server;
import com.ircserv.metier.Utilisateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
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
        sessionFactory.close();
    }

    public Message readMessage(int id) {
        Session session = sessionFactory.openSession();
        Message message = session.get(Message.class, id);
        return message;
    }


    public void create(Message message) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
        session.close();
    }

    public List<Message> getMessagesByServ(Server server){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Message as message where message.server = :server");
        query.setParameter("server", server);
        List<Message> messages = query.list();
        return new ArrayList<>(messages);
    }



    public static void main(String[] args) {
        MessageManager messageManager = new MessageManager();
        messageManager.setup();

        UtilisateurManager um = new UtilisateurManager();
        um.setup();
        Utilisateur user = um.readUser(14);
        ServerManager sm = new ServerManager();
        sm.setup();
        Server server = sm.readServer(1);
        Message msg = new Message();
        msg.setContenu("test");
        msg.setDate(new Timestamp(System.currentTimeMillis()));
        msg.setServer(server);
        msg.setUser(user);
        messageManager.create(msg);

        PieceJointeManager pj = new PieceJointeManager();



    }
}
