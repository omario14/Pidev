package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.repository.LikesRepository;

@Service
public class LikesService {
	
	@Autowired
	LikesRepository likesRepository;

}
