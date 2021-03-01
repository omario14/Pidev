package tn.esprit.spring.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ProductRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ProductRepository ProductRepository;
	
	private static final Logger l = LogManager.getLogger(ProductServiceImpl.class);


	/**********************Creating add method that insert category into database***************/
    @Override
	public int addCategory(Category c) {
		categoryRepository.save(c);
		 return c.getId();
	}
    
    /*******************Creating deleting method that remove category by id  from database*********/
	@Override
	public void deleteCategory(int id) {
		categoryRepository.deleteById(id);
		
	}
	
	/****************Creating update method that upgrade category from database*****************/
	@Override
	public void updateCategory(Category c, int idCategory) {
		Category category = categoryRepository.findById(idCategory).get();
		category.setLabelCat(c.getLabelCat());
		categoryRepository.save(category);
		
	}
	
	/***************Creating retrieve method that retrieve all category from database **************/
	@Override
	public List<Category> retrieveAllCategories() {
		List<Category> cats = (List<Category>) categoryRepository.findAll();
		for(Category cat : cats) {
			l.info("cat +++ :" + cat);
		}
		return cats;
	}


	/**************Creating getByid method that retrieve category detail from database************/
	@Override
	public Category retrieveCategoryById(int id) {
		Category cat = categoryRepository.findById(id).orElse(null);
		l.info("Category updated: \n" + cat);
		return cat;
	}
	
	/**************Creating getByname method that retrieve category by name from database************/
	@Override
	public Category findCategoryByName(String name) {
		return categoryRepository.findCategoryByName(name);
	}

	@Override
	public void affecterCategoryProduct(int idp, int idc) {
		Category category = categoryRepository.findById(idc).get();
        Product product = ProductRepository.findById(idp).get();
		product.setCat(category);
		ProductRepository.save(product);
		
	}
	



}
