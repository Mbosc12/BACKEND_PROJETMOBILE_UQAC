package fr.boscmalo.uqac.book2roadbackend.Model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TarifId implements Serializable {
    private String prix;
    private long codeCircuit;

    public TarifId(String prix, long codeCircuit) {
        this.prix = prix;
        this.codeCircuit = codeCircuit;
    }

    public TarifId() {
    }
}
