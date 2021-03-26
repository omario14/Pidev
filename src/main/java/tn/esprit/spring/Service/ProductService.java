package tn.esprit.spring.Service;

import tn.esprit.spring.exception.ProductNotExistException;
import tn.esprit.spring.entities.Product;
import tn.esprit.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product getProductById(Long productId) throws ProductNotExistException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if (!optionalProduct.isPresent())
			throw new ProductNotExistException("Product id is invalid " + productId);
		return optionalProduct.get();
	}

}
