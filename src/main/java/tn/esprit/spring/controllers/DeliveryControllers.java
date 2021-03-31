package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.SMS;
import tn.esprit.spring.repositories.DeliveryManRepository;
import tn.esprit.spring.services.IDeliveryServices;

@RestController
public class DeliveryControllers {

	@Autowired
	IDeliveryServices delservice;

	@Autowired
	DeliveryManRepository livreurRepository;
	
	// http://localhost:8081/SpringMVC/servlet/getDeliveries
	@GetMapping("/getDeliveries")
	public List<Delivery> getDeliveries() {
		List<Delivery> Elist = delservice.getDeliveries();
		return Elist;
	}

	// http://localhost:8081/SpringMVC/servlet/addDelivery
	@PostMapping("/addDelivery")
	public Delivery addEvent(@RequestBody Orders o) {
		/* Delivery Dy = delservice.addDelivery(D); */
		return null;
	}

	// http://localhost:8081/SpringMVC/servlet/Remove-Delivery/{Delivery-id}
	@DeleteMapping("/Remove-Delivery/{Delivery-id}")
	public void deleteDelivery(@PathVariable("Delivery-id") int id) {
		delservice.deleteDelivery(id);
		;
	}

	// http://localhost:8081/SpringMVC/servlet/update-Delivery
	@PutMapping("/update-Delivery")
	public Delivery updateDelivery(@RequestBody Delivery Dy) {
		return delservice.updateDelivery(Dy);
	}

	// http://localhost:8081/SpringMVC/servlet/passDelivery
	@PostMapping("/passDelivery")
	@ResponseBody
	public String passerLivraison(@RequestBody Orders order) {

		delservice.passerLivraison(order);
		// delservice.geocode(order.getAdress());

		return " delivery added";
	}

	// http://localhost:8081/SpringMVC/servlet/affecterLivraisonALivreur/{idLivreur}/{idDelivery}
	/*@PostMapping("/affecterLivraisonALivreur/{idLivreur}/{idDelivery}")
	@ResponseBody
	public void affecterLivraisonALivreur(@PathVariable("idLivreur") int idLivreur,
			@PathVariable("idDelivery") int idDelivery) {

		delservice.affecterLivraisonALivreur(idLivreur, idDelivery);

	}*/
	
	
	@PutMapping("/setDeleveryArrived")
	public void setDeleveryArrived(@RequestBody int deliveryId)
	{
		Delivery delivery=delservice.getById(deliveryId);
		delivery.setEtat(delivery.getEtat().Arrived);
		DeliveryMan livreurManage = livreurRepository.findById(delivery.getDelMan().getId()).get();
		livreurManage.setStateLivreur(true);
		SMS sms=new SMS(delivery.getLongitude()+","+delivery.getLattitude(),String.valueOf(livreurManage.getTel()));
		try {
			sms.send();
		} catch (Exception e) {
			System.out.println(e);
			
		}

		 delservice.updateDelivery(delivery);
		
	}
	
	
	@PostMapping("/affectDeliveryToNearestDeliveryMen")
	public DeliveryMan affectDeliveryToNearestDeliveryMen(@RequestBody int deliveryId) {
		return delservice.AffectDeliveryToNearesDeliveyMen(deliveryId);
	}
	
}
