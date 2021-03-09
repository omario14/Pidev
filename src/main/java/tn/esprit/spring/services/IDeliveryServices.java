package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Delivery;



public interface IDeliveryServices {
	List <Delivery> getDeliveries();
	Delivery addDelivery(Delivery D);
	Delivery updateDelivery(Delivery D);
	void deleteDelivery(int  id);

	public String passerLivraison(Delivery delivery);
	public void affecterLivraisonALivreur(int idLivreur,int idDelivery);
}

