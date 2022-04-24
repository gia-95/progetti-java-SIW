package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDateTime creationTime;
	
	@ManyToOne
	private Costumer costumer;
	
	@OneToMany (fetch = FetchType.EAGER)
	private List<OrderLine> orderLines;
	
	
	
	public Order() {
		this.orderLines = new LinkedList<>();
	}

	public Boolean addOrderLine (OrderLine item) {
		return this.orderLines.add(item);
	}


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public LocalDateTime getCreationTime() {
		return creationTime;
	}



	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}



	public Costumer getCostumer() {
		return costumer;
	}



	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}



	public List<OrderLine> getOrderLines() {
		return orderLines;
	}



	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	
	
	

}
