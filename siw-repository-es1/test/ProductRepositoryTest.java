import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.repository.ProductRepository;

class ProductRepositoryTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	private static ProductRepository prepo;
	
	@BeforeAll
	public static void beforeAll () {
		emf = Persistence.createEntityManagerFactory("products-unit");
		em = emf.createEntityManager();
		prepo = new ProductRepository();
		prepo.setEntityManager(em);
	}
	
	@AfterAll
	public static void closeEntityManager() throws SQLException{
		if (em != null) em.close();
		if (emf != null) emf.close();
	}
	
	@BeforeEach
	public void beforeEach () {
		tx = em.getTransaction();
	}
	
	@Test
	public void productRepositoryTest () {		
		tx.begin();
		prepo.deleteAll();
		tx.commit();
		
		assertEquals(0, prepo.findAll().size());
		
		Product p1 = new Product("p1", 1F, "desc1", "code-001");
		assertNull(p1.getId());
		Product p2 = new Product("p2", 2F, "desc2", "code-002");
		Product p3 = new Product("p3", 3F, "desc3", "code-003");

		
		tx.begin();
		prepo.save(p1);
		prepo.save(p2);
		prepo.save(p3);
		
		assertEquals(3, prepo.findAll().size()); 	//Questa cosa è strana; prima del commit sul database non mi fa vedere le ennuple,
													// però l'assert mi dice che se faccio la query per prenderli, quelli ci sono già...
		tx.commit();
		
		assertEquals(3, prepo.findAll().size());
		
		//Elimina un prodotto tramite metodo 'delete' repository
		tx.begin();
		prepo.delete(p3);
		tx.commit();
		assertEquals(2, prepo.findAll().size());
		
		// trovo Product tramite id
		assertNotNull( prepo.findById((long) 1));
		assertNull( prepo.findById((long) 3));
		
		//provo count
		assertEquals (2, (int)prepo.count());
		
	}

}
