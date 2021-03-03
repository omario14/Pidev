package tn.esprit.spring.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Ray;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.RayRepository;

@Service
public class RayServiceImpl implements IRayService {
	
	@Autowired
	RayRepository rayRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	private static final Logger l = LogManager.getLogger(ProductServiceImpl.class);

	/**********************Creating add method that insert Ray into database***************/
	@Override
	public int addRay(Ray r) {
		rayRepository.save(r);
		 return r.getId();
	}
	
	/*******************Creating deleting method that remove Ray by id  from database*********/
	@Override
	public void deleteRay(int id) {
		rayRepository.deleteById(id);
		
	}
	
	/****************Creating update method that upgrade Ray from database*****************/
	@Override
	public void updateRay(Ray r, int idRay) {
		Ray ra = rayRepository.findById(idRay).get();
		ra.setType(r.getType());
		rayRepository.save(ra);
		
	}
	
	/***************Creating retrieve method that retrieve all Ray from database **************/
	@Override
	public List<Ray> retrieveAllRays() {
		List<Ray> ras = (List<Ray>) rayRepository.findAll();
		for(Ray ra : ras) {
			l.info("ray +++ :" + ra);
		}
		return ras;	}
	
	/**************Creating getByid method that retrieve Ray detail from database************/
	@Override
	public Ray retrieveRayById(int id) {
		Ray ra = rayRepository.findById(id).orElse(null);

		l.info("Ray updated: \n" + ra);
		return ra;
	}
	/**************Creating findRayByName method that retrieve Ray by Type from database************/
	@Override
	public Ray findRayByName(String name) {
		return rayRepository.findRayByName(name);
	}

	/**************Creating affecterRayCategory method that affect category to Ray ************/
	@Override
	public void affecterRayCategory(int idc, int idr) {
		Ray ra = rayRepository.findById(idr).get();
        Category cat = categoryRepository.findById(idc).get();
		cat.setRay(ra);
		categoryRepository.save(cat);
		
	}

}
