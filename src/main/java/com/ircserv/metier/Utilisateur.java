package com.ircserv.metier;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String identifiant;
    private String password;
    private Date birthDate;
    private Date registrationDate;
    private String mailPro;
    private String telephonePro;

    public Utilisateur() {
        this.id = -1;
    }

    @Id
    @Column(name = "no_utilisateur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "identifiant")
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    @Column(name = "mot_de_passe")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "date_naissance")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "date_inscription")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "mail_pro")
    public String getMailPro() {
        return mailPro;
    }

    public void setMailPro(String mailPro) {
        this.mailPro = mailPro;
    }

    @Column(name = "telephone_pro")
    public String getTelephonePro() {
        return telephonePro;
    }

    public void setTelephonePro(String telephonePro) {
        this.telephonePro = telephonePro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(identifiant, that.identifiant) && Objects.equals(password, that.password) && Objects.equals(birthDate, that.birthDate) && Objects.equals(registrationDate, that.registrationDate) && Objects.equals(mailPro, that.mailPro) && Objects.equals(telephonePro, that.telephonePro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, identifiant, password, birthDate, registrationDate, mailPro, telephonePro);
    }
}

