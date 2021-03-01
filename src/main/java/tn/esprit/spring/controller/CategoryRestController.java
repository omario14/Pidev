package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.service.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryRestController {
	
	@Autowired
	ICategoryService categoryService;
	
	//creating a post mapping that add category in database
    @PostMapping("/add-category")
	@ResponseBody
	public int  addCategory(@RequestBody Category c) {
    	categoryService.addCategory(c);
		return c.getId();
    }
    
  //creating a put  mapping that upgrade category
  	@PutMapping("/update-category/{idCategory}")
  	@ResponseBody
  	public ResponseEntity<String> updateCategory(
  		@RequestBody Category category,@PathVariable("idCategory")int idCategory) {
  		categoryService.updateCategory(category,idCategory);
  	    return new ResponseEntity<String>("Category updated successfully",HttpStatus.OK);
  		
  	}
  	
  //creating a delete mapping that delete category from database
  	@DeleteMapping("/delete-category/{idCategory}")
  	@ResponseBody
  	public ResponseEntity<String>  deleteCategory(
  		@RequestBody Category category,@PathVariable("idCategory")int idCategory) {
  		categoryService.deleteCategory(idCategory);
  	    return new ResponseEntity<String>("Category deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
  	
  	//creating a get mapping that retrieves all categories from database.
  	@GetMapping("/get-all-category")
  	@ResponseBody
  	public List<Category>  getAllCategories() {
  		List<Category> categories = new ArrayList<>();
  		for(Category c : categoryService.retrieveAllCategories()) {
  			categories.add(c);
  			
  		}
  		
  		return categories;
  	}
  	
  	//creating a get mapping that retrieves all categories from database.
  	 @GetMapping("/find-category-byid/{id}")
  	 @ResponseBody
  		public Category findCategoryById(@PathVariable("id")int id) {
  			return categoryService.retrieveCategoryById(id);
  		}
  	 
  	//creating a get mapping that retrieves all categories from database.
  		 @GetMapping("/find-category-byname/{name}")
  		 @ResponseBody
  			public Category findCategoryByName(@PathVariable("name")String name) {
  				return categoryService.findCategoryByName(name);
  			}
  		 
  		@PutMapping("/affecter-category-product/{idp}/{idc}")
  		@ResponseBody
  			public String affectCategoryProduct(@PathVariable("idp")int idp, @PathVariable("idc")int idc) {
  			categoryService.affecterCategoryProduct(idp, idc);
  			return "Category affected to Product successfully!!";
  		}
  		

}
