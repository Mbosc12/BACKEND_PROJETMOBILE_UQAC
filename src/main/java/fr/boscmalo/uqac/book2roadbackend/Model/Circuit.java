package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Circuit")
public class Circuit {

    @Id
    @Column(name="Code")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;

    @Column(name="Nom")
    private String nom;

    @Column(name="Adresse")
    private String adresse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codepostal", nullable = false)
    @JsonIgnoreProperties(value = {"Ville", "hibernateLazyInitializer"})
    private Ville ville;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codeproprietaire", nullable = false)
    @JsonIgnoreProperties(value = {"Utilisateur", "hibernateLazyInitializer"})
    private Utilisateur utilisateur;

    @Column(name="Description")
    private String description;

    @Column(name="Tarif")
    private float tarif;

    @OneToMany
    @JoinTable(
            name="CIRCUIT_RESERVATION",
            joinColumns = @JoinColumn(name="Codecircuit"),
            inverseJoinColumns = @JoinColumn(name="Codereservation")
    )
    private List<Reservation> reservations = new ArrayList<>();


    @Column(name="Region")
    private Long codeRegion;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(Long codeRegion) {
        this.codeRegion = codeRegion;
    }
    
}
