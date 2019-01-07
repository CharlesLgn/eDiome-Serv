package com.ircserv.metier;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "droit_utilisateur")
public class Utilisateur_Droit_Server  implements Serializable {

    int id;
    Droit id_droit;
    Utilisateur no_utilisateur;
    Server code_serveur;

    public Utilisateur_Droit_Server() {
    }


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
    @JoinColumn(name = "id_droit")
    public Droit getId_droit() {
        return id_droit;
    }


    public void setId_droit(Droit id_droit) {
        this.id_droit = id_droit;
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
