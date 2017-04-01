package persistence.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import persistence.model.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    
    public User findByEmail(String email);

}