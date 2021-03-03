package tn.esprit.spring.service;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Shop;

@Repository
public interface IShopService {
	
	public void updateShop(Shop s);
	public Shop getShop();
	public void affecterShopRay(int idr, int ids);

}
