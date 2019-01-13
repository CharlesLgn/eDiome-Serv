package com.ircserv.manager;

import com.ircserv.metier.TypePieceJointe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TypePieceJointeManager extends HibernateFactory<TypePieceJointe>{

    public TypePieceJointeManager(){
        super(TypePieceJointe.class);
    }

    public TypePieceJointe getPJbyExtension(String extention) {
        // code to get a book
        Session session = getSession();
        Query query = session.createQuery("from TypePieceJointe as TypePieceJointe where TypePieceJointe.extension = :ext");
        query.setParameter("ext", extention);
        List<TypePieceJointe> pj = query.list();
        return pj.size() == 0 ? new TypePieceJointe() : pj.get(0);
    }

}



