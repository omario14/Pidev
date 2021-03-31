package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	private float price;
	private int quantity;
	private String description;
	private float weight;
	@Size(min=13, max=13)
	@Column(unique=true)
	private String barCode;
	@Lob
	private String image;
	private int quantityPerRay;
	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	
	
	
	
	@JsonIgnore
	@ManyToOne
	private Category cat;
	
	@JsonIgnore
	@ManyToOne
	private Ray ray;
	
	public Product() {
		
	}
	
	

	public Product(String label, float price, int quantity, String description, float weight, String barCode,
			String image, int quantityPerRay, Category cat, Ray ray) {
		super();
		this.label = label;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.weight = weight;
		this.barCode = barCode;
		this.image = image;
		this.quantityPerRay = quantityPerRay;
		this.cat = cat;
		this.ray = ray;
	}



	public Product(String label, float price, int quantity, String description, float weight, String barCode,
			String image,Date expirationDate) {


		super();

		this.label = label;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.weight = weight;
		this.barCode = barCode;
		this.image = image;
		this.expirationDate=expirationDate;
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

	public Ray getRay() {
		return ray;
	}

	public void setRay(Ray ray) {
		this.ray = ray;
	}



	public int getQuantityPerRay() {
		return quantityPerRay;
	}



	public void setQuantityPerRay(int quantityPerRay) {
		this.quantityPerRay = quantityPerRay;
	}



	public Date getExpirationDate() {
		return expirationDate;
	}



	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	

}
