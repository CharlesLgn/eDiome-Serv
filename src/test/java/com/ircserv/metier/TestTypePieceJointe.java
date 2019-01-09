package com.ircserv.metier;

import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestTypePieceJointe implements Serializable {
    @Test
    public void contructor_casNull(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        assertNull(typePieceJointe.getExtension());
        assertNull(typePieceJointe.getLibelle());
        assertEquals(typePieceJointe.getId(),-1);
    }

    @Test
    public void getId(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setId(2);
        assertEquals(typePieceJointe.getId(), 2);
    }

    /**___Extension___**/
    @Test
    public void getExtension_casNull(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setExtension(null);
        assertNull(typePieceJointe.getExtension());
    }

    @Test
    public void getExtension_casVide(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setExtension("");
        assertEquals(typePieceJointe.getExtension(), "");
    }

    @Test
    public void getExtension_casDonnee(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setLibelle("ddd");
        assertEquals(typePieceJointe.getLibelle(), "ddd");
    }


    /**___Libelle___**/
    @Test
    public void getLibelle_casNull(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setLibelle(null);
        assertNull(typePieceJointe.getLibelle());
    }

    @Test
    public void getLibelle_casVide(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setLibelle("");
        assertEquals(typePieceJointe.getLibelle(), "");
    }

    @Test
    public void getLibelle_casDonnee(){
        TypePieceJointe typePieceJointe = new TypePieceJointe();
        typePieceJointe.setLibelle("ddd");
        assertEquals(typePieceJointe.getLibelle(), "ddd");
    }
}
