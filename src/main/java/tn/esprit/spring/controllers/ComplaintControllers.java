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

import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.services.IComplaintServices;

@RestController
public class ComplaintControllers {
	@Autowired
	IComplaintServices cService;
	
	// http://localhost:8081/SpringMVC/servlet/getComplaints
	
	@GetMapping("/getComplaints")
	    public List <Complaint> getComplaints(){
		List<Complaint> Compls = cService.getComplaints();
		return Compls;
	}
	
	// http://localhost:8081/SpringMVC/servlet/addComplaint
	@PostMapping("/addComplaint")
	    public Complaint addComplaint(@RequestBody Complaint c) {
		Complaint com = cService.addComplaint(c);
		return com ;
	}
	
	// http://localhost:8081/SpringMVC/servlet/update-complaint
	@PutMapping("/update-complaint")
	public Complaint updateDel(@RequestBody Complaint c) {
		return cService.updateComplaint(c);
	}
	
	// http://localhost:8081/SpringMVC/servlet/Remove-C/{C-id}
	@DeleteMapping("/Remove-C/{C-id}")
	public void deleteComplaint(@PathVariable ("C-id") int id) {
		cService.deletecomplaint(id);
	}
		

}
