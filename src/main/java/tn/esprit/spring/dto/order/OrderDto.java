package tn.esprit.spring.dto.order;

import javax.validation.constraints.NotNull;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.User;

public class OrderDto {
    private Integer id;
    private @NotNull User user;
    private @NotNull String Adresse;

    public OrderDto() {
    }

    public OrderDto(Orders order) {
        this.setId(order.getId());
        this.setUser(order.getUser());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
    

}
