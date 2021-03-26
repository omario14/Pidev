package tn.esprit.spring.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name = "orderitems")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderItemId;

	@Column(name = "quantity")
	private @NotNull int quantity;

	@Column(name = "price")
	private @NotNull double price;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Orders order;

	@OneToOne
	@JoinColumn(name = "productId", referencedColumnName = "id")
	private Product product;

	public OrderItem() {
	}

	
	public OrderItem( @NotNull int quantity, @NotNull double price, Orders order,Product product) {
		super();
		this.quantity = quantity;
		this.price = price;
        this.createdDate = new Date();
		this.order = order;
		this.product = product;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Integer getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}


	public Orders getOrder() {
		return order;
	}


	public void setOrder(Orders order) {
		this.order = order;
	}
	

}
