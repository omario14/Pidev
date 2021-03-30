package tn.esprit.spring.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notifications {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer notificationId;
	
	private String message;
	
	private Date createdAt;
	
	private int EventId;
	
	
	
	
	
	public Notifications(){}
	
	public Notifications(String message,Date createdAt){
		this.message = message;
		this.createdAt = createdAt;
	
	}


	public Integer getNotificationId() {
		return notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(java.util.Date date) {
		this.createdAt = date;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public int getEventId() {
		return EventId;
	}

	public void setEventId(int eventId) {
		EventId = eventId;
	}

}
