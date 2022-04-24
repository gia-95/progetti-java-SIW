package it.uniroma3.siw.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Provider {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String phoneNumber;
	
	private String email;
	
	private String vatin;
	
	@ManyToMany (mappedBy = "providers")
	private List<Product> products;
	
	@OneToOne
	private Address address;
	
	
	public Provider() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
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


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getVatin() {
		return vatin;
	}


	public void setVatin(String vatin) {
		this.vatin = vatin;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}
