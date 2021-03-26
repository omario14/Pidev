package tn.esprit.spring.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dto.order.OrderItemsDto;
import tn.esprit.spring.entities.OrderItem;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.repository.OrderItemsRepository;

import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }
    
    public List<OrderItem> ItemOnorder(Integer orderid)
    {	List<OrderItem> Orderitem = orderItemsRepository.findAllByOrderIdOrderItem(orderid);
    	return Orderitem;
    }


}
