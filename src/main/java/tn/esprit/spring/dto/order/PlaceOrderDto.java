package tn.esprit.spring.dto.order;


import javax.validation.constraints.NotNull;

import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.User;

public class PlaceOrderDto {
    private Integer id;
    private @NotNull User user;
    private @NotNull Double totalPrice;
    private @NotNull Double discount;

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(Orders order) {
        this.setId(order.getId());
        this.setUser(order.getUser());
        this.setTotalPrice(order.getTotalPrice());
        this.setDiscount(order.getDiscount());
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
    
}
