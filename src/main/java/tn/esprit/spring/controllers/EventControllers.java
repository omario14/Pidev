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
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.services.IEventServices;

@RestController
public class EventControllers {
	@Autowired
	IEventServices eService;
	
	// http://localhost:8081/SpringMVC/servlet/getEvents
		@GetMapping("/getEvents")
		public List <Event> getEvents(){
		List <Event> Elist = eService.getEvents();
			return Elist;
			}
		// http://localhost:8081/SpringMVC/servlet/addEvent
		@PostMapping("/Event")
		public Event addEvent(@RequestBody Event E) {
			Event Ev = eService.addEvent(E);
			return Ev ;
	}
		// http://localhost:8081/SpringMVC/servlet/Remove-Event/{Event-id}
		@DeleteMapping("/Remove-Event/{Event-id}")
		public void deleteEvent(@PathVariable ("Event-id") int id) {
			eService.deleteEvent(id);
		}

		// http://localhost:8081/SpringMVC/servlet/update-Event
		@PutMapping("/update-Event")
		public Event updateEvent(@RequestBody Event E) {
			return eService.updateEvent(E);
		}


}
