package tn.esprit.spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Event;
import tn.esprit.spring.repositories.EventRepository;


@Service
public class EventServices implements IEventServices  {
	@Autowired
	EventRepository erepository;

	@Override
	public List<Event> getEvents() {
		
		return (List<Event>) erepository.findAll();
	}

	@Override
	public Event addEvent(Event E) {
		
		return erepository.save(E);
	}

	@Override
	public Event updateEvent(Event E) {
		
		return erepository.save(E);
	}

	@Override
	public void deleteEvent(int id) {
		
		erepository.deleteById(id);
	}

}
