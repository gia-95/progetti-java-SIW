package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Curatore {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nomeCognome;
	
	@Column(nullable = false)
	private Integer matriola;
	
	@OneToMany (mappedBy = "curatore") // il fetch di default LAZY va bene perchè potrei voler sapere solo le info 
	private List<Collezione> collezioni; // sul nome o sulla matricola

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCognome() {
		return nomeCognome;
	}

	public void setNomeCognome(String nomeCognome) {
		this.nomeCognome = nomeCognome;
	}

	public Integer getMatriola() {
		return matriola;
	}

	public void setMatriola(Integer matriola) {
		this.matriola = matriola;
	}

	public List<Collezione> getCollezioni() {
		return collezioni;
	}

	public void setCollezioni(List<Collezione> collezioni) {
		this.collezioni = collezioni;
	}

}
