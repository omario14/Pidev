package tn.esprit.spring.controller;

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

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/addComment")
	public int ajouterComment(@RequestBody Comment c) {
		return commentService.ajouterComment(c);
	}
	
	@DeleteMapping("/deleteComment/{idComment}")
  	@ResponseBody
  	public ResponseEntity<String> deleteComment(
  		@RequestBody Comment comment,@PathVariable("idComment")int idComment) {
		commentService.deleteComment(idComment);
  	    return new ResponseEntity<String>("Comment deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
	
	@PutMapping("/updateComment/{idComment}")
  	@ResponseBody
  	public ResponseEntity<String> updateComment(
  		@RequestBody Comment comment,@PathVariable("idComment")int idComment) {
  		commentService.updateComment(comment, idComment);
  	    return new ResponseEntity<String>("Comment updated successfully",HttpStatus.OK);
  		
  	}
	
	@GetMapping("/findCommentBySubject/{subject}")
	@ResponseBody
	public Comment findCommentBySubject(@PathVariable("subject") Subject subject) {
		return commentService.findCommentBySubject(subject);
	}

}
