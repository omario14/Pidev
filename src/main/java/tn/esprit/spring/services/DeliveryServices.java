package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.repositories.DeliveryRepository;
@Service
public class DeliveryServices implements IDeliveryServices {
	@Autowired
	DeliveryRepository delrepository;

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

	

}
