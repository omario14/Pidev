package tn.esprit.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	private float price;
	private int quantity;
	private String description;
	private float weight;
	@Column(unique=true)
	private String barCode;
	private String image;
	
	@ManyToOne
	private Category cat;
	
	public Product() {
		
	}

	public Product(String label, float price, int quantity, String description, float weight, String barCode,
			String image) {
		super();
		this.label = label;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.weight = weight;
		this.barCode = barCode;
		this.image = image;
	}

	public Product(String label, float price, int quantity, String description, float weight, String barCode,
			String image, Category cat) {
		super();
		this.label = label;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.weight = weight;
		this.barCode = barCode;
		this.image = image;
		this.cat = cat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Category getCat() {
		return cat;
	}

	public void setCat(Category cat) {
		this.cat = cat;
	}
	
	

}
