package tn.esprit.spring.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import tn.esprit.spring.dto.cart.CartDto;
import tn.esprit.spring.dto.cart.CartItemDto;
import tn.esprit.spring.dto.checkout.CheckoutItemDto;
import tn.esprit.spring.dto.order.OrderDto;
import tn.esprit.spring.dto.order.PlaceOrderDto;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CartService cartService;

	@Autowired
	OrderItemsService orderItemsService;

	@Autowired
	private MailService mailService;

	@Value("${baseURL}")
	private String baseURL;

	@Value("${STRIPE_SECRET_KEY}")
	private String apiKey;

	@Autowired
	private CouponService couponService;

	public Orders saveOrder(PlaceOrderDto orderDto, User user, String sessionID) {
		Orders order = getOrderFromDto(orderDto, user, sessionID);
		return orderRepository.save(order);
	}

	private Orders getOrderFromDto(PlaceOrderDto orderDto, User user, String sessionID) {
		Orders order = new Orders(orderDto, user, sessionID, user.getAdress());
		return order;
	}

	public List<Orders> listOrders(User user) {
		List<Orders> orderList = orderRepository.findAll();
		return orderList;
	}

	public static OrderDto getDtoFromOrder(Orders order) {
		OrderDto orderDto = new OrderDto(order);
		return orderDto;
	}
	// order.getTotalAmount().divide(new BigDecimal(100)).intValue()

	public void placeOrder(User user, String sessionId) {
		CartDto cartDto = cartService.listCartItems(user);
		if (couponService.ExistCoupon(user)) {

			if (couponService.getCouponStaut(user) && couponService.getDiscountByUser(user) < cartDto.getTotalCost()) {
				couponService.setDiscountByUser(user, 0.0);
				couponService.setCouponStaut(user, false);

			} else if (couponService.getDiscountByUser(user) > cartDto.getTotalCost()) {

				couponService.setDiscountByUser(user, cartDto.getDiscount() - cartDto.getTotalCost());
				couponService.setCouponStaut(user, false);
			} else {
				DecimalFormat df = new DecimalFormat("0.00");
				Double bd = Double.parseDouble(df.format(cartDto.getTotalCost() / 100));
				couponService.shouldGenerateNumericCode(user, bd);

			}
		}
		if (couponService.ExistCoupon(user) == false) {

			DecimalFormat df = new DecimalFormat("0.00");
			Double bd = Double.parseDouble(df.format(cartDto.getTotalCost() / 100));
			couponService.shouldGenerateNumericCode(user, bd);
			//couponService.setCouponamount(user, d);
		}
		PlaceOrderDto placeOrderDto = new PlaceOrderDto();
		placeOrderDto.setUser(user);
		placeOrderDto.setTotalPrice(cartDto.getTotalCost());
		placeOrderDto.setDiscount(cartDto.getDiscount());
		Orders order = saveOrder(placeOrderDto, user, sessionId);

		List<CartItemDto> cartItemDtoList = cartDto.getcartItems();
		sendMail(order, cartItemDtoList);
		for (CartItemDto cartItemDto : cartItemDtoList) {
			cartItemDto.getProduct().setQuantity(cartItemDto.getProduct().getQuantity() - cartItemDto.getQuantity());
			OrderItem orderItem = new OrderItem(cartItemDto.getQuantity(), cartItemDto.getProduct().getPrice(), order,
					cartItemDto.getProduct());
			orderItemsService.addOrderedProducts(orderItem);
		}
		cartService.deleteCartItems(user);
	}

	SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
		return SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
				.setUnitAmount(((long) checkoutItemDto.getPrice()) * 100)
				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
						.setName(checkoutItemDto.getProductName()).build())
				.build();
	}

	SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
		return SessionCreateParams.LineItem.builder().setPriceData(createPriceData(checkoutItemDto))
				.setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity()))).build();
	}

	public Session createSession(User user) throws StripeException {
		CartDto cartDto = cartService.listCartItems(user);
		List<CartItemDto> cartItemDtoList = cartDto.getcartItems();
		String successURL = baseURL + "payment/success";
		String failedURL = baseURL + "payment/failed";
		Stripe.apiKey = apiKey;
		List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<SessionCreateParams.LineItem>();
		for (CartItemDto cartItemDto : cartItemDtoList) {
			CheckoutItemDto CheckoutItemDto = new CheckoutItemDto(cartItemDto.getProduct().getLabel(),
					cartItemDto.getQuantity(), cartItemDto.getProduct().getPrice(), cartItemDto.getProduct().getId(),
					cartItemDto.getUser().getId());
			sessionItemsList.add(createSessionLineItem(CheckoutItemDto));
		}

		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setCancelUrl(failedURL).addAllLineItem(sessionItemsList)
				.setSuccessUrl(successURL).build();
		return Session.create(params);
	}

	public void sendMail(Orders order, List<CartItemDto> cartItemDtoList) {
		Map<String, Object> model = new HashMap<>();
		model.put("orders", order);
		model.put("values", cartItemDtoList);
		mailService.sendMail(model);

	}

	public Orders getOrder(int ids) {
		Optional<Orders> or = orderRepository.findById(ids);
		return or.get();
	}
}
