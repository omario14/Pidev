package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{

}
