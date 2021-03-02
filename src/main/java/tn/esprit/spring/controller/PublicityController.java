package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Publicity;
import tn.esprit.spring.service.PublicityService;

@RestController
@RequestMapping("/publicity")
public class PublicityController {
	
	@Autowired
	PublicityService publicityService;
	
	@PostMapping("/addpub")
	public int ajouterPublicity(@RequestBody Publicity p) {
		return publicityService.ajouterPublicity(p);
	}
	
	
	
	@GetMapping("/getAllPublicities")
  	@ResponseBody
  	public List<Publicity>  getAllPublicities() {
  		List<Publicity> publicities = new ArrayList<>();
  		for(Publicity p : publicityService.retrieveAllPublicities()) {
  			publicities.add(p);
  		}
  		return publicities;
  	}
	
	@DeleteMapping("/deletePublicity/{idPublicity}")
  	@ResponseBody
  	public ResponseEntity<String>  deletePublicity(
  		@RequestBody Publicity publicity,@PathVariable("idPublicity")int idPublicity) {
		publicityService.deletePublicity(idPublicity);
  	    return new ResponseEntity<String>("Publicity deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
	
	@PutMapping("/updatePublicity/{idPublicity}")
  	@ResponseBody
  	public ResponseEntity<String> updatePublicity(
  		@RequestBody Publicity publicity,@PathVariable("idPublicity")int idPublicity) {
		publicityService.updatePublicity(publicity, idPublicity);
  	    return new ResponseEntity<String>("Publicity updated successfully",HttpStatus.OK);
  	}

}
