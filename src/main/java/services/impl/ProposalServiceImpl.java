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

	@Autowired
	private ProposalRepository repository;
	
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

}
