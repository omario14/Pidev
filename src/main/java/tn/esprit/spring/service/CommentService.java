package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.BigWord;
import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.exception.SpringVoteException;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.repository.UserRepository;;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SubjectRepository subjectRepository;
	
	//private static final Logger l = LogManager.getLogger(CommentService.class);
	
	public void addCommentToSubject(Comment comment,int idSubject, int idUser) {
		
		Subject subject = subjectRepository.findById(idSubject).get();
		
		List<String> bigWords = new ArrayList<>();
		for (BigWord big : BigWord.values()) { 
			if(comment.getDescription().contains(big.toString()))
			{  bigWords.add(comment.getDescription());
			}
		}
		if(bigWords.size() == 0){
			User user = userRepository.findById(idUser).get();
			Date d = new Date();
			comment.setDate(d);
			comment.setSub(subject);
			comment.setUser(user);
			
			commentRepository.save(comment);	
		} else {
			throw new SpringVoteException("You've written big words");
		}
				
	}
	
	public void deleteComment(int idComment, int idUser) {
		User user = userRepository.findById(idUser).get();
		Comment comment = commentRepository.findById(idComment).get();
		if (user.getId() == comment.getUser().getId() && user.getRole().equals(user.getRole().user) || user.getRole().equals(user.getRole().admin)){
			commentRepository.deleteById(idComment);
		} else {
			throw new SpringVoteException("You can't");
		}
		
	}
	
	
	public void updateComment(Comment c, int idComment, int idUser) {
		User user = userRepository.findById(idUser).get();
		Comment comment = commentRepository.findById(idComment).get();
		if (user.getId() == comment.getUser().getId()){
			comment.setDescription(c.getDescription());
			commentRepository.save(comment);
		} else {
			throw new SpringVoteException("You can't");
		}		
	}
	

	public Comment findCommentBySubject(Subject subject) {
		return commentRepository.findCommentBySubject(subject);		
	}
	
	
}
