package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Delivery;

public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {

}
