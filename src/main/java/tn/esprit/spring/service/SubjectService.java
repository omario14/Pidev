package tn.esprit.spring.service;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.repository.CommentRepository;
import tn.esprit.spring.repository.SubjectRepository;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	CommentRepository commentRepository;
	
	
	private static final Logger l = LogManager.getLogger(SubjectService.class);
	
	public int ajouterSubject(Subject s) {
		
		return subjectRepository.save(s).getId();
	}
	
	public void affecterCommentASubject(int commentId, int subjectId) {
		Comment comment = commentRepository.findById(commentId).get();
		Subject subject = subjectRepository.findById(subjectId).get();
		if (ObjectUtils.isEmpty(comment) && !ObjectUtils.isEmpty(subject))
			subject.getComments().add(comment);
			//departement.getEmploye().add(employe);
	}
	
	public void deleteSubject(int id) {
		subjectRepository.deleteById(id);
		
	}
	
	public List<Subject> retrieveAllSubjects() {
		List<Subject> subs = (List<Subject>) subjectRepository.findAll();
		for(Subject sub : subs) {
			l.info("sub +++ :" + sub);
		}
		return subs;
	}

	public void updateSubject(Subject s, int idSubject) {
		Subject subject = subjectRepository.findById(idSubject).get();
		subject.setTitle(s.getTitle());
		subject.setDescription(s.getDescription());
		subjectRepository.save(subject);
	}
	

}
