package tn.esprit.spring.Service;

import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.*;
import tn.esprit.spring.exception.CartItemNotExistException;
import tn.esprit.spring.exception.ProductNotExistException;
import tn.esprit.spring.dto.cart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private CouponService couponService;

	public CartService() {
	}

	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	public boolean addToCart(AddToCartDto addToCartDto, User user) {
		Product p = productService.getProductById(addToCartDto.getProduct().getId());
		Cart ad = getProductCartById(p.getId(), user.getId());

		if (p.getQuantity() < addToCartDto.getQuantity()) {
			return false;
		}
		if (ad != null) {
			if (ad.getQuantity() + addToCartDto.getQuantity() > p.getQuantity()) {
				return false;
			} else {
				ad.setQuantity(addToCartDto.getQuantity() + ad.getQuantity());
				return true;
			}
		}

		else {
			Cart cart = getAddToCartFromDto(addToCartDto, user);
			cartRepository.save(cart);
			return true;
		}

	}

	private Cart getAddToCartFromDto(AddToCartDto addToCartDto, User user) {
		Cart cart = new Cart(addToCartDto, user);
		return cart;
	}

	public CartDto listCartItems(User user) {

		List<Cart> cartList = cartRepository.findAllByUserIdOrderByCreatedDateDesc(user.getId());
		List<CartItemDto> cartItems = new ArrayList<>();
		for (Cart cart : cartList) {
			CartItemDto cartItemDto = getDtoFromCart(cart);
			cartItems.add(cartItemDto);
		}
		double totalCost = 0;
		for (CartItemDto cartItemDto : cartItems) {
			totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
		}
		boolean check = false;
		double discount = 0;
		// il faut faire un test sur l'existance d'un coupon
		if (couponService.ExistCoupon(user)) {
			discount = couponService.getDiscountByUser(user);
			check = couponService.getCouponStaut(user);
														
		}
		CartDto c = new CartDto(cartItems, totalCost);
		if (check) {
			if (discount > totalCost) {
				Double totalost = 0.0;
				c = new CartDto(cartItems, totalCost, totalost);
				c.setDiscount(totalCost);
				return c;
			} else if(totalCost == 0 ){
				c = new CartDto(cartItems, totalCost,0.0);
				return c;
			}else{
				c = new CartDto(cartItems, discount, totalCost -discount );
				//couponService.setDiscountByUser(user, 0.0);
				//couponService.setCouponStaut(user, false);
				c.setDiscount(discount);
				return c;
				
			}

		} else {

			return c = new CartDto(cartItems, totalCost);

		}
	}

	public static CartItemDto getDtoFromCart(Cart cart) {
		CartItemDto cartItemDto = new CartItemDto(cart);
		return cartItemDto;
	}

	public boolean updateCartItem(AddToCartDto cartDto, User user, Product product) {
		Product p = productService.getProductById(cartDto.getProduct().getId());
		if (p.getQuantity() < cartDto.getQuantity()) {
			return false;
		} else {
			Cart cart = getAddToCartFromDto(cartDto, user);
			cart.setQuantity(cartDto.getQuantity());
			cart.setUser(user);
			cart.setId(cartDto.getId());
			cart.setProduct(product);
			cart.setCreatedDate(new Date());
			cartRepository.save(cart);
			return true;
		}
	}

	public void deleteCartItem(int id, User user) throws CartItemNotExistException {
		if (!cartRepository.existsById(id))
			throw new CartItemNotExistException("Cart id is invalid : " + id);
		cartRepository.deleteById(id);

	}

	public void deleteCartItems(User user) {
		cartRepository.deleteAll();
	}

	public Cart getProductCartById(long productId, int userid) throws ProductNotExistException {
		Cart optionalProduct = cartRepository.findByproductid(productId, userid);
		return optionalProduct;
	}

}
