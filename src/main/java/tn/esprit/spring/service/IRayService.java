package tn.esprit.spring.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Ray;

@Repository
public interface IRayService  {
	
	public int  addRay(Ray r);
	public void deleteRay(int id);
	public void updateRay(Ray r,int idRay);
	public List<Ray> retrieveAllRays();
	public Ray retrieveRayById (int id);
	public Ray findRayByName(String name);
	public void affecterRayCategory(int idc,int idr);

}
