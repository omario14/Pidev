package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Publicity;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.exception.SpringVoteException;
import tn.esprit.spring.repository.PublicityRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class PublicityService {
	
	@Autowired
	PublicityRepository publicityRepository;
	@Autowired
	UserRepository userRepository;
	
private static final Logger l = LogManager.getLogger(PublicityService.class);
	
	public void ajouterPublicity(Publicity p, int idUser) {
		User user = userRepository.findById(idUser).get();
		if(user.getRole().equals(user.getRole().admin)){
		 publicityRepository.save(p);
		} else {
			throw new SpringVoteException("You've to be an admin");
		}
	}
	
	public void deletePublicity(int id, int idUser) {
		User user = userRepository.findById(idUser).get();
		if(user.getRole().equals(user.getRole().admin)){
			publicityRepository.deleteById(id);
		} else {
			throw new SpringVoteException("You've to be an admin");
		}
		
		
	}
	
	public List<Publicity> retrieveAllPublicities() {
		List<Publicity> pubs = (List<Publicity>) publicityRepository.findAll();
		for(Publicity pub : pubs) {
			l.info("pub +++ :" + pub);
		}
		return pubs;
	}
	
	public boolean validePublicity(int id){
		Publicity pub = publicityRepository.findById(id).get();
		Date d = new Date();
		
		if(pub.getDateDebut().equals(d) || pub.getDateFin().equals(d) || pub.getDateDebut().before(d) && pub.getDateFin().after(d))
			return true;
		else
			return false;
		
	}
	
	public Publicity retrievePublicityByGeneration(int idUser){
		Publicity publici = new Publicity();
		User user = userRepository.findById(idUser).get();
		List<Publicity> pubs = (List<Publicity>) publicityRepository.findAll();
		List<Publicity> pubss = new ArrayList<>();
		
		for(Publicity pub : pubs) {
			
				if(user.getAge()<20 && pub.getGeneration().equals(pub.getGeneration().KIDS) && validePublicity(pub.getId()) == true){
					pubss.add(pub);
				}
				else 
					if(user.getAge()<40 && user.getAge()>20 && pub.getGeneration().equals(pub.getGeneration().YOUTH) && validePublicity(pub.getId()) == true){
					pubss.add(pub);
					}else
						if(user.getAge()>40 && pub.getGeneration().equals(pub.getGeneration().ADULT) && validePublicity(pub.getId()) == true){
					pubss.add(pub);
				}			
		}
		
		Random r = new Random();
		int index = r.nextInt(pubss.size());
        publici = pubss.get(index);
		
		return publici;
		
	}

	public void updatePublicity(Publicity p, int idPublicity, int idUser) {
		User user = userRepository.findById(idUser).get();
		if(user.getRole().equals(user.getRole().admin)){
		Publicity publicity = publicityRepository.findById(idPublicity).get();
		publicity.setName(p.getName());
		publicity.setDateFin(p.getDateFin());
		publicityRepository.save(publicity);
		}
		else { throw new SpringVoteException("You've to be an admin");}
	}
	
	
	

}
