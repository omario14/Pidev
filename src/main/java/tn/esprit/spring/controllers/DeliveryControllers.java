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

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.services.IDeliveryServices;

@RestController
public class DeliveryControllers {

	@Autowired
	IDeliveryServices delservice;
	
	
	
	// http://localhost:8081/SpringMVC/servlet/getDeliveries
			@GetMapping("/getDeliveries")
			public List <Delivery> getDeliveries(){
			List <Delivery> Elist = delservice.getDeliveries();
				return Elist;
				}
			// http://localhost:8081/SpringMVC/servlet/addDelivery
			@PostMapping("/addDelivery")
			public Delivery addEvent(@RequestBody Orders o) {
				/*Delivery Dy = delservice.addDelivery(D);*/
				return null  ;
		}
			// http://localhost:8081/SpringMVC/servlet/Remove-Delivery/{Delivery-id}
			@DeleteMapping("/Remove-Delivery/{Delivery-id}")
			public void deleteDelivery(@PathVariable ("Delivery-id") int id) {
				delservice.deleteDelivery(id);;
			}

			// http://localhost:8081/SpringMVC/servlet/update-Delivery
			@PutMapping("/update-Delivery")
			public Delivery updateDelivery(@RequestBody Delivery Dy) {
				return delservice.updateDelivery(Dy);
			}
			
			// http://localhost:8081/SpringMVC/servlet/passDelivery
			@PostMapping("/passDelivery")
			@ResponseBody
			public String passerLivraison(@RequestBody Orders order){
				
			
				   delservice.passerLivraison(order);
				   //delservice.geocode(order.getAdress());
		
			return" delivery added";
			}
			
			// http://localhost:8081/SpringMVC/servlet/affecterLivraisonALivreur/{idLivreur}/{idDelivery}
			@PostMapping("/affecterLivraisonALivreur/{idLivreur}/{idDelivery}")
			@ResponseBody
			public void affecterLivraisonALivreur(@PathVariable ("idLivreur")int idLivreur, @PathVariable ("idDelivery") int idDelivery){
				
		
				 delservice.affecterLivraisonALivreur(idLivreur, idDelivery);
		    
			}
}
