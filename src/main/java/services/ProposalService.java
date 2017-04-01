package services;

import java.util.List;

import persistence.model.Proposal;

public interface ProposalService {
	
	void save(Proposal proposal);
	List<Proposal> findAll();

}
