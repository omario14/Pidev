package tn.esprit.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int quantity;
	private int etat;
	private int linePrice;
	
	@ManyToOne
	@JoinColumn(name="prod_id", referencedColumnName="id")
	private Product products;
	
	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName="id")
	private Order orderr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getLinePrice() {
		return linePrice;
	}

	public void setLinePrice(int linePrice) {
		this.linePrice = linePrice;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public Order getOrderr() {
		return orderr;
	}

	public void setOrderr(Order orderr) {
		this.orderr = orderr;
	}
	
	

}
