package tn.esprit.spring.service;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import tn.esprit.spring.entities.Category;
import tn.esprit.spring.entities.EmailCfg;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.CategoryRepository;
import tn.esprit.spring.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	ProductRepository ProductRepository;
	@Autowired
	CategoryRepository categoryRepository;
	
	
	private final static String ACCOUND_SID =  "ACd4a4fcbce1c8ff35cec974dc6e440fd6";
	private final static String AUTH_ID= "d6cabc05c80affae16acdd82f9d56a8e" ;
	private final static String TWILIO_NUMBER= "14243873030";
	
	private EmailCfg emailCfg;
	
	
	public ProductServiceImpl(EmailCfg emailCfg) {
		this.emailCfg = emailCfg;
	}
	
	/**********************Test Method***************/

	@Override
	public int checkExpirationDate(Product p) {

		int ok=0;
		Date date = new Date();
		long y = (p.getExpirationDate().getYear()- date.getYear());
		long m = (p.getExpirationDate().getMonth()- date.getMonth());
		long d = (p.getExpirationDate().getDay()- date.getDay());
		String str = Long.toString(y);
		//System.out.print(str+" year ");
		str= str.concat(Long.toString(m));
		//System.out.print(str+" months ");
		str = str.concat(Long.toString(d));
		/*System.out.print(str+"  ");
		System.out.println(p.getLabel()+" : "+str.compareTo("004"));*/
		ok=str.compareTo("004");
		
			
		
		return ok;
		
	}
	@Override
	public boolean checkBarCode(String s) {
			if (!s.matches("^619[0-9]*$")) {
				return false;
				}
			
		return true;
	}
	
	
	/**********************Creating add method that insert product into database***************/

	@Override
	public int addProduct(Product p) {
		Twilio.init(ACCOUND_SID,AUTH_ID);
		
		Message message;
		float totale =0;
		float newCollsAmount=0;
		
		/*Product p=new Product();
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setLabel(label);
		p.setPrice(price);
		p.setQuantity(quantity);
		p.setDescription(description);
		p.setWeight(weight);
		p.setBarCode(barCode);*/
try {
			
			String path = "C:\\Barecode\\barecode.jpg";
			
			BufferedImage bf = ImageIO.read(new FileInputStream(path));
			
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
					new BufferedImageLuminanceSource(bf)));
			
			Result result = new MultiFormatReader().decode(bitmap);
			
			if (checkBarCode(result.getText())==true) {
				p.setBarCode(result.getText());
				
				ProductRepository.save(p);
				
				Message.creator(
						new PhoneNumber("+21651589453"),
						new PhoneNumber(TWILIO_NUMBER),
						"Hello Mr Test New Product is added").create();
						

			}else {
				System.out.print("Invalid BarCode "+result.getText());
			}
			
		} catch(Exception e) {
			System.out.println("Error while reading barcode " + e.getMessage());
		}
		
		return p.getId();
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
	public List<Product> getAllProducts(String keyword) {
		/*List<Product>getProducts = (List<Product>) ProductRepository.findAll();
		for(Product p : getProducts) {
			
			if(checkExpirationDate(p)<0) {
				//Create Email Sender
    			JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
    			mailsender.setHost(this.emailCfg.getHost());
    			mailsender.setPort(this.emailCfg.getPort());
    			mailsender.setUsername(this.emailCfg.getUsername());
    			mailsender.setPassword(this.emailCfg.getPassword());
    			
    			//Create Email instance
    			SimpleMailMessage message = new SimpleMailMessage(); 
    	        message.setFrom("noreply@stockController.com");
    	        message.setTo("admin@consommi.tn"); 
    	        message.setSubject("Product Alert"); 
    	        message.setText("The Product ["+p.getLabel()+"]  will expire in "+p.getExpirationDate());
    	        
    	        //Send the mail
    	        mailsender.send(message);
			}
		}*/
		if (keyword!= null) {
			for(Product n : ProductRepository.findAll(keyword)) {
				System.out.println("Before");
				Category cat = categoryRepository.findById(findCategoryByProduct(n.getId())).get();
				System.out.print("categoryyyy"+cat+"  :  "+findCategoryByProduct(n.getId()));
				cat.setPopularCat(cat.getPopularCat()+1);
				categoryRepository.save(cat);
			}
			return ProductRepository.findAll(keyword);
		}
		return (List<Product>)ProductRepository.findAll();
	}
    
    /***************Creating getAll method that retrieve all product from database **************/
    @Override
	public List<Product> getAllProducts(int pageNo, int pageSize) {
		/*List<Product>getProducts = (List<Product>) ProductRepository.findAll();
		for(Product p : getProducts) {
			
			if(checkExpirationDate(p)<0) {
				//Create Email Sender
    			JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
    			mailsender.setHost(this.emailCfg.getHost());
    			mailsender.setPort(this.emailCfg.getPort());
    			mailsender.setUsername(this.emailCfg.getUsername());
    			mailsender.setPassword(this.emailCfg.getPassword());
    			
    			//Create Email instance
    			SimpleMailMessage message = new SimpleMailMessage(); 
    	        message.setFrom("noreply@stockController.com");
    	        message.setTo("admin@consommi.tn"); 
    	        message.setSubject("Product Alert"); 
    	        message.setText("The Product ["+p.getLabel()+"]  will expire in "+p.getExpirationDate());
    	        
    	        //Send the mail
    	        mailsender.send(message);
			}
		}*/
		Pageable paging=PageRequest.of(pageNo, pageSize) ;
		Page<Product> pagedResult = ProductRepository.findAll(paging);
		return pagedResult.toList();
		
		//return (List<Product>)ProductRepository.findAll();
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

	
	@Override
	public int findCategoryByProduct(int idp) {
		
		Product prod = ProductRepository.findById(idp).get();
		
		
 	return prod.getCat().getId() ;
	}

	@Override
	public List<Product> getAllProductsByPopularity() {
		return ProductRepository.findSortedByCat();
	}
	
	


}
