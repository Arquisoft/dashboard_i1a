package persistence.repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import persistence.model.Proposal;

@Transactional
public interface ProposalRepository extends CrudRepository<Proposal, Long> {
    
    public Proposal findByIdProposal(String IdProposal);

}
