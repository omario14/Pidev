package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Ray;
import tn.esprit.spring.entities.Shop;
import tn.esprit.spring.repository.RayRepository;
import tn.esprit.spring.repository.ShopRepository;

@Service
public class ShopServiceImpl implements IShopService{

	@Autowired
	ShopRepository shopRepository;
	@Autowired
	RayRepository rayRepository;
	
	@Override
	public void updateShop(Shop s) {
		 shopRepository.save(s);
		
	}

	@Override
	public Shop getShop() {
		return shopRepository.findShop();
	}
	
	@Override
	public void affecterShopRay(int idr, int ids) {
		Shop shop = shopRepository.findById(ids).get();
        Ray ray = rayRepository.findById(idr).get();
		ray.setShop(shop);
		rayRepository.save(ray);
		
	}

	@Override
	public void disaffectShopRay(int idr) {
		shopRepository.disaffectShopRay(idr);
		
		
	}

}
