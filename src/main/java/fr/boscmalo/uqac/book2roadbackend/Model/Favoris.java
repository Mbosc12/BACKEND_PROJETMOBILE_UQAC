package fr.boscmalo.uqac.book2roadbackend.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Favoris")
public class Favoris {

    @Id
    @Column(name="Code")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;
    
    @Column(name="Codeutilisateur")
    private Long codeUtilisateur;
    
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

	public Long getCodeCircuit() {
		return codeCircuit;
	}

	public void setCodeCircuit(Long codeCircuit) {
		this.codeCircuit = codeCircuit;
	}
    
    
}
