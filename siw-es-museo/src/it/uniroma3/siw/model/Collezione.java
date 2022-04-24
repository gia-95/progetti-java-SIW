package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Collezione {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;

	@OneToMany (fetch = FetchType.EAGER) // in linea generale, quando carico una stanza, lo faccio per sapere le 
	private List<Opera> opere;		    // opere al suo interno, quindi le carico subito.
	
	@ManyToOne (fetch = FetchType.LAZY) //Quando carico la stanza/collezione potrei non volere subito l'informazione 
	private Curatore curatore;			// sul curatore ma, per esempio, solo la lista delle opere al suo interno.
	
	public Long getId() {
		return id;
	}
	
	public Curatore getCuratore() {
		return curatore;
	}

	public void setCuratore(Curatore curatore) {
		this.curatore = curatore;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Opera> getOpere() {
		return opere;
	}

	public void setOpere(List<Opera> opere) {
		this.opere = opere;
	}
	
	
	
	
	
	
}
