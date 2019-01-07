package com.ircserv.metier;


import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "piece_jointe")
public class PieceJointe {

    private int id_piece_jointe;
    private String chemin;
    private TypePieceJointe id_type_pj;


    public PieceJointe() {
    }

    @Id
    @Column(name = "id_piece_jointe")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_piece_jointe() {
        return id_piece_jointe;
    }

    public void setId_piece_jointe(int id_piece_jointe) {
        this.id_piece_jointe = id_piece_jointe;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_pj")
    public TypePieceJointe getId_type_pj() {
        return id_type_pj;
    }

    public void setId_type_pj(TypePieceJointe id_type_pj) {
        this.id_type_pj = id_type_pj;
    }
}
