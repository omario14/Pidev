package tn.esprit.spring.dto.order;

import javax.validation.constraints.NotNull;

import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.Product;

public class OrderItemsDto {

    private @NotNull double price;
    private @NotNull int quantity;
    private @NotNull Orders order;
    private @NotNull Product product;

    public OrderItemsDto () {}

    public OrderItemsDto(@NotNull double price, @NotNull int quantity, @NotNull Orders order, @NotNull Product product) {
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

  
}
