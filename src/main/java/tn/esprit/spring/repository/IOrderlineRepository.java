package tn.esprit.spring.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.OrderLine;

@Transactional
@Repository
public interface IOrderlineRepository extends CrudRepository<OrderLine, Integer> {

	@Modifying
	@Query(value = "INSERT INTO order_line (prod_id, quantity,	line_price) VALUES (:prodid,:quantity,:price)", nativeQuery = true)
	void AddOrderLine(@Param("prodid") int prodid, @Param("quantity") int quantit, @Param("price") float price);
	
	
	@Modifying
	@Transactional
	@Query(value = "update order_line e SET e.quantity=:quantity where e.prod_id=:prodid", nativeQuery = true)
	void updatequantity(@Param("prodid") int prodid, @Param("quantity") int quantity);
	
	@Query(value ="SELECT x.label ,c.line_price , c.quantity FROM order_line c JOIN product x where x.id=c.prod_id", nativeQuery = true)
	List<String> getOrderline();

}
