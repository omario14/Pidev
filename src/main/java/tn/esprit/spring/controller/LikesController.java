package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.service.LikesService;

@RestController
@RequestMapping("/likes")
public class LikesController {
	
	@Autowired
	LikesService likesService;

}
