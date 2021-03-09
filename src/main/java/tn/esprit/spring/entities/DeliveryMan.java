package tn.esprit.spring.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DeliveryMan {
	public enum StateLivreur {
		Active, InActive;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String firstName;
	private String lastName;
	private String password;
	@Column(unique=true)
	private String email;
	private long tel;
	private float salary;
	private int deliveryNbre;
	
	private StateLivreur stateLivreur;
	
	@OneToMany(mappedBy="delMan")
	private List<Delivery> deliveries;
	
	
	

	public StateLivreur getStateLivreur() {
		return stateLivreur;
	}

	public void setStateLivreur(StateLivreur stateLivreur) {
		this.stateLivreur = stateLivreur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public int getDeliveryNbre() {
		return deliveryNbre;
	}

	public void setDeliveryNbre(int deliveryNbre) {
		this.deliveryNbre = deliveryNbre;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	

}
