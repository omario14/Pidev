package tn.esprit.spring.dto.cart;

import javax.validation.constraints.NotNull;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;

public class AddToCartDto {
	private Integer id;
	private @NotNull User user;
	private @NotNull Product product;
	private @NotNull Integer quantity;

	public AddToCartDto() {
	}

	public AddToCartDto(Integer id, @NotNull User user, @NotNull Product product, @NotNull Integer quantity) {
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}

	public AddToCartDto(Cart cart) {
		this.setId(cart.getId());
		this.setProduct(cart.getProduct());
		this.setUser(cart.getUser());
		this.setQuantity(cart.getQuantity());

	}


	@Override
	public String toString() {
		return "CartDto{" + "id=" + id + ", userId=" + user + ", productId=" + product + ", quantity=" + quantity
				+ ",";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
