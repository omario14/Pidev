package tn.esprit.spring.service;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.entities.User;
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
	
	public int ajouterComment(Comment c) {
		return commentRepository.save(c).getId();
	}
	
	public void deleteComment(int id) {
		commentRepository.deleteById(id);
	}
	
	
	public void updateComment(Comment c, int idComment) {
		Comment comment = commentRepository.findById(idComment).get();
		comment.setDescription(c.getDescription());
		commentRepository.save(comment);		
	}
	

	public Comment findCommentBySubject(Subject subject) {
		return commentRepository.findCommentBySubject(subject);		
	}
	
	public void addCommentToSubject(Comment comment,int idSubject, int idUser) {
		
		Subject subject = subjectRepository.findById(idSubject).get();
		
		User user = userRepository.findById(idUser).get();
		
		comment.setSub(subject);
		comment.setUser(user);
		
		commentRepository.save(comment);		
	}
}
