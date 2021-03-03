package tn.esprit.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Shop;
import tn.esprit.spring.service.IShopService;

@RestController
@RequestMapping("/Shop")
public class ShopRestController {
	
	@Autowired
	IShopService shopService;
	
	//creating a put  mapping that upgrade Shop
  	@PutMapping("/update-shop")
  	@ResponseBody
  	public ResponseEntity<String> updateShop(@RequestBody Shop shop) {
  		shopService.updateShop(shop);
  	    return new ResponseEntity<String>("Shop updated successfully",HttpStatus.OK);
  		
  	}
  	
  	//creating a get mapping that retrieves all Shop from database.
  	@GetMapping("/retrieve-shop")
  	@ResponseBody
  	public Shop  getShop() {
  		return shopService.getShop();
  	}
  	
  	@PutMapping("/affecter-shop-ray/{idr}/{ids}")
		@ResponseBody
			public String affectShopRay(@PathVariable("idr")int idr, @PathVariable("ids")int ids) {
			shopService.affecterShopRay(idr, ids);
			return "Ray affected to Shop successfully!!";
		}
}
