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

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {

	
	@Autowired
	 IProductService ProductService;
	
	//creating post mapping method that insert product into database
	 @PostMapping("/add-product")
	 @ResponseBody
	 public int addProduct(@RequestBody Product p) {
		    ProductService.addProduct(p);
			return p.getId();
			
		}
	//creating put mapping that updates the product detail  
	 @PutMapping("/update-product/{idProduct}")
	 @ResponseBody
		public int updateProduct(
			@RequestBody Product product,@PathVariable("idProduct")int idProduct) {
		    ProductService.updateProduct(product,idProduct);
		    return product.getId();
			
		}
	  //creating a delete mapping that delete data from database
		@DeleteMapping("/delete-product/{idProduct}")
		@ResponseBody
		public ResponseEntity<String>  deleteProduct(
			@RequestBody Product product,@PathVariable("idProduct")int idProduct) {
			ProductService.deleteProduct(idProduct);
		    return new ResponseEntity<String>("Category deleted successfully",HttpStatus.ACCEPTED);
			
		}
		//creating a get mapping that retrieves all the product detail from the database   
		@GetMapping("/get-all-product")
		@ResponseBody
		public List<Product>  getAllProduct() {
			List<Product> product = new ArrayList<>();
			for(Product p : ProductService.getAllProducts()) {
				product.add(p);
			}
			return product;
		}
		//creating a get mapping that retrieves a specific product
		@GetMapping("/get-productbyId/{idp}")
		@ResponseBody
		public Product getProductById(@PathVariable("idp")int idp) {
			
			return ProductService.getProductById(idp);
		}
		
		//creating a get mapping that retrieves a specific product
		@GetMapping("/get-productbyCategory/{category}")
		@ResponseBody
		public List<Product> getProductById(@PathVariable("category")String categoryName) {
			
			return ProductService.getProductsByCategory(categoryName);
		}
		 
		
}
