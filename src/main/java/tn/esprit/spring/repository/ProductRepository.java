package tn.esprit.spring.repository;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
