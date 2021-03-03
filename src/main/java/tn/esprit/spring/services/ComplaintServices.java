package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Complaint;
import tn.esprit.spring.repositories.ComplaintRepository;

@Service
public class ComplaintServices implements IComplaintServices {
	@Autowired
	ComplaintRepository cRepository;
	

	@Override
	public Complaint addComplaint(Complaint C) {
		
		return cRepository.save(C);
	}

	@Override
	public Complaint updateComplaint(Complaint C) {
		
		return cRepository.save(C);
	}

	@Override
	public void deletecomplaint(int id) {
		
		cRepository.deleteById(id);
	}

	@Override
	public List<Complaint> getComplaints() {
		
		return (List<Complaint>) cRepository.findAll();
	}

}
