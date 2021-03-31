package tn.esprit.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.Nullable;

import tn.esprit.spring.common.AuthenticationFailException;
import tn.esprit.spring.entities.Publicity;
import tn.esprit.spring.repository.PublicityRepository;
import tn.esprit.spring.service.AuthenticationService;
import tn.esprit.spring.service.PublicityService;

@RestController
@RequestMapping("/publicity")
public class PublicityController {
	
	@Autowired
	PublicityService publicityService;
	@Autowired
	PublicityRepository publicityRepository;
	@Autowired
    AuthenticationService authenticationService;
	
	@PostMapping("/addpub")
	public void ajouterPublicity(@RequestBody Publicity p, @RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		publicityService.ajouterPublicity(p, idUser);
	}
	
	@PostMapping("/file")
    @ResponseBody
    public Publicity  uploddimg (@RequestParam("file") @Nullable MultipartFile file , @RequestParam("idpub") int idpub, @RequestParam("token") String token ) {
       
		Publicity publicity = publicityRepository.findById(idpub).get();
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
        if(file==null) {
        	//publicity.setImage("defaultPic.jpg");
            publicityService.ajouterPublicity(publicity, idUser);
            
        }else {
            try { 
                File f = new File("C:\\Upload\\"+file.getOriginalFilename());
                file.transferTo(f);
                publicity.setImage(file.getOriginalFilename());
                publicityService.ajouterPublicity(publicity, idUser);
                
            } catch (IllegalStateException e) {
             e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return(publicity);
    }
	
	
	@GetMapping("/getAllPublicities")
  	@ResponseBody
  	public List<Publicity>  getAllPublicities(@RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
  		List<Publicity> publicities = new ArrayList<>();
  		for(Publicity p : publicityService.retrieveAllPublicities()) {
  			publicities.add(p);
  		}
  		return publicities;
  	}
	
	@GetMapping("/getOnePublicity")
  	@ResponseBody
  	public Publicity  getOnePublicity(@RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
  		Publicity pub = publicityService.retrievePublicityByGeneration(idUser);
  		return pub;
  	}

	
	
	
	
	@DeleteMapping("/deletePublicity/{idPublicity}")
  	@ResponseBody
  	public ResponseEntity<String>  deletePublicity(
  		@RequestBody Publicity publicity, @PathVariable("idPublicity")int idPublicity, @RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		publicityService.deletePublicity(idPublicity, idUser);
  	    return new ResponseEntity<String>("Publicity deleted successfully",HttpStatus.ACCEPTED);
  		
  	}
	
	@PutMapping("/updatePublicity/{idPublicity}")
  	@ResponseBody
  	public ResponseEntity<String> updatePublicity(
  		@RequestBody Publicity publicity,@PathVariable("idPublicity")int idPublicity, @RequestParam("token") String token) throws AuthenticationFailException{
		authenticationService.authenticate(token);
		int idUser = authenticationService.getUser(token).getId();
		publicityService.updatePublicity(publicity, idPublicity, idUser);
  	    return new ResponseEntity<String>("Publicity updated successfully",HttpStatus.OK);
  	}

}
