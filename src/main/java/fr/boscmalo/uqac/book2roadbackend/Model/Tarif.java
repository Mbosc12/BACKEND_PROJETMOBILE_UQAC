package fr.boscmalo.uqac.book2roadbackend.Model;

import javax.persistence.*;

@Entity
@IdClass(TarifId.class)
@Table(name="Tarif")
public class Tarif {

    @Id
    @Column(name="Prix")
    private String prix;

    @Id
    @Column(name="Codecircuit")
    private Long codeCircuit;

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public Long getCodecircuit() {
        return codeCircuit;
    }

    public void setCodecircuit(Long codeCircuit) {
        codeCircuit = codeCircuit;
    }
}
