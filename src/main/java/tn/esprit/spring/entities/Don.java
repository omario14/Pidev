package tn.esprit.spring.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Don {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private float don;
	@OneToMany
	private List<User> userr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getDon() {
		return don;
	}
	public void setDon(float don) {
		this.don = don;
	}
	public List<User> getUserr() {
		return userr;
	}
	public void setUserr(List<User> userr) {
		this.userr = userr;
	}

	
}
