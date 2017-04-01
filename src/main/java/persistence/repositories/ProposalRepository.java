package persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import persistence.model.Proposal;
import persistence.model.User;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    
    public Proposal findByUser(User user);

}
