package persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByEmail(String email);

}