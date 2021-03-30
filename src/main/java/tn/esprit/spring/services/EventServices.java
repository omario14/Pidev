package tn.esprit.spring.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.entities.Notifications;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.EventRepository;
import tn.esprit.spring.repositories.NotificationsRepository;
import tn.esprit.spring.repositories.UserRepository;


@Service
public class EventServices implements IEventServices  {
	@Autowired
	EventRepository erepository;
	
	@Autowired
	NotificationsRepository nrepository;
	@Autowired
	UserRepository urepository;

	@Override
	public List<Event> getEvents() {
		
		return (List<Event>) erepository.findAll();
	}

	@Override
	public Event addEvent(Event E) {
		Notifications N = new Notifications();
		N.setMessage(E.getDescription());
		N.setCreatedAt(E.getDateDebut());
		N.setEventId(E.getId());
		
		try {
			sendmail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nrepository.save(N);
		return erepository.save(E);
	}

	

	@Override
	public Event updateEvent(Event E) {
		
		return erepository.save(E);
	}

	@Override
	public void deleteEvent(int id) {
		
		erepository.deleteById(id);
	}
	
	private void sendmail() throws AddressException, MessagingException, IOException {
	
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("ezzeddinechariout17@gmail.com" , "az655656hjvj");
		      }
		   });
		   
		   //Message msg = new MimeMessage(session);
		  // msg.setFrom(new InternetAddress("ezzeddinechariout17@gmail.com", false));
		   
		   String listOfMails="";
		   boolean first=true;
		   for (User u : urepository.findAll()) {
			   if(first) {listOfMails+=u.getEmail();first=false;}
			listOfMails+=","+u.getEmail();
		}
		   Message msg = new MimeMessage(session);
		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(listOfMails));
		   msg.setSubject("new event");
		   msg.setContent("Tutorials point email", "text/html");
		   msg.setSentDate(new Date());
		   msg.setText("hello!!");
		   Transport.send(msg); 
		
		  // msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ezzeddine.chariout@gmail.com"));
		  // msg.setSubject("new event");
		  // msg.setContent("Tutorials point email", "text/html");
		  // msg.setSentDate(new Date());

		  // MimeBodyPart messageBodyPart = new MimeBodyPart();
		  // messageBodyPart.setContent("Tutorials point email", "text/html");

		  // Multipart multipart = new MimeMultipart();
		  // multipart.addBodyPart(messageBodyPart);
		  // MimeBodyPart attachPart = new MimeBodyPart();

		  // multipart.addBodyPart(attachPart);
		  // msg.setContent(multipart);
		  // msg.setText("hello!!");
		  // Transport.send(msg); 
		   
		}

}
