package com.ircserv.metier;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDroit {

    @Test
    public void getId(){
        Droit droit = new Droit();
        droit.setId(2);
        assertEquals(droit.getId(), 2);
    }

    @Test
    public void contructor_casNull(){
        Droit droit = new Droit();
        assertNull(droit.getDescription());
        assertEquals(droit.getId(), -1);
        assertNull(droit.getLibelle());
    }

    @Test
    public void getDescription_casNull(){
        Droit droit = new Droit();
        droit.setDescription(null);
        assertNull(droit.getDescription());
    }

    @Test
    public void getDescription_casVide(){
        Droit droit = new Droit();
        droit.setDescription("");
        assertEquals(droit.getDescription(), "");
    }

    @Test
    public void getDescription_casDonnee(){
        Droit droit = new Droit();
        droit.setDescription("test");
        assertEquals(droit.getDescription(), "test");
    }

    @Test
    public void getLibelle_casNull(){
        Droit droit = new Droit();
        droit.setLibelle(null);
        assertNull(droit.getLibelle());
    }

    @Test
    public void getLibelle_casVide(){
        Droit droit = new Droit();
        droit.setLibelle("");
        assertEquals(droit.getLibelle(), "");
    }

    @Test
    public void getLibelle_casDonnee(){
        Droit droit = new Droit();
        droit.setLibelle("test");
        assertEquals(droit.getLibelle(), "test");
    }
}
