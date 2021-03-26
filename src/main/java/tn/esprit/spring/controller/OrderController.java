package tn.esprit.spring.controller;

import com.itextpdf.text.DocumentException;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import tn.esprit.spring.common.ApiResponse;
import tn.esprit.spring.dto.checkout.CheckoutItemDto;
import tn.esprit.spring.dto.checkout.StripeResponse;
import tn.esprit.spring.dto.order.OrderItemsDto;
//import  tn.esprit.spring.exceptions.AuthenticationFailException;
import tn.esprit.spring.exception.ProductNotExistException;
import tn.esprit.spring.utils.GeneratePdfReport;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private MailService mailService;
	@Autowired
	private OrderItemsService orderitemsService;

	// private AuthenticationService authenticationService;

	/*
	 * public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token")
	 * String token, @RequestParam("sessionId") String sessionId) throws
	 * ProductNotExistException, AuthenticationFailException
	 */
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> placeOrder(@RequestBody String sessionId) throws ProductNotExistException {
		// authenticationService.authenticate(token);
		// int userId = authenticationService.getUser(token).getId();
		// orderService.placeOrder(userId, sessionId);
		User u = userService.getUserById(2);
		orderService.placeOrder(u, sessionId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<List<Orders>> getAllOrders() {
		// authenticationService.authenticate(token);
		// int userId = authenticationService.getUser(token).getId();
		// List<Orders> orderDtoList = orderService.listOrders(userId);
		User u = userService.getUserById(1);
		List<Orders> orderDtoList = orderService.listOrders(u);
		return new ResponseEntity<List<Orders>>(orderDtoList, HttpStatus.OK);
	}

	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkoutList() throws StripeException {
		User u = userService.getUserById(2);
		Session session = orderService.createSession(u);
		StripeResponse stripeResponse = new StripeResponse(session.getId());
		return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/pdfreport/{orderid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> citiesReport(@PathVariable Integer orderid)
			throws FileNotFoundException, DocumentException {
		// User u = userService.getUserById(1);
		Orders od = orderService.getOrder(orderid);
		List<OrderItem> orderitems = orderitemsService.ItemOnorder(orderid);
		ByteArrayInputStream bis = GeneratePdfReport.createPDF(orderitems, od);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

	@GetMapping("/order/user/{orderid}")
	public ResponseEntity<?> Proudctorderlist(@PathVariable Integer orderid) {
		Orders x = orderService.getOrder(orderid);
		List<OrderItem> orderitems = orderitemsService.ItemOnorder(orderid);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("Totale", x.getTotalPrice());
		result.put("orderitem", orderitems);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}
