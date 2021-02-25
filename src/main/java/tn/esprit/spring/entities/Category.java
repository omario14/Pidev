package tn.esprit.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String labelCat;
	private String mark;
	
	@OneToMany(mappedBy="cat")
	private List<Product> products;
	
	@OneToMany(mappedBy="cat")
	private List<Publicity> pubs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabelCat() {
		return labelCat;
	}

	public void setLabelCat(String labelCat) {
		this.labelCat = labelCat;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Publicity> getPubs() {
		return pubs;
	}

	public void setPubs(List<Publicity> pubs) {
		this.pubs = pubs;
	}
	
	

}
