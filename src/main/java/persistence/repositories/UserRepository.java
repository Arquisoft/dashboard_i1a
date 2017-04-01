package persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import persistence.model.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    
    public User findByEmail(String email);

}