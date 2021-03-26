package tn.esprit.spring.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.exception.ProductNotExistException;
import tn.esprit.spring.exception.UserNotExistException;
import tn.esprit.spring.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public User getUserById(int userid) throws UserNotExistException {
		Optional<User> optionalUser = userRepository.findById(userid);
		if (!optionalUser.isPresent())
			throw new ProductNotExistException("User id is invalid " + userid);
		return optionalUser.get();
	}

}
