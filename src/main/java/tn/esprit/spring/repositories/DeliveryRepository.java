package tn.esprit.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.entities.Delivery;
import java.util.List;
public interface DeliveryRepository extends CrudRepository<Delivery, Integer> {

	//@Query(value="SELECT * from delivery d join t_commande c on c.deliveries_delivery_id=d.delivery_id"
	//		+ " WHERE c.id_user=1 and d.delivery_state='Approved'",nativeQuery=true)
	//public List<Delivery> getDeliveryByUserAndState(@Param("idUSer") int idUser,@Param("state") String state);
	
}
