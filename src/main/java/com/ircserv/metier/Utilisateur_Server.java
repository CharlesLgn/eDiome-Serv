package com.ircserv.metier;


import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "utilisateur_serveur")
public class Utilisateur_Server implements Serializable {
    private int id;
    private Utilisateur user;
    private Server server;

    public Utilisateur_Server() {
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
    @JoinColumn(name = "no_utilisateur")
    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_serveur")
    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }


}
