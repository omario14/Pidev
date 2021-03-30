package tn.esprit.spring.services;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.UriAssert;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.DeliveryMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repositories.DeliveryManRepository;
import tn.esprit.spring.repositories.DeliveryRepository;
import tn.esprit.spring.repositories.OrdersRepository;



@Service
public class DeliveryServices implements IDeliveryServices {
	
	@Autowired
	DeliveryRepository delrepository;
	
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	DeliveryManRepository livreurRepository;

	@Override
	public List<Delivery> getDeliveries() {
		
		return (List<Delivery>) delrepository.findAll();
	}
	

	@Override
	public Delivery addDelivery(Delivery D) {
		
		return delrepository.save(D);
	}

	@Override
	public Delivery updateDelivery(Delivery D) {
		
		return delrepository.save(D);
	}

	@Override
	public void deleteDelivery(int id) {
		
		delrepository.deleteById(id);
		
	}

	@Override
	public String passerLivraison(Orders order)  {
		
		//Orders orders = (Orders) ordersRepository.findAll();
final String uri = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+order.getAdress()+".json?access_token=pk.eyJ1IjoiZXp6ZWRkaW5lIiwiYSI6ImNrbWp1eHVwejBwaTUyeGxuOGxjdDN3ODMifQ.qYcucDhEuNuIZV_BFv0inw";
		
		RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> result = restTemplate.getForObject(uri, Map.class);
        List<Object> features= (List<Object>) result.get("features");
       // features.get("center");
        Map<String,Object> featureContent=(Map<String, Object>) features.get(0);
        System.out.println(featureContent.get("center"));
        List<Integer> coord=(List<Integer>) featureContent.get("center");
        System.out.println(coord.get(0)+"lat");
        System.out.println(coord.get(1)+"long");
       // return result;
		
		
		Delivery del= new Delivery();
		//List<Orders> listo= Arrays.asList(order); 
		//del.setOrders(listo);
		del.setEtat(Delivery.StateDelivery.IN_PROGRESS);
		del.setDateDel(order.getDate());
		//System.out.println(order.getDate()+"222155555555555555555555555");
		del.setPrice(1);
		del.setUserr(order.getUserr());
		del.setAddress(String.valueOf(coord.get(1)+","+coord.get(0)));
		
		delrepository.save(del);
		
		
		
		
		
		return "delivery went successfully";
	}

	@Override
	public void affecterLivraisonALivreur(int idLivreur, int idDelivery) {
		
		DeliveryMan livreurManage = livreurRepository.findById(idLivreur).get();
		Delivery DeliveryManage = delrepository.findById(idDelivery).get();
		
		
		
	if (  livreurManage.getStateLivreur().equals(DeliveryMan.StateLivreur.Active) ) {
	
		
		
		}
	else {
		livreurManage.setDeliveryNbre(livreurManage.getDeliveryNbre()+1);
		livreurManage.setStateLivreur(DeliveryMan.StateLivreur.Active);
		livreurManage.setSalary(livreurManage.getSalary()+5);
		livreurRepository.save(livreurManage);
		
		DeliveryManage.setDelMan(livreurManage);
		DeliveryManage.setEtat(Delivery.StateDelivery.Approved);
		delrepository.save(DeliveryManage);
		}
	}


	@Override
	public String geocode(String address) {
		Orders order = new Orders();
		
		final String uri = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+order.getAdress()+".json?access_token=pk.eyJ1IjoiZXp6ZWRkaW5lIiwiYSI6ImNrbWp1eHVwejBwaTUyeGxuOGxjdDN3ODMifQ.qYcucDhEuNuIZV_BFv0inw";
		
		RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result +"1221111111111111111111111111111111111111111111111111111111");
        return result;
	}
	
	
	

}