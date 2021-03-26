package tn.esprit.spring.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Coupon {

	@Id
	@Column(name = "ID", nullable = false, unique = true, updatable = false)
	private long id;
	@Column(name = "CODE", nullable = false)
	private String code;
	@Column(name = "amount", nullable = false)
	private double amount;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	private boolean used=false;
	public Coupon() {
	}
	

	public Coupon(String code, double amount, User user) {
		super();
		this.code = code;
		this.amount = amount;
		this.user = user;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public boolean isUsed() {
		return used;
	}


	public void setUsed(boolean used) {
		this.used = used;
	}


	

	
}
