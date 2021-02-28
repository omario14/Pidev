package tn.esprit.spring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.IOrderRepository;

@Service
public class OrderService implements IOrderService{
	@Autowired
	private IOrderRepository orderRepository;
}
