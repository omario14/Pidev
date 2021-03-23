package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Vote;
import tn.esprit.spring.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	/*@PostMapping("/addVote/{idSubject}/{idUser}")
    public ResponseEntity<Void> vote(@RequestBody Vote vote, @PathVariable int idSubject, @PathVariable int idUser) {
        voteService.vote(vote, idSubject, idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	*/
	@PostMapping("/addVote/{idSubject}/{idUser}")
	public void addVote(@RequestBody Vote vote, @PathVariable int idSubject, @PathVariable int idUser){
		voteService.addVote(vote, idUser, idSubject);
	}
	
	

}
