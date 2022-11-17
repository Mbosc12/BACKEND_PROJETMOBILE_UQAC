package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Image")
public class Image {

    @Id
    @Column(name="Code")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;

    @Column(name="Lien")
    private String lien;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Codecircuit", nullable = false)
    @JsonIgnoreProperties(value = {"Circuit", "hibernateLazyInitializer"})
    private Circuit circuit;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
