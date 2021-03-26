package tn.esprit.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Cart;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserIdOrderByCreatedDateDesc(Integer userId);
    
    
    @Query("Select m from Cart m where m.product.id=:productid and m.user.id=:userid")
    Cart findByproductid(@Param("productid") long productid,@Param("userid") int userid);

}

