package fr.boscmalo.uqac.book2roadbackend.Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="Code")
    private Long code;

    @Column(name="Codeutilisateur")
    private Long codeUtilisateur;

    @Column(name="Codecircuit")
    private Long codeCircuit;

    @Column(name="Datedebut")
    private Date dateDebut;

    @Column(name="Datefin")
    private Date dateFin;

    @Column(name="Prixfinal")
    private float prixFinal;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCodeUtilisateur() {
        return codeUtilisateur;
    }

    public void setCodeUtilisateur(Long codeUtilisateur) {
        this.codeUtilisateur = codeUtilisateur;
    }

    public Long getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(Long codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public float getPrixFinal() {
        return prixFinal;
    }

    public void setPrixFinal(float prixFinal) {
        this.prixFinal = prixFinal;
    }
}
