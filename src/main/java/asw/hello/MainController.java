package asw.hello;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import asw.persistence.model.Association;
import asw.persistence.model.Comment;
import asw.persistence.model.Proposal;
import asw.persistence.model.Topic;
import asw.persistence.model.User;

@Controller
@RequestMapping("/")
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);
	private List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());

	private Map<String, Proposal> proposals = generateProposals();
	private String currentPage = "login";

	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("credentials", new UserCredentials());
		return getCurrentPage();
	}

	@PostMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
	public String login(@ModelAttribute UserCredentials credentials, Model model
			, HttpServletRequest request) {
		if(!request.getSession().isNew()){
			setCurrentPage("login");
			request.getSession().invalidate();
		}
		String username = credentials.getUsername();
		String pass = credentials.getPassword();
		model.addAttribute("credentials", credentials);

		if (username.equals("admin") && pass.equals("admin")) {
			request.getSession().setAttribute("loggedUser", "admin");
			setCurrentPage("admin");
		} else if (username.equals("council") && pass.equals("council")) {
			request.getSession().setAttribute("loggedUser", "council");
			setCurrentPage("council");
		} else
			setCurrentPage("error");
		return getCurrentPage();
	}
	
	@GetMapping(value = "/admin", produces = MediaType.TEXT_HTML_VALUE)
	public String admin(Model model){
		return getCurrentPage();
	}
	
	@GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
	public String back(Model model){
		UserCredentials credentials = new UserCredentials();
		model.addAttribute("credentials", credentials);
		return getCurrentPage();
	}

	@RequestMapping("/viewProposal")
	public String viewProposal(Model model, Long id) {
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

		model.addAttribute("proposal", p1);

		setCurrentPage("viewProposal");
		
		return getCurrentPage();
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

	public List<SseEmitter> getSseEmitters() {
		return sseEmitters;
	}

	public void setSseEmitters(List<SseEmitter> sseEmitters) {
		this.sseEmitters = sseEmitters;
	}
	
	public void setCurrentPage(String currentPage){
		this.currentPage = currentPage;
	}
	
	public String getCurrentPage(){
		return this.currentPage;
	}

}