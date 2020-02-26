package br.com.itau.working.workingtime.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;
	
	public Optional<User> findOne(Integer id) {
		return userRepo.findById(id);
	}
	
	public List<User> listUsers() {
		return userRepo.findAll();
	}

	public User save(User user) {
		userRepo.save(user);
		
		return user;
	}
}