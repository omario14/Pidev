package tn.esprit.spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Complaint {
	public enum decision {
		Remboursement,Echange,Réparation
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private decision decComplaint;
	private int DeliveryId;
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
	public decision getDecComplaint() {
		return decComplaint;
	}
	public void setDecComplaint(decision decComplaint) {
		this.decComplaint = decComplaint;
	}
	public int getDeliveryId() {
		return DeliveryId;
	}
	public void setDeliveryId(int deliveryId) {
		DeliveryId = deliveryId;
	}
	
	

}
