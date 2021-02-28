package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Orders;

public interface IOrderRepository extends CrudRepository<Orders, Integer> {

}
