package tn.esprit.spring.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.User;

public interface UserRepository extends CrudRepository<User,Integer> {
	List<User> findAll();

}
