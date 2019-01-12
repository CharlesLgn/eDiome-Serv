package com.ircserv.manager;

import com.ircserv.metier.Droit;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DroitManager extends HibernateFactory<Droit> {
    public DroitManager() {
        super(Droit.class);
    }

    public List<Droit> getall() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Droit as droit");
        return query.list();
    }
}

