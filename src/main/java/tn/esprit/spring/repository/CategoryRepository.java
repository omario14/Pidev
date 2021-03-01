package tn.esprit.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	/**********************************Update category ***************************/
	@Transactional
	@Modifying
	@Query("UPDATE Category  c SET c.labelCat = :name WHERE c.id =:id")
	public void updateCateogry(@Param("name")String name,@Param("id")int id);
	/**********************************Find category ByName***************************/
	@Query("SELECT cat from Category cat WHERE cat.labelCat =:name")
	public Category findCategoryByName(@Param("name")String name);
}
