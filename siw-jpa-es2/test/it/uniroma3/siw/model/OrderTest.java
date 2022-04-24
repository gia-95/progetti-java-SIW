package it.uniroma3.siw.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeAll
	public static void initEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("products-unit-test");
		em = emf.createEntityManager();
	}
	
	@AfterAll
	public static void closeEntityManager() throws SQLException {
		if (em != null) em.close();
		if (emf != null) emf.close();
	}

	@BeforeEach
	public void initTransaction() {
		tx = em.getTransaction();
	}

	
	@Test
	public void controllaFetchEAGERlistaOrdini() {
		
		Order ordine = new Order();
		OrderLine rigaOrdine = new OrderLine();
		ordine.addOrderLine(rigaOrdine);
		
		tx.begin();
		em.persist(ordine);
		em.persist(rigaOrdine);
		tx.commit();
		
		//Controllo che abbia agginto Id=1 dopo il persist();
		assertEquals(1, ordine.getId());
		
		Order ordineTemp = em.find(Order.class, ordine.getId());
		assertNotNull(ordineTemp);
		
		//Controllo che effettivamente quando carica dal DB l'oggetto "Order" (ordineTemp), lui carica
		//	anche la lista degli "OrderLine" --> fetch.EAGER
		assertNotNull(ordineTemp.getOrderLines());
		assertEquals (1, ordineTemp.getOrderLines().size());
	}
	
}
