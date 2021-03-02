package tn.esprit.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Ray;

public interface RayRepository  extends CrudRepository<Ray, Integer>{

	
	/**********************************Update category ***************************/
	@Transactional
	@Modifying
	@Query("UPDATE Ray  r SET r.type = :name WHERE r.id =:id")
	public void updateRay(@Param("name")String name,@Param("id")int id);
	/**********************************Find category ByName***************************/
	@Query("SELECT ra from Ray ra WHERE ra.type =:name")
	public Ray findRayByName(@Param("name")String name);
}
