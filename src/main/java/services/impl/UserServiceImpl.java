package services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.model.User;
import persistence.repositories.UserRepository;
import services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(User user) {
		repository.save(user);
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		if (repository.findAll() != null) {
			Iterator<User> it = repository.findAll().iterator();
			while (it.hasNext())
				users.add(it.next());
		}
		return users;
	}

	@Override
	public void delete(User user) {
		repository.delete(user);
	}

	@Override
	public boolean checkExists(Long id) {
		return repository.findOne(id) != null;
	}

}
