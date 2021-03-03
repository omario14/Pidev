package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.services.IDeliveryManServices;

@RestController
public class DeliveryManControllers {
	@Autowired
	IDeliveryManServices dservice;
	
	// http://localhost:8081/SpringMVC/servlet/getDeliveryMen
	@GetMapping("/getDeliveryMen")
	public List <DeliveryMan> getDeliveryMen(){
	List <DeliveryMan> Dlist = dservice.getDeliveryMen();
		return Dlist;
		}
	// http://localhost:8081/SpringMVC/servlet/addDeliveryMan
	@PostMapping("/addDeliveryMan")
	public DeliveryMan addDeliveryMan(@RequestBody DeliveryMan d) {
		DeliveryMan delv = dservice.addDeliveryMan(d);
		return delv ;
}
	// http://localhost:8081/SpringMVC/servlet/Remove-del/{Del-id}
	@DeleteMapping("/Remove-del/{Del-id}")
	public void deleteDel(@PathVariable ("Del-id") int id) {
		dservice.deleteDeliveryMan(id);
	}

	// http://localhost:8081/SpringMVC/servlet/update-del
	@PutMapping("/update-del")
	public DeliveryMan updateDel(@RequestBody DeliveryMan d) {
		return dservice.updateDeliveryMan(d);
	}

}
