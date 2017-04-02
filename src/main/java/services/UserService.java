package services;

import java.util.List;

import persistence.model.User;

public interface UserService {
	
	void save(User user);
	boolean checkExists(Long id);
	List<User> findAll();
}
