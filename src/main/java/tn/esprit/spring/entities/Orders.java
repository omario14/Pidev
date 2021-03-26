package tn.esprit.spring.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import tn.esprit.spring.dto.order.PlaceOrderDto;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;
    
    @Column(name = "discount")
    private Double discount;

    @Column(name = "session_id")
    private String sessionId;
    
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy="order",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    private String Adresse ; 
    
    public Orders() {
    }

    public Orders(PlaceOrderDto orderDto, User user, String sessionId,String Adresse){
    	this.user = user;
        this.createdDate = new Date();
        this.totalPrice = orderDto.getTotalPrice();
        this.discount = orderDto.getDiscount();
        this.sessionId = sessionId;
        this.Adresse = Adresse ;
    }
    public Orders(PlaceOrderDto orderDto, User user, String sessionId,String Adresse,Double discount){
    	this.user = user;
        this.createdDate = new Date();
        this.totalPrice = orderDto.getTotalPrice()-discount;
        this.discount = discount;
        this.sessionId = sessionId;
        this.Adresse = Adresse ;

    }


    
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	
    
    
}
