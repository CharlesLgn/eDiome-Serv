package com.ircserv.metier;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "serveur")
public class Server implements Serializable {
  private int id;
  private String name;
  private Utilisateur createur;

  public Server(int id, String name, Utilisateur user) {
    this.id = id;
    this.name = name;
    this.createur=user;
  }

  public Server() {
    this.id=-1;
  }

  public Server(String name, Utilisateur user) {
    this(-1, name, user);
  }

  @Id
  @Column(name = "code_serveur")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public int getId() {
    return id;
  }

  @Column(name = "nom_serveur")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(int id) {
    this.id = id;
  }


  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "no_createur")
  public Utilisateur getCreateur() {
    return createur;
  }

  public void setCreateur(Utilisateur createur) {
    this.createur = createur;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Server server = (Server) o;
    return id == server.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createur);
  }
}
