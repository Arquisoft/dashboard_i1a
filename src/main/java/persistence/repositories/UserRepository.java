package persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import persistence.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByEmail(String email);

}