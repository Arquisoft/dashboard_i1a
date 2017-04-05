package asw.hello;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import asw.persistence.model.Association;
import asw.persistence.model.Comment;
import asw.persistence.model.Proposal;
import asw.persistence.model.Topic;
import asw.persistence.model.User;
import asw.services.ProposalService;

@Component
@Scope("singleton")
public class ProposalsLiveHandler {

	private static final Logger logger = Logger.getLogger(MainController.class);

	private Map<Long, Proposal> proposals;// = generateProposals();

	@Autowired
	private ProposalService pService;

	@KafkaListener(topics = "newVote")
	public void listen(String data) {
		String[] contents = data.split(";");

		if (contents.length != 2)
			return;

		Proposal p;
		int newVote;

		if (proposals.containsKey(Long.parseLong(contents[0]))) {
			p = proposals.get(Long.parseLong(contents[0]));

			if (contents[1].equals("+"))
				newVote = +1;
			else if (contents[1].equals("-"))
				newVote = -1;
			else
				newVote = 0;

			p.setNumberOfVotes(p.getNumberOfVotes() + newVote);
		}
		// else {
		// p = new Proposal();
		// p.setTitle(contents[0]);
		// proposals.put(p.getId(), p);
		// }

		logger.info("New message received: \"" + data + "\"");
	}

	@PostConstruct
	private void updateProposalsFromDatabase() {

		Proposal p1 = new Proposal();

		p1.setTitle("Liberate snakes through the city");
		p1.setContent("We all hate rats, we should set" + " some snakes free to eat them, once"
				+ " the rats are extinct we can throw the snakes in Gijón");
		p1.setMinVotes(1000);
		p1.setTopic(Topic.HEALTHCARE);
		p1.setNumberOfVotes(890);

		Comment c1 = new Comment();
		Association.MakeComment.link(new User(), c1, p1);
		c1.setContent("pole");

		Comment c2 = new Comment();
		c2.setContent("No te lo perdonare Carmena");
		Association.MakeComment.link(new User(), c2, p1);

		pService.save(p1);

		Map<Long, Proposal> proposalsMap = new HashMap<Long, Proposal>();

		List<Proposal> proposalsList = pService.findAll();

		for (Proposal p : proposalsList)
			proposalsMap.put(p.getId(), p);

		this.proposals = proposalsMap;
		logger.info("Loading proposals from the database");

	}

	public Map<Long, Proposal> getMap() {
		return proposals;
	}

}
