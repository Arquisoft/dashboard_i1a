package services;

import java.util.List;

import persistence.model.Proposal;

public interface ProposalService {
	
	void save(Proposal proposal);
	void delete(Proposal proposal);
	boolean checkExists(Long id);
	List<Proposal> findAll();

}
