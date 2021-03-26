package tn.esprit.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.dto.order.OrderItemsDto;
import tn.esprit.spring.entities.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
	
	
	
	@Query("select o from OrderItem o where o.order.id=:orderid")
	List<OrderItem> findAllByOrderIdOrderItem(@Param("orderid") Integer orderid);
}
