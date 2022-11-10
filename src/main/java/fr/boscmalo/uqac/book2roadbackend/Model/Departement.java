package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Departement")
public class Departement {

    @Id
    @Column(name="Numero")
    private Long numero;

    @Column(name="Nom")
    private String nom;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Coderegion", nullable = false)
    @JsonIgnoreProperties(value = {"Departement", "hibernateLazyInitializer"})
    private Region region;


    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
