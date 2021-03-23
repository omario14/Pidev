package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;




@Entity
public class Comment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//private String desc;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subjectId", referencedColumnName="id")
	private Subject sub;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId", referencedColumnName="id")
	private User user;
	
	
	
	public Comment() {
		super();
	}
	public Comment(int id, String description,/*String desc,*/ Date date, Subject sub, User user) {
		super();
		this.id = id;
		this.description = description;
		//this.desc = desc;
		this.date = date;
		this.sub = sub;
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}*/
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Subject getSub() {
		return sub;
	}
	public void setSub(Subject sub) {
		this.sub = sub;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	
	

}
