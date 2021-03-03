package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Service.IOrderlineService;
import tn.esprit.spring.Service.OrderlineService;

@RestController
public class OrderlineController implements IOrderlineService {

	@Autowired
	OrderlineService ue;

	

	@Override
	@GetMapping("/del/orderline/{id}")
	public void deleteOrderline(@PathVariable("id") int id) {
		ue.deleteOrderline(id);
		
	}


	@Override
	@GetMapping("/add/orderline/{prodid}/{amount}/{price}")
	@ResponseBody
	public void addOrderline(@PathVariable("prodid") int prodid,@PathVariable("amount")  int amount,@PathVariable("price")  float price) {
		ue.addOrderline(prodid, amount, price*amount);
		
	}

	@Override
	@GetMapping("/up/orderline/{prodid}/{quantity}")
	@ResponseBody
	public int updateOrderline(@PathVariable("prodid") int idprod,@PathVariable("quantity") int quantity) {
		ue.updateOrderline(idprod, quantity);
		return 0;
	}


	@Override
	@GetMapping("/orderline")
	@ResponseBody
	public List<String> getOrderline() {
		return ue.getOrderline();
		
	}


	
}
