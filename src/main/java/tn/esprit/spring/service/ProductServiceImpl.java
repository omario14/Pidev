package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	ProductRepository ProductRepository;
	/**********************Creating add method that insert product into database***************/
	@Override
	public int addProduct(Product product) {
		ProductRepository.save(product);
		return product.getId();
	}
	/****************Creating update method that upgrade product from database*****************/ 
    @Override
	public void updateProduct(Product p, int idProduct) {
		Product product = ProductRepository.findById(idProduct).get();
		product.setLabel(p.getLabel());
		product.setDescription(p.getDescription());
		product.setPrice(p.getPrice());
		product.setBarCode(p.getBarCode());
		product.setImage(p.getImage());
		product.setQuantity(p.getQuantity());
		product.setWeight(p.getWeight());
		ProductRepository.save(product);
		
	}
    /*******************Creating deleting method that remove product by id  from database*********/
	@Override
	public int deleteProduct(int idProduct) {
		Product product = ProductRepository.findById(idProduct).get();
		ProductRepository.delete(product);
		return idProduct;
	}
	/***************Creating getAll method that retrieve all product from database **************/
    @Override
	public List<Product> getAllProducts() {
		return (List<Product>)ProductRepository.findAll();
	}
    /**************Creating getByid method that retrieve product detail from database************/
	@Override
	public Product getProductById(int id) {
		return ProductRepository.findById(id).get();  
	}
	/***************Creating getAll product by category method from database **************/
	@Override
	public List<Product>getProductsByCategory(String categoryName) {
		List<Product>productsList = new ArrayList<>();
		List<Product>getProducts = (List<Product>) ProductRepository.findAll();
		for(Product p : getProducts) {
			
			if(p.getCat().getLabelCat().equals(categoryName)) {
				productsList.add(p);
			}
		}
		return productsList;
	}



}
