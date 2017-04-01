package services.impl;

import java.util.List;

import persistence.model.Proposal;
import persistence.repositories.ProposalRepository;
import services.ProposalService;

public class ProposalServiceImpl implements ProposalService {
	
	private ProposalRepository repository;

	@Override
	public void save(Proposal proposal) {
		repository.save(proposal);

	}

	@Override
	public List<Proposal> findAll() {
		return (List<Proposal>) repository.findAll();
	}

}
