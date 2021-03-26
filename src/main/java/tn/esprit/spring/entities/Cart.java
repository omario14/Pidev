package tn.esprit.spring.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import tn.esprit.spring.dto.cart.AddToCartDto;

import java.util.Date;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private @NotBlank User user;

	@Column(name = "created_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "product_id" , referencedColumnName = "id")
	private Product product;

	private int quantity;
	

	public Cart() {
	}

	public Cart(AddToCartDto addToCartDto, User user) {
		this.user = user;
		this.product = addToCartDto.getProduct();
		this.quantity = addToCartDto.getQuantity();
		this.createdDate = new Date();
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getproduct() {
		return product;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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


	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", createdDate=" + createdDate + ", product=" + product
				+ ", quantity=" + quantity + "]";
	}

}
