package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.service.ICategoryService;
import tn.esprit.spring.service.IProductService;

@SpringBootTest
class SpringBootPiDevApplicationTests {
	@Autowired
	IProductService Prod;
	@Autowired
	ICategoryService cat;


	@Test
	public void contextLoads()  {

		/*Category cat1 = new Category("Electronics");
		Product prod1 = new Product("Mamzouj",0.250f,500,"Mamzouj petit yaought de delice",20,"619 555 745","12415#65545",cat1);
		Product prod2 = new Product("Lait Delice",1.200f,1000,"7lib delice",1000,"619 111 745","11315#65545",cat1);
		cat.addCategory(cat1);
		Prod.addProduct(prod1);
		Prod.addProduct(prod2);
		
		cat.addCategory(cat1);*/
		
	}
	
	
}
