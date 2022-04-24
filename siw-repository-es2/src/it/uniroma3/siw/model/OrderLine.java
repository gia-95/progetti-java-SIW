package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
public class OrderLine {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private Float unitPrice;
	
	private Integer quantity;
	
	@ManyToOne (fetch = FetchType.EAGER)  //come Default
	private Product product;
	
	
	
	public OrderLine() {
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Float getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
