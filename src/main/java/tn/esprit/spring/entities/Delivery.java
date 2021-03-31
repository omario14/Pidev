package tn.esprit.spring.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Delivery {
	
	public enum StateDelivery {
		IN_PROGRESS, 
		Approved,Arrived
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private float price;
	@Temporal(TemporalType.DATE)
	private Date dateDel;
	private String address;
	private String Lattitude ;
	private String Longitude;
	
	@Enumerated
	private StateDelivery etat;
	
	
	private int userr;
	
	@OneToMany
	private List<Orders> orders;
	@ManyToOne
	private DeliveryMan delMan;
	
	public String getLattitude() {
		return Lattitude;
	}
	public void setLattitude(String lattitude) {
		Lattitude = lattitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getDateDel() {
		return dateDel;
	}
	public void setDateDel(Date dateDel) {
		this.dateDel = dateDel;
	}
	
	public StateDelivery getEtat() {
		return etat;
	}
	public void setEtat(StateDelivery etat) {
		this.etat = etat;
	}
	
	public int getUserr() {
		return userr;
	}
	public void setUserr(int userr) {
		this.userr = userr;
	}
	
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		
		this.orders = orders;
	}
	public DeliveryMan getDelMan() {
		return delMan;
	}
	public void setDelMan(DeliveryMan delMan) {
		this.delMan = delMan;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
