package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Category;

@Repository
public interface ICategoryService {
	public int  addCategory(Category c);
	public void deleteCategory(int id);
	public void updateCategory(Category c,int idCategory);
	public List<Category> retrieveAllCategories();
	public Category retrieveCategoryById (String id);
	public Category findCategoryByName(String name);
	public void affecterCategoryProduct(int idp,int idc);

}
