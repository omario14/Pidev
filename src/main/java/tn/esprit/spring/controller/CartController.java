package tn.esprit.spring.controller;

import tn.esprit.spring.common.ApiResponse;
import tn.esprit.spring.dto.cart.AddToCartDto;
import tn.esprit.spring.dto.cart.CartDto;
//import tn.esprit.spring.exception.AuthenticationFailException;
import tn.esprit.spring.exception.CartItemNotExistException;
import tn.esprit.spring.exception.ProductNotExistException;
import tn.esprit.spring.entities.*;
//import tn.esprit.spring.Service.AuthenticationService;
import tn.esprit.spring.Service.CartService;
import tn.esprit.spring.Service.CouponService;
import tn.esprit.spring.Service.ProductService;
import tn.esprit.spring.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;
	
	 @Autowired
	 private CouponService couponService;
	    

	// private AuthenticationService authenticationService;
	// public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto
	// addToCartDto, @RequestParam("token") String token) throws
	// AuthenticationFailException, ProductNotExistException {

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto)
			throws ProductNotExistException {
		// authenticationService.authenticate(token);

		// int userId = authenticationService.getUser(token).getId();

		// cartService.addToCart(addToCartDto,userId);
		User u = userService.getUserById(2);
		if(cartService.addToCart(addToCartDto, u))
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

		}else{
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Quantity not available"), HttpStatus.CREATED);

		}


	}

	// public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String
	// token) throws AuthenticationFailException {
	@GetMapping("/")
	public ResponseEntity<CartDto> getCartItems() {
		// authenticationService.authenticate(token);
		// int userId = authenticationService.getUser(token).getId();
		User u = userService.getUserById(2);
		CartDto cartDto = cartService.listCartItems(u);
		// CartDto cartDto = cartService.listCartItems(userId);

		return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
	}

	/*
	 * @PutMapping("/update/{cartItemId}") public ResponseEntity<ApiResponse>
	 * updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
	 * 
	 * @RequestParam("token") String token) throws
	 * AuthenticationFailException,ProductNotExistException {
	 */

	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto)
			throws ProductNotExistException {
		// authenticationService.authenticate(token);
		// int userId = authenticationService.getUser(token).getId();
		Product product = productService.getProductById(cartDto.getProduct().getId());
		User u = userService.getUserById(1);
		// cartService.updateCartItem(cartDto,userId,product);
		if(cartService.updateCartItem(cartDto, u, product))
		{
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);

		}else{
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Quantity not available"), HttpStatus.OK);

		}
	}

	// ,@RequestParam("token") String token
	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID)
			throws CartItemNotExistException {
		// authenticationService.authenticate(token);
		// int userId = authenticationService.getUser(token).getId();
		// cartService.deleteCartItem(itemID,userId);
		User u = userService.getUserById(1);
		cartService.deleteCartItem(itemID, u);

		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
	}

}
