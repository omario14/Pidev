package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.repositories.DeliveryManRepository;

@Service
public class DeliveryManServices implements IDeliveryManServices {
@Autowired
DeliveryManRepository drepository;
	

	@Override
	public DeliveryMan addDeliveryMan(DeliveryMan D) {
		
		return drepository.save(D);
	}

	@Override
	public DeliveryMan updateDeliveryMan(DeliveryMan D) {
		
		return drepository.save(D);
	}

	@Override
	public void deleteDeliveryMan(int id) {
		
		drepository.deleteById(id);
	}

	@Override
	public List<DeliveryMan> getDeliveryMen() {
		
		return (List<DeliveryMan>) drepository.findAll();
	}

}
