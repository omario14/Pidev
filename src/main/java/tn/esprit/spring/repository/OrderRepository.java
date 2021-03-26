package tn.esprit.spring.repository;

import tn.esprit.spring.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findAllByUserIdOrderByCreatedDateDesc(Integer userId);

}
