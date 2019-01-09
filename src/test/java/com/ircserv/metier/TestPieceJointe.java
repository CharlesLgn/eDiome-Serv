package com.ircserv.metier;


import org.junit.Test;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestPieceJointe implements Serializable {
    @Test
    public void contructor_casNull(){
        PieceJointe pieceJointe = new PieceJointe();
        assertNull(pieceJointe.getChemin());
        assertNull(pieceJointe.getTypePieceJointe());
        assertEquals(pieceJointe.getId(), -1);
    }

    @Test
    public void contructor_casAll(){
        PieceJointe pieceJointe = new PieceJointe("cc", new TypePieceJointe());
        assertEquals(pieceJointe.getChemin(), "cc");
        assertEquals(pieceJointe.getTypePieceJointe(), new TypePieceJointe());
        assertEquals(pieceJointe.getId(), -1);
    }

    @Test
    public void getId(){
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setId(2);
        assertEquals(pieceJointe.getId(), 2);
    }

    /**___Chemin___**/
    @Test
    public void getChemin_casNull(){
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setChemin(null);
        assertNull(pieceJointe.getChemin());
    }

    @Test
    public void getChemin_casVide(){
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setChemin("");
        assertEquals(pieceJointe.getChemin(), "");
    }

    @Test
    public void getChemin_casDonnee(){
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setChemin("");
        assertEquals(pieceJointe.getChemin(), "");
    }


    /**___Type Piece Jointe___**/
    @Test
    public void getTypePieceJointe_casNull(){
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setTypePieceJointe(null);
        assertNull(pieceJointe.getTypePieceJointe());
    }

    @Test
    public void getTypePieceJointe_casVide(){
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setTypePieceJointe(new TypePieceJointe());
        assertEquals(pieceJointe.getTypePieceJointe(),  new TypePieceJointe());
    }

    @Test
    public void getTypePieceJointe_casDonnee(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setLibelle("ddd");
        PieceJointe pieceJointe = new PieceJointe();
        pieceJointe.setTypePieceJointe(typePieceJointe);
        assertEquals(pieceJointe.getTypePieceJointe(), typePieceJointe);
    }

}
