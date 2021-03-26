package tn.esprit.spring.dto.product;

import javax.validation.constraints.NotNull;
import tn.esprit.spring.entities.Product;

public class ProductDto {

	private long id;
	private @NotNull String label;
	private @NotNull float price;
	private @NotNull int quantity;
	private @NotNull String description;
	private @NotNull float weight;
	private Long categoryId;
	private String barCode;
	private String image;

	public ProductDto(Product product) {
		 this.setId(product.getId());
		 this.setLabel(product.getLabel());
		//this.setImageURL(product.getImageURL());
		 this.setDescription(product.getDescription());
		 this.setPrice(product.getPrice());
		// this.setCategoryId(product.getCategory().getId());
	}

	public ProductDto(@NotNull String name, @NotNull String image, @NotNull float price, @NotNull String description,
			@NotNull Long categoryId) {
		this.label = name;
		this.image = image;
		this.price = price;
		this.description = description;
		this.categoryId = categoryId;
	}

	public ProductDto() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
