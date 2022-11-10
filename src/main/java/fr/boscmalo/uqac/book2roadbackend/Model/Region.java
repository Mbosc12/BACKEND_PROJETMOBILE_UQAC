package fr.boscmalo.uqac.book2roadbackend.Model;

import javax.persistence.*;

@Entity
@Table(name="Region")
public class Region {
    @Id
    @Column(name="Code")
    private long code;

    @Column(name="Nom")
    private String nom;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
