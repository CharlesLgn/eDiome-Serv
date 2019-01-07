package com.ircserv.metier;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Server server;
    private Timestamp date;
    private Utilisateur user;
    private String contenu;
    private PieceJointe id_pj;


    public Message(int id, Utilisateur user, Timestamp date, String contenu, Server server, PieceJointe id_pj) {
        this.id = id;
        this.user = user;
        this.id_pj = id_pj;
        this.date = date;
        this.contenu = contenu;
        this.server = server;
    }

    public Message(){}

    @Id
    @Column(name = "id_message")
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


    @Column(name = "date_envoi")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Lob
    @Column(name = "corps_initial", columnDefinition = "LONGTEXT")
    public String getContenu() {
        return contenu;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pj")
    public PieceJointe getId_pj() {
        return id_pj;
    }

    public void setId_pj(PieceJointe id_pj) {
        this.id_pj = id_pj;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(user, message.user) && Objects.equals(id_pj, message.id_pj) && Objects.equals(date, message.date) && Objects.equals(contenu, message.contenu) && Objects.equals(server, message.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, id_pj, date, contenu, server);
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", user=" + user + ", typeMessage='" + id_pj + '\'' + ", date=" + date + ", contenu='" + contenu + '\'' + ", server=" + server + '}';
    }
}
