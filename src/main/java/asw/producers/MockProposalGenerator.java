package asw.producers;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.services.ProposalService;

@Component
public class MockProposalGenerator {
	
	@SuppressWarnings("unused")
	@Autowired
	private ProposalService proposalRepository;
	
	/**
	 * Generates a random vote of the form idProposal;symbol where wymbol is either "+" or "-".
	 * 
	 * The id of the proposal as well as the symbol are chosen randomly.
	 * 
	 * @return A String representing a vote.
	 */
	public String generate(){
		Random r = new Random();
//		int randomPos = r.nextInt(proposalRepository.findAll().size());
//		String vote = String.valueOf(proposalRepository.findAll().get(randomPos).getId());
		
		String vote = String.valueOf(r.nextLong());
		
		vote += ";";
		vote += System.currentTimeMillis()%2 == 0 ? "+" : "-";

    	return vote;
	}

}
