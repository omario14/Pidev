package tn.esprit.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Vote;
import tn.esprit.spring.entities.VoteType;
import tn.esprit.spring.exception.SpringVoteException;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	VoteRepository voteRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	UserRepository userRepository;
	
	
	public void addVote(Vote vote, int idUser, int idSubject){
		User user = userRepository.findById(idUser).get();
		Subject subject = subjectRepository.findById(idSubject).get();
		List<Vote> votes = voteRepository.findAll();
		for(Vote votee : votes) {
			if(votee.getUser().getId() == idUser && votee.getSubject().getId() == idSubject ){
				if(votee.getVoteType().equals(vote.getVoteType())){
					if(vote.getVoteType().equals(vote.getVoteType().UPVOTE))
					{
						subject.setVoteCount(subject.getVoteCount() - 1);
						voteRepository.deleteById(votee.getId());
						throw new SpringVoteException("You have already "
			                    + vote.getVoteType() + "'d for this post");
					} else if(vote.getVoteType().equals(vote.getVoteType().DOWNVOTE)) {
						subject.setVoteCount(subject.getVoteCount() + 1);
						voteRepository.deleteById(votee.getId());
						throw new SpringVoteException("You have already "
			                    + vote.getVoteType() + "'d for this post");
					}					
				}
				else if (votee.getVoteType().equals(vote.getVoteType()) ==! true){
					if(votee.getVoteType().equals(votee.getVoteType().DOWNVOTE)){
						subject.setVoteCount(subject.getVoteCount() + 1);
						voteRepository.deleteById(votee.getId());
						throw new SpringVoteException("You have already "
			                    + vote.getVoteType() + "'d for this post");
					}
					else if (votee.getVoteType().equals(votee.getVoteType().UPVOTE)){
						subject.setVoteCount(subject.getVoteCount() - 1);
						voteRepository.deleteById(votee.getId());
						throw new SpringVoteException("You have already "
			                    + vote.getVoteType() + "'d for this post");
					}
				}
			}
		}
		if(vote.getVoteType().equals(vote.getVoteType().UPVOTE)){
			subject.setVoteCount(subject.getVoteCount() + 1);
		} else {
			subject.setVoteCount(subject.getVoteCount() - 1);
		}
		
		vote.setSubject(subject);
		vote.setUser(user);
		
		voteRepository.save(vote);
		subjectRepository.save(subject);
	}
}
