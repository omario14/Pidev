package tn.esprit.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Shop;

public interface ShopRepository extends CrudRepository<Shop, Integer> {
	@Query("SELECT s from Shop s ")
	public Shop findShop();
	
	@Transactional
	@Modifying
	@Query("UPDATE Ray  r SET r.shop = null WHERE r.id =:id")
	public void disaffectShopRay(@Param("id")int id);
}
