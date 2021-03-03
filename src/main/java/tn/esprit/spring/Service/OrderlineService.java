package tn.esprit.spring.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.IOrderlineRepository;

@Service
public class OrderlineService implements IOrderlineService {
	
	@Autowired
	private IOrderlineRepository orderlineRepository;
	


	@Override
	public void addOrderline(int prodid, int amount , float price) {
		orderlineRepository.AddOrderLine(prodid, amount, price);
		
	}

	@Override
	public void deleteOrderline(int id) {
		orderlineRepository.deleteById(id);
		
	}

	@Override
	public int updateOrderline(int idprod , int quantity) {
		orderlineRepository.updatequantity(idprod, quantity);
		return 0;
	}

	@Override
	public List<String> getOrderline() {
		return orderlineRepository.getOrderline();
		
	}

	
	public float totale() {
		 return orderlineRepository.total();
	}

}
