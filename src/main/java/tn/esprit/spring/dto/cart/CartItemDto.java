package tn.esprit.spring.dto.cart;


import javax.validation.constraints.NotNull;

import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.entities.User;

public class CartItemDto {
    private Integer id;
    private @NotNull User user;
    private @NotNull Integer quantity;
    private @NotNull Product product;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setUser(cart.getUser());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", userId=" + user +
                ", quantity=" + quantity +
                ", productName=" + product.getLabel() +
                '}';
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
