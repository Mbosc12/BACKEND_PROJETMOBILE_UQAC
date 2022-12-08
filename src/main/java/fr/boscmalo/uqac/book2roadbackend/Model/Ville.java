package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Ville")
public class Ville {

    @Id
    @Column(name="Codepostal")
    private Long codePostal;

    @Column(name="Nom")
    private String nom;

    @Column(name="Latitude")
    private String latitude;

    @Column(name="Longitude")
    private String longitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numerodepartement", nullable = false)
    @JsonIgnoreProperties(value = {"Ville", "hibernateLazyInitializer"})
    private Departement departement;


    public Long getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(Long codePostal) {
        this.codePostal = codePostal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
