package com.ircserv.manager;

import com.ircserv.metier.PieceJointe;

public class PieceJointeManager extends HibernateFactory<PieceJointe> {
    public PieceJointeManager(){
        super(PieceJointe.class);
    }
}
