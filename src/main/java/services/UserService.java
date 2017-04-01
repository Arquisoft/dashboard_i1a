package services;

import java.util.List;

import persistence.model.User;

public interface UserService {
	
	void save(User user);
	void delete(User user);
	boolean checkExists(Long id);
	List<User> findAll();
}
