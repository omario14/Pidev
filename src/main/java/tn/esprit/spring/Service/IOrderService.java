package tn.esprit.spring.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Orders;

public interface IOrderService {
	public void addOrder(String adress,int userr_id);
	public List<Orders> getAllOrder();
	public List<String> getOrderByName(String name);
	public  Optional<Orders> GetOrderByID(int id);

}
