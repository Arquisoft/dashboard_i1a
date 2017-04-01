package services;

import java.util.List;

import persistence.model.User;

public interface UserService {
	
	void save(User user);
	List<User> findAll();
}
