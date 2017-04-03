package services;

import java.util.List;

import persistence.model.Proposal;

public interface ProposalService {
	
	void save(Proposal proposal);
	boolean checkExists(Long id);
	List<Proposal> findAll();
	void delete(Proposal proposal);

}
