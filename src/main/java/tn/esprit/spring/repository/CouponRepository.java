package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Integer>{
	
	@Query("select m from Coupon m where m.user.id=:userid")
	Coupon GetcouponByUserId(@Param ("userid") int userid);
	
	@Query("select m from Coupon m where m.user.id=:userid and m.code=:code")
	Coupon checkcoupon(@Param ("userid") int userid,@Param ("code") String code);

	@Query("select m from Coupon m where m.user.id=:userid ")
	Coupon GetCouponByUser(@Param ("userid") int userid);

}
