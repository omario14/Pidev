package tn.esprit.spring.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.cart.CartDto;
import tn.esprit.spring.entities.Coupon;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.CouponRepository;
import tn.esprit.spring.utils.CodeConfig;
import tn.esprit.spring.utils.CodeConfig.Charset;
import tn.esprit.spring.utils.VoucherCodes;

@Transactional
@Service
public class CouponService {

	@Autowired
	CouponRepository couponRepository;

	@Autowired
	private CartService cartService;

	public String shouldGenerateNumericCode(User user, double amount) {
		Coupon cp = couponRepository.GetcouponByUserId(user.getId());
		if (cp != null) {
			// double x = cp.getAmount()+amount;
			cp.setAmount(cp.getAmount() + amount);
			return "Done " + cp.getAmount();

		} else {
			CodeConfig config = CodeConfig.length(8).withCharset(Charset.ALPHANUMERIC);
			String code = VoucherCodes.generate(config);
			Coupon coupon = new Coupon(code, amount, user);
			couponRepository.save(coupon);
			return "Code :" + code;
		}
	}
	/*
	 * public boolean CheckCoupon(String code,User user) { Coupon cp =
	 * couponRepository.checkcoupon(user.getId(),code); CartDto cart =
	 * cartService.listCartItems(user); cart.setDiscount(cp.getAmount());
	 * System.out.println(cart.getDiscount()); return true;
	 * 
	 * }
	 */

	public boolean CheckCoupon(String code, User user) {
		Coupon cp = couponRepository.checkcoupon(user.getId(), code);
		if (cp != null && cp.getCode().equals(code)) {
			return true;
		} else
			return false;

	}

	public double getDiscountByUser(User user) {
		Coupon cpp = couponRepository.GetcouponByUserId(user.getId());
		System.out.println(cpp.getAmount());
		return cpp.getAmount();
	}

	public void setDiscountByUser(User user, Double amount) {
		Coupon cpp = couponRepository.GetcouponByUserId(user.getId());
		System.out.print(cpp);
		cpp.setAmount(amount);
	}

	public boolean getCouponStaut(User user) {
		Coupon cp = couponRepository.GetcouponByUserId(user.getId());
		return cp.isUsed();
	}

	public boolean ExistCoupon(User user) {
		Coupon cp = couponRepository.GetcouponByUserId(user.getId());
		if (cp != null) {
			return true;

		} else {
			return false;
		}
	}

	public boolean setCouponStaut(User user, boolean x) {
		Coupon cp = couponRepository.GetcouponByUserId(user.getId());
		cp.setUsed(x);
		return true;

	}

	public void setCouponamount(User user, Double amount) {
		Coupon cp = couponRepository.GetcouponByUserId(user.getId());

		cp.setAmount(amount);

	}
}
