package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.common.AuthenticationFailException;
import tn.esprit.spring.entities.Vote;
import tn.esprit.spring.service.AuthenticationService;
import tn.esprit.spring.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	VoteService voteService;
	@Autowired
    AuthenticationService authenticationService;
	
	
	@PostMapping("/addVote/{idSubject}")
	public void addVote(@RequestBody Vote vote, @PathVariable int idSubject, @RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		voteService.addVote(vote, idUser, idSubject);
	}
	
	

}
