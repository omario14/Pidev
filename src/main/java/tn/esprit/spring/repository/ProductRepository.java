package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import tn.esprit.spring.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>,PagingAndSortingRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.label LIKE %?1% ")
	public List<Product> findAll (String keyword);
	
	@Query("SELECT p FROM Product p ORDER BY p.cat.popularCat DSC")
	public List<Product> findSortedByCat ();
	

}
