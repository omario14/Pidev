package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Service.CartService;
import tn.esprit.spring.Service.CouponService;
import tn.esprit.spring.Service.UserService;
import tn.esprit.spring.dto.cart.CartDto;
import tn.esprit.spring.entities.Cart;
import tn.esprit.spring.entities.Coupon;
import tn.esprit.spring.entities.User;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	@Autowired
	private UserService userService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private CartService cartService;

	@GetMapping("/generate")
	public ResponseEntity<String> genarte() {
		User u = userService.getUserById(1);
		String response = couponService.shouldGenerateNumericCode(u, 15);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/add")
	public ResponseEntity<String> adddiscount(@RequestBody Coupon cp) {
		User u = userService.getUserById(1);

		if (couponService.CheckCoupon(cp.getCode(), u)) {
			if (cartService.listCartItems(u).getDiscount() > 0) {
				return new ResponseEntity<>("You already use your coupon", HttpStatus.OK);

			} else if (couponService.getDiscountByUser(u) == 0) {

				return new ResponseEntity<>("No Money", HttpStatus.OK);

			} else {
				couponService.setCouponStaut(u, true);
				return new ResponseEntity<>("Discount add : " + couponService.getDiscountByUser(u), HttpStatus.OK);
			}

		} else {
			return new ResponseEntity<>("Your discount code is not correct", HttpStatus.OK);

		}

	}

}
