package com.ircserv.manager;

import com.ircserv.metier.Droit;
import com.ircserv.metier.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DroitManager extends HibernateFactory<Droit> {
    public DroitManager() {
        super(Droit.class);
    }
}

