package hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import persistence.model.Association;
import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

	private Map<String, Proposal> proposals = generateProposals();

	@RequestMapping("/live")
	public String landing(Model model) {
		return "index";
	}

	@RequestMapping("/viewProposal")
	public String viewProposal(Model model, Long id) {
		// put the proposal in the map
		Proposal p1 = new Proposal();

		// Comentario de prueba 1
		Comment c1 = new Comment();
		Association.MakeComment.link(new User(), c1, p1);
		c1.setContent("pole");

		// Comentario de prueba 2
		Comment c2 = new Comment();
		c2.setContent("No te lo perdonare Carmena");
		Association.MakeComment.link(new User(), c2, p1);

		model.addAttribute("proposal", p1);
		
		return "viewProposal";
	}

	@KafkaListener(topics = "newVote")
	public void listen(String data) {
		String[] contents = data.split(";");

		if (contents.length != 2)
			return;

		Proposal p;
		int newVote;

		if (proposals.containsKey(contents[0]))
			p = proposals.get(contents[0]);
		else {
			p = new Proposal();
			p.setTitle(contents[0]);
			proposals.put(p.getTitle(), p);
		}

		if (contents[1].equals("+"))
			newVote = +1;
		else if (contents[1].equals("-"))
			newVote = -1;
		else
			newVote = 0;

		p.setNumberOfVotes(p.getNumberOfVotes() + newVote);

		logger.info("New message received: \"" + data + "\"");
	}

	private static Map<String, Proposal> generateProposals() {
		Map<String, Proposal> lista = new HashMap<String, Proposal>();

		Proposal p = new Proposal();
		p.setTitle("tituloPrueba");

		lista.put("tituloPrueba", p);

		return lista;
	}

	@ModelAttribute("proposals")
	public Map<String, Proposal> getProposals() {
		return proposals;
	}

	public void setProposals(Map<String, Proposal> proposals) {
		this.proposals = proposals;
	}

}