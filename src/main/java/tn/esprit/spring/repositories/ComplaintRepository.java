package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint, Integer> {

}
