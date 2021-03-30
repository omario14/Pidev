package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tn.esprit.spring.entities.Product;
import tn.esprit.spring.service.IProductService;

@Controller
public class MvcController {
	
	@Autowired
	IProductService ProductService;
	 @RequestMapping("/")
	    public String home() {
	        System.out.println("Going home...");
	        return "index";
	    }
	 @RequestMapping("/add_product")
	 public String showForm(Model model) {
		    Product prod = new Product();
		     
		    model.addAttribute("prod", prod);
		     
		    return "add_product";
		}
	 
	 @PostMapping("/add-product")
	 public ModelAndView addProduct(@ModelAttribute("prod") Product prod,Model model) {
		 List productlist = new ArrayList();
		    
		   // productlist = ProductService.getAllProducts(prod.getLabel());// here list size is 2
		    model.addAttribute("product", productlist);
		    return new ModelAndView("redirect:/searchbysurnameresult", "Model", productlist);
			
		}

}
