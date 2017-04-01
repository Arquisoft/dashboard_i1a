package persistence.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import persistence.model.Proposal;

@Transactional
public interface ProposalDao extends CrudRepository<Proposal, Long> {
    
    public Proposal findByIdProposal(String IdProposal);

}
