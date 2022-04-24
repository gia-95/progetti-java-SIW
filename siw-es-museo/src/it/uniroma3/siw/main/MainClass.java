package it.uniroma3.siw.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/* 
 * ( cascade = {CascadeType.PERSIST, CascadeType.REMOVE} )    PER RICORDARMI LA SINTASSI
 * 
 * In questo progetto non metto il PERSIST perchè seguendo le indicazioni delle slide non 
 * ci sono mai due entità che formano una composizione
 * 
 */


public class MainClass {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("museum-unit");
		EntityManager em = emf.createEntityManager();
		em.close();
		emf.close();
		
	}

}
