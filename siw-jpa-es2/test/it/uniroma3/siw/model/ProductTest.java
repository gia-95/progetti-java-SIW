package it.uniroma3.siw.model;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.*;

import org.junit.jupiter.api.*;

class ProductTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	@BeforeAll
	public static void initEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("products-unit-test");
		em = emf.createEntityManager();
	}
	
	@AfterAll
	public static void closeEntityManager() throws SQLException{
		if (em != null) em.close();
		if (emf != null) emf.close();
	}

	@BeforeEach
	public void initTransaction() {
		tx = em.getTransaction();
	}

	// Controllo che l'Id viene generato automaticamente dopo il --> persist() (prima è "null")
	@Test
	public void shouldCreateAproduct() throws Exception {
		
		Product product = new Product();
		product.setName("SLANGAN");
		product.setCode("4321423131-AA");
		tx.begin();
		assertNull("ID should not be null", product.getId());
		em.persist(product);
		assertNotNull("ID should not be null", product.getId());
		tx.commit();
		assertNotNull("ID should not be null", product.getId());
		List<Product> products = em.createNamedQuery("findAllProducts", Product.class).getResultList();
		assertEquals(1, products.size());
	}
}