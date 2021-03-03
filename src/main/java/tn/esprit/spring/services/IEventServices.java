package tn.esprit.spring.services;

import java.util.List;
import tn.esprit.spring.entities.Event;

public interface IEventServices {
	List <Event> getEvents();
	Event addEvent(Event E);
	Event updateEvent(Event E);
	void deleteEvent(int  id);
	

}
