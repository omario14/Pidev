package tn.esprit.spring.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.Service.MailService;

 
@RestController
public class MailSenderController {
 
    @Autowired
    private MailService mailService;
 
 //  @PostMapping("send-mail")
   /*   public ResponseEntity<String> sendMail() {
	   
      Map<String, ArrayList<String>> model = new HashMap<>();
       // model.put("name", "Ayoub");
        model.put("orders",new ArrayList<String>());
        String response = mailService.sendMail(model);
        return new ResponseEntity<>(response, HttpStatus.OK);*/
    //}
}