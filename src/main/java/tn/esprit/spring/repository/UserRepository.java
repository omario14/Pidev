package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByuserName(String userName);
	
    List<User> findAll();

    User findByEmail(String email);

    User findUserByEmail(String email);
}
