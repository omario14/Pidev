package tn.esprit.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entities.Ray;
import tn.esprit.spring.service.IRayService;

@RestController
@RequestMapping("/ray")
public class RayRestController {
	@Autowired
	IRayService rayService;
	
	//creating a post mapping that add ray in database
    @PostMapping("/add-ray")
	@ResponseBody
	public int  addRay(@RequestBody Ray r) {
    	rayService.addRay(r);
		return r.getId();
    }
    
  //creating a put  mapping that upgrade ray
  	@PutMapping("/update-ray/{idray}")
  	@ResponseBody
  	public ResponseEntity<String> updateRay(
  		@RequestBody Ray ra,@PathVariable("idRay")int idRay) {
  		rayService.updateRay(ra,idRay);
  	    return new ResponseEntity<String>("Ray updated successfully",HttpStatus.OK);
  		
  	}
  	
  //creating a delete mapping that delete ray from database
  	@DeleteMapping("/delete-ray/{idRay}")
  	@ResponseBody
  	public ResponseEntity<String>  deleteCategory(
  		@RequestBody Ray ra,@PathVariable("idRay")int idRay) {
  		rayService.deleteRay(idRay);
  	    return new ResponseEntity<String>("Ray deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
  	
  	//creating a get mapping that retrieves all Rays from database.
  	@GetMapping("/get-all-ray")
  	@ResponseBody
  	public List<Ray>  getAllRays() {
  		List<Ray> rays = new ArrayList<>();
  		for(Ray r : rayService.retrieveAllRays()) {
  			rays.add(r);
  			
  		}
  		
  		return rays;
  	}
  	
  	//creating a get mapping that retrieves all Rays from database.
  	 @GetMapping("/find-ray-byid/{id}")
  	 @ResponseBody
  		public Ray findRayById(@PathVariable("id")int id) {
  			return rayService.retrieveRayById(id);
  		}
  	 
  	//creating a get mapping that retrieves all Rays from database.
  		 @GetMapping("/find-ray-byname/{name}")
  		 @ResponseBody
  			public Ray findRayByName(@PathVariable("name")String name) {
  				return rayService.findRayByName(name);
  			}
  		 
  		@PutMapping("/affecter-ray-category/{idc}/{idr}")
  		@ResponseBody
  			public String affectRayCategory(@PathVariable("idc")int idc, @PathVariable("idr")int idr) {
  			rayService.affecterRayCategory(idc, idr);
  			return "Category affected to Ray successfully!!";
  		}
  		
}
