package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Complaint;

public interface IComplaintServices {
	
	List <Complaint> getComplaints();
	Complaint addComplaint(Complaint C);
	Complaint updateComplaint(Complaint C);
	void deletecomplaint(int  id);

}
