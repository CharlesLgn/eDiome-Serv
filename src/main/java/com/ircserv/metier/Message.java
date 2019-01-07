package com.ircserv.metier;

import javax.persistence.*;
import java.io.Serializable;
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
    public String toString() {
        return "Message{" + "id=" + id + ", user=" + user + ", typeMessage='" + id_pj + '\'' + ", date=" + date + ", contenu='" + contenu + '\'' + ", server=" + server + '}';
    }

    public static class MessageBuilder {
        private int id;
        private Server server;
        private Timestamp date;
        private Utilisateur user;
        private String contenu;
        private PieceJointe id_pj;

        public MessageBuilder() {
            this.id = -1;
        }

        public MessageBuilder addNoUser(int id){
            this.id = id;
            return this;
        }

        public MessageBuilder addServ(Server server){
            this.server = server;
            return this;
        }

        public MessageBuilder addPieceJointe(PieceJointe pJ){
            this.id_pj = pJ;
            return this;
        }

        public MessageBuilder setDate(Timestamp date) {
            this.date = date;
            return this;
        }

        public MessageBuilder setUser(Utilisateur user) {
            this.user = user;
            return this;
        }

        public MessageBuilder setContenu(String contenu) {
            this.contenu = contenu;
            return this;
        }

        public Message build(){
            Message message = new Message();

            message.id          = this.id;
            message.server      = this.server;
            message.id_pj       = this.id_pj;
            message.date        = this.date;
            message.user        = this.user;
            message.contenu     = this.contenu;

            return message;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, server, date, user, contenu, id_pj);
    }
}
