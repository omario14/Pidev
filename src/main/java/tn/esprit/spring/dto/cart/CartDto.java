package tn.esprit.spring.dto.cart;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private double discount;
    private double totalCost;

    public CartDto(List<CartItemDto> cartItemDtoList, double totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }
    
    public CartDto()
    {
    	
    }

    public CartDto(List<CartItemDto> cartItems, double discount, double totalCost) {
		super();
		this.cartItems = cartItems;
		this.totalCost = totalCost ;
		this.discount = discount;
	}


	public double getDiscount() {
		return discount;
	}


	public void setDiscount(double discount) {
		this.discount = discount;
	}


	public List<CartItemDto> getcartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItemDtoList) {
        this.cartItems = cartItemDtoList;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
}
