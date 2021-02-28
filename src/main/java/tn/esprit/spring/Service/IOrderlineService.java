package tn.esprit.spring.Service;

import java.util.List;

public interface IOrderlineService {
	
	public void addOrderline(int prodid,int amount,float price);
	public void deleteOrderline(int id);
	public int updateOrderline(int prodid,int quantity);
	public List<String> getOrderline();

}
