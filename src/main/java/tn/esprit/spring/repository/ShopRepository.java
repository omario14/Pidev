package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Shop;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
	@Query("SELECT s from Shop s ")
	public Shop findShop();
}
