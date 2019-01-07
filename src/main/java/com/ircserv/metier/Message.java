package com.ircserv.metier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private Server server;
    private String typeMessage;
    private LocalDateTime date;
    private Utilisateur user;
    private String contenu;


    public Message(int id, Utilisateur user, String typeMessage, LocalDateTime date, String contenu, Server server) {
        this.id = id;
        this.user = user;
        this.typeMessage = typeMessage;
        this.date = date;
        this.contenu = contenu;
        this.server = server;
    }

    private Message(){}

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

    @Column(name = "type_message")
    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    @Column(name = "date_envoi")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Lob
    @Column(name = "corps_initial", columnDefinition = "LONGTEXT")
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && Objects.equals(user, message.user) && Objects.equals(typeMessage, message.typeMessage) && Objects.equals(date, message.date) && Objects.equals(contenu, message.contenu) && Objects.equals(server, message.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, typeMessage, date, contenu, server);
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", user=" + user + ", typeMessage='" + typeMessage + '\'' + ", date=" + date + ", contenu='" + contenu + '\'' + ", server=" + server + '}';
    }

    public static class MessageBuilder {

        private int id;
        private Server server;
        private String typeMessage;
        private LocalDateTime date;
        private Utilisateur user;
        private String contenu;

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

        public MessageBuilder addTypeMessage(String typeMessage){
            this.typeMessage = typeMessage;
            return this;
        }

        public MessageBuilder setDate(LocalDateTime date) {
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
            message.typeMessage = this.typeMessage;
            message.date        = this.date;
            message.user        = this.user;
            message.contenu     = this.contenu;

            return message;
        }

    }
}
