package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Comment;
import tn.esprit.spring.entities.Subject;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query("SELECT com from Comment com WHERE com.sub =:subject")
	public Comment findCommentBySubject(@Param("subject") Subject subject);
}
