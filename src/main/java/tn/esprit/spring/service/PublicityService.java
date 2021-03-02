package tn.esprit.spring.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Publicity;
import tn.esprit.spring.repository.PublicityRepository;

@Service
public class PublicityService {
	
	@Autowired
	PublicityRepository publicityRepository;
	
private static final Logger l = LogManager.getLogger(PublicityService.class);
	
	public int ajouterPublicity(Publicity p) {
		
		return publicityRepository.save(p).getId();
	}
	
	public void deletePublicity(int id) {
		publicityRepository.deleteById(id);
		
	}
	
	public List<Publicity> retrieveAllPublicities() {
		List<Publicity> pubs = (List<Publicity>) publicityRepository.findAll();
		for(Publicity pub : pubs) {
			l.info("pub +++ :" + pub);
		}
		return pubs;
	}

	public void updatePublicity(Publicity p, int idPublicity) {
		Publicity publicity = publicityRepository.findById(idPublicity).get();
		publicity.setName(p.getName());
		publicity.setDateFin(p.getDateFin());
		publicityRepository.save(publicity);
	}
	

}
