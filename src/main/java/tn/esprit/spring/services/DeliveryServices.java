package tn.esprit.spring.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.repositories.DeliveryManRepository;
import tn.esprit.spring.repositories.DeliveryRepository;



@Service
public class DeliveryServices implements IDeliveryServices {
	
	@Autowired
	DeliveryRepository delrepository;
	
	//@Autowired
	//CommandeManRepository commandeRepository;
	
	@Autowired
	DeliveryManRepository livreurRepository;

	@Override
	public List<Delivery> getDeliveries() {
		
		return (List<Delivery>) delrepository.findAll();
	}

	@Override
	public Delivery addDelivery(Delivery D) {
		
		return delrepository.save(D);
	}

	@Override
	public Delivery updateDelivery(Delivery D) {
		
		return delrepository.save(D);
	}

	@Override
	public void deleteDelivery(int id) {
		
		delrepository.deleteById(id);
		
	}

	@Override
	public String passerLivraison(Delivery delivery) {
		
		delrepository.save(delivery);
		
		return "delivery went successfully";
	}

	@Override
	public void affecterLivraisonALivreur(int idLivreur, int idDelivery) {
		DeliveryMan livreurManage = livreurRepository.findById(idLivreur).get();
		Delivery DeliveryManage = delrepository.findById(idDelivery).get();
		
		
		
	if(livreurManage.getStateLivreur().equals(livreurManage.getStateLivreur().InActive)){
	
		 System.out.println("Ce livreur est non disponible pour le momment");
		
	}
	else 
		livreurManage.setDeliveryNbre(livreurManage.getDeliveryNbre()+1);
	livreurManage.setStateLivreur(livreurManage.getStateLivreur().InActive);
	DeliveryManage.setDelMan(livreurManage);
		
	}

	

}
