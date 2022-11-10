package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Tarif")
public class Tarif implements Serializable {
    @Id
    @Column(name="Prix")
    private int prix;

    @Id
    @Column(name="Codecircuit")
    private int codeCircuit;

    @ManyToOne
    @JoinColumn(name="Codecircuit", nullable=false, insertable=false)
    @JsonBackReference
    private Circuit circuit;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getCodeCircuit() {
        return codeCircuit;
    }

    public void setCodeCircuit(int codeCircuit) {
        this.codeCircuit = codeCircuit;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
}
