package persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import persistence.model.Proposal;
import persistence.model.User;

@Transactional
public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    
    public Proposal findByUser(User user);

}
