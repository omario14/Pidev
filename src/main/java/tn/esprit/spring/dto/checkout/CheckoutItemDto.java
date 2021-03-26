package tn.esprit.spring.dto.checkout;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;

public class CheckoutItemDto {

	private String productName;
	private int quantity;
	private double price;
	private long productId;
	private int userId;

	public CheckoutItemDto() {
	}

	public CheckoutItemDto(String productName, int quantity, double price, long productId, int userId) {
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.productId = productId;
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


}
