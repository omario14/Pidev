package tn.esprit.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ray {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String type;
	
	@OneToMany(mappedBy = "ray")
	private List<Category> category;
	
	@ManyToOne
	private Shop shop;
	
	
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Ray() {
		
	}
	
	

	public Ray(String type, List<Category> category, Shop shop) {
		super();
		this.type = type;
		this.category = category;
		this.shop = shop;
	}

	public Ray(String type, List<Category> category) {
		super();
		this.type = type;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	
	
	
}
