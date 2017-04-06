package asw.producers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import asw.persistence.model.Proposal;
import asw.persistence.model.Topic;
import asw.services.ProposalService;

/**
 * Simulates user interaction, generating votes and proposals. Useful for Kafka
 * testing.
 */
@Component
public class MockGenerator {

    private static final String MOCK_CONTENT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer ultrices auctor venenatis. Duis tempus mi nec elit condimentum, eu tincidunt urna";
    private static final Random RANDOM = new Random();

    private List<String> titles;

    public MockGenerator() {
	titles = new ArrayList<String>();
	titles.add("Free coffee on Mondays");
	titles.add("3-day weekends");
	titles.add("Why don't we just die already");
	titles.add("Send SDI to the hell");
	titles.add("Make la vida less dura");
	titles.add("MAKE ASW GREAT AGAIN!");
    }

    @Autowired
    private ProposalService proposalRepository;

    /**
     * Simulates the creation of a random vote of the form idProposal;symbol
     * where symbol is either "+" or "-".
     * 
     * The id of the proposal as well as the symbol are chosen randomly.
     * 
     * @return A String representing a vote.
     */
    public String generateVote() {

	int randomPos = RANDOM.nextInt(proposalRepository.findAll().size());
	String vote = String.valueOf(proposalRepository.findAll().get(randomPos).getId());

	vote += ";";
	vote += System.currentTimeMillis() % 2 == 0 ? "+" : "-";

	return vote;
    }

    /**
     * Simulates the generation of a new proposal.
     * 
     * @return The id of the new proposal.
     */
    public Proposal generateProposal() {
	Proposal p = new Proposal();

	p.setTitle(titles.get(RANDOM.nextInt(titles.size())));
	p.setContent(MOCK_CONTENT);
	p.setMinVotes(RANDOM.nextInt(100000));
	p.setTopic(Topic.randomTopic());
	p.setNumberOfVotes(RANDOM.nextInt(100000));

	proposalRepository.save(p);
	return p;
    }
}
