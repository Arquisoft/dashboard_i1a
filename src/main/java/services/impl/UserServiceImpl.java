package services.impl;

import java.util.List;

import persistence.model.User;
import persistence.repositories.UserRepository;
import services.UserService;

public class UserServiceImpl implements UserService{
	
	private UserRepository repository;

	@Override
	public void save(User user) {
		repository.save(user);		
	}

	@Override
	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

}
