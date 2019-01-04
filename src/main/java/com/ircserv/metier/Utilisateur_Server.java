package com.ircserv.metier;


import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;



@Entity
@Table(name = "utilisateur_serveur")
public class Utilisateur_Server implements Serializable {
    private int id;
    private Utilisateur no_utilisateur;
    private Server code_serveur;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_utilisateur")
    public Utilisateur getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateur no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_serveur")
    public Server getCode_serveur() {
        return code_serveur;
    }

    public void setCode_serveur(Server code_serveur) {
        this.code_serveur = code_serveur;
    }


}
