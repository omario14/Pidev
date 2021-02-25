package tn.esprit.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Complaint {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Decision decComplaint;
	private String Email;
	private String description;
	@ManyToOne
	private User userr;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Decision getDecComplaint() {
		return decComplaint;
	}
	public void setDecComplaint(Decision decComplaint) {
		this.decComplaint = decComplaint;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUserr() {
		return userr;
	}
	public void setUserr(User userr) {
		this.userr = userr;
	}
	
	

}
