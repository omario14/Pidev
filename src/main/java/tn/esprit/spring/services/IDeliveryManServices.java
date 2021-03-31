package tn.esprit.spring.services;

import java.util.List;
import tn.esprit.spring.entities.DeliveryMan;

public interface IDeliveryManServices {
	List <DeliveryMan> getDeliveryMen();
	List <DeliveryMan> getActiveDeliveryMen();
	DeliveryMan addDeliveryMan(DeliveryMan D);
	DeliveryMan updateDeliveryMan(DeliveryMan D);
	void deleteDeliveryMan(int  id);

}
