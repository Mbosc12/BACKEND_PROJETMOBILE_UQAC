package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Utilisateur")
public class Utilisateur {

    @Id
    @Column(name="Code")
    private Long code;

    @Column(name="Pseudo")
    private String pseudo;

    @Column(name="Motdepasse")
    private String motDePasse;

    @Column(name="Nom")
    private String nom;

    @Column(name="Prenom")
    private String prenom;

    @Column(name="Email")
    private String email;

    @Column(name="Telephone")
    private String telephone;

    @Column(name="Adresse")
    private String adresse;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codepostal", nullable = false)
    @JsonIgnoreProperties(value = {"Ville", "hibernateLazyInitializer"})
    private Ville ville;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
}
