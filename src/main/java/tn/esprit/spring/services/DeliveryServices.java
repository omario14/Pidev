package tn.esprit.spring.services;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestToUriTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.UriAssert;
import org.hibernate.query.criteria.internal.expression.function.SqrtFunction;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.DeliveryMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import tn.esprit.spring.entities.Delivery;
import tn.esprit.spring.entities.DeliveryMan;
import tn.esprit.spring.entities.Orders;
import tn.esprit.spring.entities.SMS;
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
       
		
		
		Delivery del= new Delivery();
		
		del.setEtat(Delivery.StateDelivery.IN_PROGRESS);
		del.setDateDel(order.getDate());
		del.setPrice(order.getTotPrice());
		del.setUserr(order.getUserr());
		del.setAddress(order.getAdress());
		del.setLattitude(String.valueOf(coord.get(1)));
		del.setLongitude(String.valueOf(coord.get(0)));
		delrepository.save(del);
		
		
		
		
		
		return "delivery went successfully";
	}
/*
	@Override
	public void affecterLivraisonALivreur(int idLivreur, int idDelivery) {
		
		DeliveryMan livreurManage = livreurRepository.findById(idLivreur).get();
		Delivery DeliveryManage = delrepository.findById(idDelivery).get();
		
		
		
	if (  livreurManage.getStateLivreur().equals(false) ) {
	
		idLivreur = idLivreur + 1;
		affecterLivraisonALivreur(idLivreur, idDelivery);
		
		}
	else {
		livreurManage.setDeliveryNbre(livreurManage.getDeliveryNbre()+1);
		livreurManage.setStateLivreur(false);
		livreurManage.setSalary(livreurManage.getSalary()+5);
		livreurRepository.save(livreurManage);
		SMS sms=new SMS("wsol?","+21695592018");
		sms.send();
		DeliveryManage.setDelMan(livreurManage);
		DeliveryManage.setEtat(Delivery.StateDelivery.Approved);
		delrepository.save(DeliveryManage);
		
		}
	}*/


	@Override
	public String geocode(String address) {
		Orders order = new Orders();
		
		final String uri = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+order.getAdress()+".json?access_token=pk.eyJ1IjoiZXp6ZWRkaW5lIiwiYSI6ImNrbWp1eHVwejBwaTUyeGxuOGxjdDN3ODMifQ.qYcucDhEuNuIZV_BFv0inw";
		
		RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result +"1221111111111111111111111111111111111111111111111111111111");
        return result;
	}


	@Transactional
	@Override
	public void setArrived(int deleveryId) {
		
		
		delrepository.setArrived(deleveryId);
		
	}


	@Override
	public Delivery getById(int id) {
		// TODO Auto-generated method stub
		return delrepository.getById(id);
	}




	@Override
	public DeliveryMan AffectDeliveryToNearesDeliveyMen(int deliveryId) {
		// TODO Auto-generated method stub
		
		List<DeliveryMan> listLivreur=livreurRepository.getByStateLivreurTrue();
		
		if(listLivreur!=null) {
			DeliveryMan nearsetDeliveryMan=listLivreur.get(0);
			
			Delivery delivery=delrepository.getById(deliveryId);
			
			coord deliveryLocation =new coord(Float.parseFloat(delivery.getLongitude()),Float.parseFloat(delivery.getLattitude()));
			coord livreurLocation=new coord(Float.parseFloat(nearsetDeliveryMan.getLongitude()),Float.parseFloat(nearsetDeliveryMan.getLattitude()));
			
			double minDistance=calculateDistance(deliveryLocation, livreurLocation);
			
			
			for(DeliveryMan deliveryMan:listLivreur){
				
				coord loopLivreurLocation=new coord(Float.parseFloat(deliveryMan.getLongitude()),Float.parseFloat(deliveryMan.getLattitude()));
				
				double distance=calculateDistance(deliveryLocation, loopLivreurLocation);
				
				if(distance<minDistance) {
					minDistance=distance;
					nearsetDeliveryMan=deliveryMan;
					
				}
				
			}
			
			nearsetDeliveryMan.setDeliveryNbre(nearsetDeliveryMan.getDeliveryNbre()+1);
			nearsetDeliveryMan.setStateLivreur(false);
			nearsetDeliveryMan.setSalary(nearsetDeliveryMan.getSalary()+5);
			livreurRepository.save(nearsetDeliveryMan);
			
			delivery.setDelMan(nearsetDeliveryMan);
			delivery.setEtat(Delivery.StateDelivery.Approved);
			delrepository.save(delivery);
			
			
			
			return nearsetDeliveryMan;
		}
		return null;
	}
	class coord{
		float lon;
		float lat;
		public coord(float lon,float lat) {
			this.lon=lon;
			this.lat=lat;
			// TODO Auto-generated constructor stub
		}
		public float getLon() {
			return lon;
		}
		public void setLon(float lon) {
			this.lon = lon;
		}
		public float getLat() {
			return lat;
		}
		public void setLat(float lat) {
			this.lat = lat;
		}
		
		
	}
	public double calculateDistance(coord deliveryLocation,coord deliveryMenLocation) {
		double distance=0;
		distance=Math.sqrt(Math.pow(deliveryLocation.getLon()-deliveryMenLocation.getLon(),2)+Math.pow(deliveryLocation.getLat()- deliveryMenLocation.getLat(),2))*111.16;
		
		return distance;
		
	}
	
	
	

}