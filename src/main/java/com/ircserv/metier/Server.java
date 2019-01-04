package com.ircserv.metier;


import java.io.Serializable;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;



@Entity
@Table(name = "serveur")
public class Server implements Serializable {
  private int id;
  private String name;
  private Utilisateur createur;

  public Server(int id, String name) {
    this.id = id;
    this.name = name;

  }

  public Server() {}

  public Server(String name) {
    this(-1, name);
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


  @Column(name = "no_createur")
  public Utilisateur getCreateur() {
    return createur;
  }

  public void setCreateur(Utilisateur createur) {
    this.createur = createur;
  }
}
