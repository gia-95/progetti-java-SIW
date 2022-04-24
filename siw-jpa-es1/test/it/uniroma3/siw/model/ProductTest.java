package it.uniroma3.siw.model;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Window.Type;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static EntityTransaction tx;
	
	private static Product Product1;
	private static Product Product2;
	
	
	
	@BeforeAll
	public static void initEntityManager() throws Exception {
		emf = Persistence.createEntityManagerFactory("products-unit");
		em = emf.createEntityManager();
	}
	
	@AfterAll
	public static void closeEntityManager() throws SQLException{
		if (em != null) em.close();
		if (emf != null) emf.close();
	}
	
	@BeforeEach
	public void initTransaction() {
		Product1 = new Product("name10", 10F, "descrption1", "code1");
		Product2 = new Product("name11", 20F, "descrption2", "code2");
		tx = em.getTransaction();
		Query deleteQuery = em.createQuery("DELETE FROM Product p");
		tx.begin();
		deleteQuery.executeUpdate();
		em.persist(Product1);
		em.persist(Product2);
		tx.commit();
		tx = em.getTransaction();
	}

	
	@Test
	public void selectAndDeleteTuplesDynamicQTest () {
		TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p", Product.class);
		List<Product> prodotti = q.getResultList();
		assertEquals(2, prodotti.size());
		assertEquals("name10", prodotti.get(0).getName());
		
		Query deleteQuery = em.createQuery("DELETE FROM Product p");
		tx.begin();
		assertEquals(2, deleteQuery.executeUpdate());
		tx.commit();
		
		q = em.createQuery("SELECT p FROM Product p", Product.class);
		prodotti = q.getResultList();
		assertEquals(0, prodotti.size());
	}
	
	@Test
	public void selectAndDeleteTuplesNamedQuary () {
		TypedQuery<Product> q  = em.createNamedQuery("findAllProducts", Product.class);
		List<Product> prodotti = q.getResultList();
		assertEquals(2, prodotti.size());
		
		Query nq = em.createNamedQuery("deleteAllProduct");
		tx.begin();
		assertEquals(2, nq.executeUpdate());
		tx.commit();
	}
	
	@Test
	public void selectQueryParametersTest () {
		Product Product3 = new Product("name12", 30F , "description3", "code3");
		Product Product5 = new Product("name13", 40F , "description4", "code4");
		Product Product4 = new Product("name14", 50F , "description5", "code5");
		tx.begin();
		em.persist(Product3);
		em.persist(Product4);
		em.persist(Product5);
		tx.commit();
		
		TypedQuery<Product> dQuery = em.createQuery("Select p From Product p", Product.class);
		assertEquals(5, dQuery.getResultList().size());
		
		float param = 20F;
		dQuery = em.createQuery("SELECT p FROM Product p WHERE p.price >= :prezzo ", Product.class);
		dQuery.setParameter("prezzo", param);
		assertEquals(4, dQuery.getResultList().size());

		param = 30f;
		dQuery.setParameter("prezzo", param);
		assertEquals(3, dQuery.getResultList().size());	
	}
	
	@Test
	public void queryConPiùArgomentiTest () {
		Product Product3 = new Product("name12", 30F , "description3", "code3");
		Product Product5 = new Product("name13", 40F , "description4", "code4");
		Product Product4 = new Product("name14", 40F , "description5", "code4");
		tx.begin();
		em.persist(Product3);
		em.persist(Product4);
		em.persist(Product5);
		tx.commit();
		
		TypedQuery<Object[]> dQuery = em.createQuery("SELECT DISTINCT p.price, count(*) FROM Product p GROUP BY p.price", Object[].class);
		List<Object[]> result = dQuery.getResultList();
		assertEquals(4 ,result.size());
		
	}
	
	
	
	
}
