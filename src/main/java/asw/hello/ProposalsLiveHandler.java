package asw.hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import asw.persistence.model.Proposal;
import asw.services.ProposalService;

@Component
public class ProposalsLiveHandler {

	private static final Logger logger = Logger.getLogger(MainController.class);
	
	private Map<Long, Proposal> proposals;// = generateProposals();
	
	@Autowired
	private ProposalService pService;
		
	
	@KafkaListener(topics = "newVote")
	public void listen(String data) {
//		String[] contents = data.split(";");
//
//		if (contents.length != 2)
//			return;
//
//		Proposal p;
//		int newVote;
//
//		if (proposals.containsKey(contents[0]))
//			p = proposals.get(contents[0]);
//		else {
//			p = new Proposal();
//			p.setTitle(contents[0]);
//			proposals.put(p.getId(), p);
//		}
//
//		if (contents[1].equals("+"))
//			newVote = +1;
//		else if (contents[1].equals("-"))
//			newVote = -1;
//		else
//			newVote = 0;
//
//		p.setNumberOfVotes(p.getNumberOfVotes() + newVote);
//
//		logger.info("New message received: \"" + data + "\"");
	}
	
	
	@PostConstruct 
	private void updateProposalsFromDatabase() {
		Map<Long, Proposal> proposalsMap = new HashMap<Long, Proposal>();

		List<Proposal> proposalsList = pService.findAll();
		
		for(Proposal p:proposalsList)
			proposalsMap.put(p.getId(), p);

		this.proposals=proposalsMap;
		logger.info("Loading proposals from the database");
	}


	public Map<Long, Proposal> getMap() {
		return proposals;
	}
	
}
