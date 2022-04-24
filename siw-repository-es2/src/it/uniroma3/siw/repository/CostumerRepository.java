package it.uniroma3.siw.repository;

import java.util.List;

import javax.persistence.EntityManager;

import it.uniroma3.siw.model.Address;
import it.uniroma3.siw.model.Costumer;

/*
 * 		Questo repository gestisce la classe 'Costumer' e la classe 'Address'
 */
public class CostumerRepository {
	
	private EntityManager em;

	public Costumer save(Costumer c) {
		if (c.getId()!=null) {
			em.merge(c);
		}
		else {
			em.persist(c);
		}
		return c;
	}
	
	public Address save(Address a) {
		if (a.getId()!=null) {
			em.merge(a);
		}
		else {
			em.persist(a);
		}
		return a;
	}
	
	public Costumer findCostumerById(Long id) {
		return em.find(Costumer.class, id);
	}

	public Address findAddressById(Long id) {
		return em.find(Address.class, id);
	}
	
	public List<Costumer> findAllCostumer() {
		return em.createQuery("select c from Costumer c", Costumer.class).getResultList();
	}
	
	public List<Address> findAllAddress() {
		return em.createQuery("select a from Address a", Address.class).getResultList();
	}

	public void delete(Costumer c) {
		em.remove(c);
	}
	
	public void delete(Address a) {
		em.remove(a);
	}

	public void deleteAllCostumer(){
		this.em.createQuery("delete from Costumer").executeUpdate();
	}
	
	public void deleteAllAddress(){
		this.em.createQuery("delete from Address").executeUpdate();
	}

	public long countCostumers() {
		return (Long)this.em.createQuery("select count(id) from Costumer").getSingleResult();
	}
	
	public long countAddresses() {
		return (Long)this.em.createQuery("select count(id) from Address").getSingleResult();
	}

	public boolean existsCostumerById(Long id) {
		return (this.findCostumerById(id)!=null);
	}
	
	public boolean existsAddressById(Long id) {
		return (this.findAddressById(id)!=null);
	}

	public void setEntityManager(EntityManager em) {
		this.em = em; 
	}
}
