package services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import persistence.model.Proposal;
import persistence.repositories.ProposalRepository;
import services.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService{

	private ProposalRepository repository;

	@Autowired
	public ProposalServiceImpl(ProposalRepository repository) {
		this.repository = repository;
	}
	
	public void save(Proposal proposal) {
		repository.save(proposal);
	}

	@Override
	public List<Proposal> findAll() {
		List<Proposal> proposals = new ArrayList<>();
		if (repository.findAll() != null) {
			Iterator<Proposal> it = repository.findAll().iterator();
			while (it.hasNext())
				proposals.add(it.next());
		}
		return proposals;
	}

	@Override
	public boolean checkExists(Long id) {
		return repository.findOne(id) != null;
	}

	@Override
	public void delete(Proposal proposal) {
		repository.delete(proposal);		
	}

}
