package tn.esprit.spring.repositories;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.spring.entities.Notifications;

public interface NotificationsRepository extends CrudRepository<Notifications,Integer> {

}
