package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer>{

}
