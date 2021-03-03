package tn.esprit.spring.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Service.IOrderService;
import tn.esprit.spring.Service.OrderService;
import tn.esprit.spring.entities.Orders;

@RestController
public class OrderController  implements IOrderService{
	@Autowired
	OrderService ue;

	@Override
	@GetMapping("/add/order/{adress}/{userr_id}")
	@ResponseBody
	public void addOrder(@PathVariable("adress") String adress,@PathVariable("userr_id") int userr_id) {
		ue.addOrder(adress, userr_id);
		
	}

	@Override
	@GetMapping("/allorders")
	public List<Orders> getAllOrder() {
		// TODO Auto-generated method stub
		return ue.getAllOrder();
	}

	@GetMapping("/ordername/{name}")
	@Override
	public List<String> getOrderByName(@PathVariable("name") String name) {
		// TODO Auto-generated method stub
		return ue.getOrderByName(name);
	}
	
	@GetMapping("/order/{id}")
	@Override
	public Optional<Orders> GetOrderByID(@PathVariable("id") int id) {
		// TODO Auto-generated method stub
		return ue.GetOrderByID(id);
	}


	
}
