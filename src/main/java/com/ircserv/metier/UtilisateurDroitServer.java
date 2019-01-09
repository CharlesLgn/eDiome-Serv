package com.ircserv.metier;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "droit_utilisateur")
public class UtilisateurDroitServer implements Serializable {

    private int id;
    private Droit droit;
    private Utilisateur user;
    private Server serveur;

    public UtilisateurDroitServer() {
        this.id = -1;
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
    public Droit getDroit() {
        return droit;
    }


    public void setDroit(Droit droit) {
        this.droit = droit;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_utilisateur")
    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_serveur")
    public Server getServeur() {
        return serveur;
    }

    public void setServeur(Server serveur) {
        this.serveur = serveur;
    }
}
