package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.DeliveryMan;

public interface DeliveryManRepository extends CrudRepository<DeliveryMan, Integer> {
	List<DeliveryMan> getByStateLivreurTrue();

}
