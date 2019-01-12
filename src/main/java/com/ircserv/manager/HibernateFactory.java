package com.ircserv.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public abstract class HibernateFactory<T> {
    private Class<T> tType;

    HibernateFactory(Class<T> tType) {
        this.tType = tType;
    }

    protected SessionFactory sessionFactory;

    public void setup() {
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

    public T create(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int id = (int) session.save(t);
        session.getTransaction().commit();
        session.close();
        return read(id);
    }

    public T read(int id) {
        Session session = sessionFactory.openSession();
        T t = session.get(tType, id);
        return t;
    }

    public void delete(int id) {
        T t = read(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    public void update(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(t);
        session.getTransaction().commit();
        session.close();
    }

}
