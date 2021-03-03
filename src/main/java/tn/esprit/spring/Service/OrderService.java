package tn.esprit.spring.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.repository.IOrderRepository;
import tn.esprit.spring.repository.IOrderlineRepository;

@Service
public class OrderService implements IOrderService{
	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private IOrderlineRepository orderlineRepository;


	@Override
	public void addOrder(String adress, int userr_id) {
		// TODO Auto-generated method stub
		float tot_price = orderlineRepository.total();
		Date date = new Date();
		orderRepository.AddOrder(adress, date, tot_price, userr_id);
	}


	@Override
	public List<Orders> getAllOrder() {
		// TODO Auto-generated method stub
		return (List) orderRepository.findAll();
	}


	@Override
	public Optional<Orders> GetOrderByID(int id) {
		// TODO Auto-generated method stub
		return  orderRepository.findById(id);
	}


	@Override
	public List<String> getOrderByName(String name) {
		// TODO Auto-generated method stub
		return orderRepository.getOrdersByName(name) ;
	}



	


}
