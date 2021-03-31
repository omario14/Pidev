package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.BigWord;
import tn.esprit.spring.entities.NotificationEmail;
import tn.esprit.spring.entities.Subject;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.exception.SpringVoteException;
import tn.esprit.spring.repository.SubjectRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class SubjectService {
	
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailService mailService;
	
	
	private static final Logger l = LogManager.getLogger(SubjectService.class);
	
	public void ajouterSubject(Subject s, int idUser) {
		User user = userRepository.findById(idUser).get();
		if (user.getRole().equals(user.getRole().admin) || (user.getRole().equals(user.getRole().user))){
		List<String> bigWords = new ArrayList<>();
		for (BigWord big : BigWord.values()) { 
			if(s.getDescription().contains(big.toString()))
			{  bigWords.add(s.getDescription());
			}
		}
		if(bigWords.size() == 0 && redundantSubject(s)==true)
		{
			
			Date d = new Date();
			s.setDate(d);
			s.setUser(user);
			subjectRepository.save(s);
			mailService.sendMail(new NotificationEmail("You have create a new post", s.getUser().getEmail(), s.getDescription()));
		}
		else
		{
			throw new SpringVoteException("You've written big words or you've written a redundant subject");
			
		}
		}
		
		else { 	throw new SpringVoteException("You've to authenticate");}
	}
	

	
	public void deleteSubject(int idSubject, int idUser) {
		User user = userRepository.findById(idUser).get();
		Subject subject = subjectRepository.findById(idSubject).get();
		if (user.getId() == subject.getUser().getId() && user.getRole().equals(user.getRole().user) || user.getRole().equals(user.getRole().admin))
		{
		subjectRepository.deleteById(idSubject);
		}
		else {
			throw new SpringVoteException("You've to authenticate");
		}
		
	}
	
	public List<Subject> retrieveSubjects() {
		List<Subject> subs = subjectRepository.findAll();
		List<Subject> subss = new ArrayList<>();
		
		Date d = new Date(System.currentTimeMillis()-3*24*60*60*1000);
		Date dd = new Date();
        
		for(Subject sub : subs) {
			if(sub.getDate().before(dd) && sub.getDate().after(d) || sub.getDate().equals(d) || sub.getDate().equals(dd))
			{
				subss.add(sub);
			}
			l.info("sub +++ :" + sub);
		}
		return subss;
	}

	public void updateSubject(Subject s, int idSubject, int idUser) {
		User user = userRepository.findById(idUser).get();
		Subject subject = subjectRepository.findById(idSubject).get();
		if (user.getId() == subject.getUser().getId()){
			subject.setTitle(s.getTitle());
			subject.setDescription(s.getDescription());
			subjectRepository.save(subject);
		}
		else
			throw new SpringVoteException("You've to authenticate");
		
	}
	
	public boolean redundantSubject(Subject s){
	List<Subject> subs = (List<Subject>) subjectRepository.findAll();
	for(Subject sub : subs) {
		if(sub.getDescription().equals(s.getDescription()))
			return false;
	}
	return true;
	}
}
