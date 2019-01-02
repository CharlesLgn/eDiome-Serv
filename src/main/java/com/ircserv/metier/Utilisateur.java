package com.ircserv.metier;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;



@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {



    @Id
    @Column(name = "no_utilisateur")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int no_utilisateur;
    private String nom;
    private String prenom;
    private String pseudonyme;
    private String identifiant;
    private String mot_de_passe;
    private Date date_naissance;
    private Date date_inscription;
    private String adresse;
    private String mail_pro;
    private String mail_perso;
    private String telephone_pro;
    private String telephone_perso;
    private String telephone_fixe;

    public int getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public void setPseudonyme(String pseudonyme) {
        this.pseudonyme = pseudonyme;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail_pro() {
        return mail_pro;
    }

    public void setMail_pro(String mail_pro) {
        this.mail_pro = mail_pro;
    }

    public String getMail_perso() {
        return mail_perso;
    }

    public void setMail_perso(String mail_perso) {
        this.mail_perso = mail_perso;
    }

    public String getTelephone_pro() {
        return telephone_pro;
    }

    public void setTelephone_pro(String telephone_pro) {
        this.telephone_pro = telephone_pro;
    }

    public String getTelephone_perso() {
        return telephone_perso;
    }

    public void setTelephone_perso(String telephone_perso) {
        this.telephone_perso = telephone_perso;
    }

    public String getTelephone_fixe() {
        return telephone_fixe;
    }

    public void setTelephone_fixe(String telephone_fixe) {
        this.telephone_fixe = telephone_fixe;
    }
}
