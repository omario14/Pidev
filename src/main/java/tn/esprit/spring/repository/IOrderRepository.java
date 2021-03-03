package tn.esprit.spring.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Orders;


@Transactional
@Repository
public interface IOrderRepository extends CrudRepository<Orders, Integer> {

	
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO orders (adress, date,	tot_price,userr_id) VALUES (:adress,:date,:tot_price,:userr_id)", nativeQuery = true)
	void AddOrder(@Param("adress") String adress, @Param("date") Date date, @Param("tot_price") float tot_price,@Param("userr_id") int userr_id);
	
	@Query(value = "SELECT b.date FROM orders b JOIN User x where x.first_name LIKE %:name%", nativeQuery = true)
	List<String> getOrdersByName(@Param("name") String name);
	
}
