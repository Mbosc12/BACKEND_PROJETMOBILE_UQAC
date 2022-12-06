package fr.boscmalo.uqac.book2roadbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Avis")
public class Avis {

    @Id
    @Column(name="Code")
    private Long code;

    @Column(name="Codeutilisateur")
    private Long codeUtilisateur;
    
    @Column(name="Message")
    private String message;
    
    @Column(name="Etoiles")
    private int etoiles;
    
    @Column(name="Codecircuit")
    private Long codeCircuit;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getEtoiles() {
		return etoiles;
	}

	public void setEtoiles(int etoiles) {
		this.etoiles = etoiles;
	}

	public Long getCodeCircuit() {
		return codeCircuit;
	}

	public void setCodeCircuit(Long codeCircuit) {
		this.codeCircuit = codeCircuit;
	}

    

}
