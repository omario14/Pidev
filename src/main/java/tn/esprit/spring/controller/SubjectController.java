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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.common.AuthenticationFailException;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.service.AuthenticationService;
import tn.esprit.spring.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;
	@Autowired
    AuthenticationService authenticationService;

	@PostMapping("/addsub")
	public void ajouterSubject(@RequestBody Subject s, @RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		subjectService.ajouterSubject(s, idUser);
	}
	
	@GetMapping("/getSubjects")
  	@ResponseBody
  	public List<Subject>  getSubjects(@RequestParam("token") String token) throws AuthenticationFailException {
		authenticationService.authenticate(token);
  		List<Subject> subjects = new ArrayList<>();
  		for(Subject s : subjectService.retrieveSubjects()) {
  			subjects.add(s);
  		}
  		return subjects;
  	}
	
	@DeleteMapping("/deleteSubject/{idSubject}")
  	@ResponseBody
  	public ResponseEntity<String>  deleteSubject (
  		@RequestBody Subject subject,@PathVariable("idSubject")int idSubject, @RequestParam("token") String token) throws AuthenticationFailException {
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		subjectService.deleteSubject(idSubject, idUser);
  	    return new ResponseEntity<String>("Subject deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
	
	@PutMapping("/updateSubject/{idSubject}")
  	@ResponseBody
  	public ResponseEntity<String> updateSubject(
  		@RequestBody Subject subject,@PathVariable("idSubject")int idSubject, @RequestParam("token") String token) throws AuthenticationFailException {
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		subjectService.updateSubject(subject, idSubject, idUser);
  	    return new ResponseEntity<String>("Subject updated successfully",HttpStatus.OK);
  	}
	
}
