package tn.esprit.spring.services;

import java.net.URL;
import java.util.List;

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.entities.Orders;



public interface IDeliveryServices {
	List <Delivery> getDeliveries();
	Delivery addDelivery(Delivery D);
	Delivery updateDelivery(Delivery D);
	void deleteDelivery(int  id);

	public String passerLivraison(Orders order);
	public void affecterLivraisonALivreur(int idLivreur,int idDelivery);
	public String geocode(String address);
	//public void affecterLivraisonALivreur(DeliveryMan Livreur);
}

