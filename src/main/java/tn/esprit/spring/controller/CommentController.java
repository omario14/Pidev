package tn.esprit.spring.controller;

import java.io.IOException;

import javax.transaction.Transactional;

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
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.service.AuthenticationService;
import tn.esprit.spring.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
    AuthenticationService authenticationService;

	
	

	@PostMapping("/addComment/{idSubject}")
	public void addComment(@RequestBody Comment comment, @RequestParam("token") String token, @PathVariable int idSubject) throws IOException, AuthenticationFailException
	{	
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		commentService.addCommentToSubject(comment, idSubject, idUser);							
	}
	

	@DeleteMapping("/deleteComment/{idComment}")
  	@ResponseBody
  	public ResponseEntity<String> deleteComment(@RequestBody Comment comment, @PathVariable("idComment")int idComment, @RequestParam("token") String token) throws AuthenticationFailException {
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		commentService.deleteComment(idComment, idUser);
  	    return new ResponseEntity<String>("Comment deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
	
	@PutMapping("/updateComment/{idComment}")
  	@ResponseBody
  	public ResponseEntity<String> updateComment(
  		@RequestBody Comment comment,@PathVariable("idComment")int idComment, @RequestParam("token") String token) throws AuthenticationFailException {
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		commentService.updateComment(comment, idComment, idUser);
  	    return new ResponseEntity<String>("Comment updated successfully",HttpStatus.OK);
  		
  	}
	
	@GetMapping("/findCommentBySubject/{subject}")
	@ResponseBody
	public Comment findCommentBySubject(@PathVariable("subject") Subject subject) {
		return commentService.findCommentBySubject(subject);
	}

}
