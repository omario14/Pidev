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

import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	SubjectService subjectService;

	@PostMapping("/addsub")
	public int ajouterSubject(@RequestBody Subject s) {
		return subjectService.ajouterSubject(s);
	}
	
	/*@PutMapping(value = "/affecterCommentASubject/{idcom}/{iddept}")
	public void affecterCommentASubject(@PathVariable("idcom") int commentId, @PathVariable("idsub") int subjectId) {
		subjectService.affecterCommentASubject(commentId, subjectId);
		//employeService.affecterEmployeADepartement(employeId, depId);

	}*/
	
	@GetMapping("/getAllSubjects")
  	@ResponseBody
  	public List<Subject>  getAllSubjects() {
  		List<Subject> subjects = new ArrayList<>();
  		for(Subject s : subjectService.retrieveAllSubjects()) {
  			subjects.add(s);
  		}
  		return subjects;
  	}
	
	@DeleteMapping("/deleteSubject/{idSubject}")
  	@ResponseBody
  	public ResponseEntity<String>  deleteSubject(
  		@RequestBody Subject subject,@PathVariable("idSubject")int idSubject) {
		subjectService.deleteSubject(idSubject);
  	    return new ResponseEntity<String>("Subject deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
	
	@PutMapping("/updateSubject/{idSubject}")
  	@ResponseBody
  	public ResponseEntity<String> updateSubject(
  		@RequestBody Subject subject,@PathVariable("idSubject")int idSubject) {
		subjectService.updateSubject(subject, idSubject);
  	    return new ResponseEntity<String>("Subject updated successfully",HttpStatus.OK);
  	}
	
}
