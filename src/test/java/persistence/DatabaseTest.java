package persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import asw.hello.Application;
import asw.persistence.model.Comment;
import asw.persistence.model.Proposal;
import asw.persistence.model.User;
import asw.persistence.model.Vote;
import asw.persistence.model.VoteType;
import asw.services.CommentService;
import asw.services.ProposalService;
import asw.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@Component
public class DatabaseTest {

	// @Value("${local.server.port}")
	// private int port;
	// private URL base;
	private User u1, u2, u3, u4;
	private Proposal p1, p2, p3, p4;
	private Comment c1, c2, c3, c4;
	@Autowired
	private UserService uS;
	@Autowired
	private CommentService cS;
	@Autowired
	private ProposalService pS;

	@Before
	public void setUp() {
		initializeData();
		try{
			addUsers();
			addProposals();
			addComments();
		} catch(DataIntegrityViolationException e){
			System.out.println("Reinserting data");
		}
	}
	
	@Test
	public void testServices() {
		assertTrue(uS.findAll().size() == 4);
		assertTrue(uS.checkExists(u1.getId()));
		assertTrue(uS.checkExists(u2.getId()));
		assertTrue(uS.checkExists(u3.getId()));
		assertTrue(uS.checkExists(u4.getId()));

		// Proposals

		assertTrue(pS.findAll().size() == 4);
		assertTrue(pS.checkExists(p1.getId()));
		assertTrue(pS.checkExists(p2.getId()));
		assertTrue(pS.checkExists(p3.getId()));
		assertTrue(pS.checkExists(p4.getId()));

		// Comments

		assertTrue(cS.findAll().size() == 4);
		assertTrue(cS.checkExists(c1.getId()));
		assertTrue(cS.checkExists(c2.getId()));
		assertTrue(cS.checkExists(c3.getId()));
		assertTrue(cS.checkExists(c4.getId()));
		
		// Making Proposals
		
		makeProposals();
		assertTrue(u1.getProposals().size()==3);
		assertTrue(u2.getProposals().size()==1);
		
		// Commenting
		comments();
		assertTrue(u1.getComments().size()==3);
		assertTrue(p1.getComments().size()==2);
		assertTrue(p2.getComments().size()==1);
		assertTrue(p3.getComments().size()==1);
		assertEquals(u2.getComments().size(),2);
		//assertEquals(u3.getComments().size(),2);
		
		// Voting
		vote();
		assertEquals(u1.getVotes().size(),2);
		assertEquals(p1.getVotes().size(),1);
		assertEquals(p2.getVotes().size(),2);
		assertEquals(2,p2.getVotes().size());
		
	}	

	private void initializeData() {
		initializeUsers();
		initializeProposals();
		initializeComments();
	}

	private void initializeUsers() {
		u1 = new User("Gonzalo", "Menéndez Borge", "contraseña1", "mail1", "Spain", "1111", "Address1", new Date());
		u2 = new User("Jorge", "López Alonso", "contraseña2", "mail2", "Spain", "2222", "Address2", new Date());
		u3 = new User("Julián", "García Murias", "contraseña3", "mail3", "Spain", "3333", "Address3", new Date());
		u4 = new User("Sergio", "Mosquera Dopico", "contraseña4", "mail4", "Spain", "4444", "Address4", new Date());
	}

	private void initializeProposals() {
		p1 = new Proposal(u1, "Let me return to Ireland", "I was happy there and my friend Ortin is still there");
		p2 = new Proposal(u2, "Take SDI easier", "We think that this subject is so hard");
		p3 = new Proposal(u3, "Fire Fernando Hierro",
				"If he keeps training Real Oviedo we'll never move into Liga Santander");
		p4 = new Proposal(u4, "University bus to Piedrasblancas each half an hour",
				"Is horrible when you have to wait an hour for the bus because the ASW presentations took too long");
	}

	private void initializeComments() {
		c1 = new Comment("Gonzalo you can't leave us yet, we still have another ASW deliverable", u2, p1);
		c2 = new Comment("Jorge leave that and let's go eating a slice of spanish tortilla on San Fernando", u3, p2);
		c3 = new Comment("The coach is not important while Jon Erice keeps playing", u4, p3);
		c4 = new Comment("You Piedrasblancas people always complaining...", u1, p4);
	}

	private void addUsers() {
		uS.save(u1);
		uS.save(u2);
		uS.save(u3);
		uS.save(u4);		
	}

	private void addProposals() {
		pS.save(p1);
		pS.save(p2);
		pS.save(p3);
		pS.save(p4);
	}

	private void addComments() {
		cS.save(c1);
		cS.save(c2);
		cS.save(c3);
		cS.save(c4);
	}
	
	private void makeProposals(){
		u1.makeProposal(p1);
		u2.makeProposal(p2);
		u1.makeProposal(p3);
		u1.makeProposal(p4);
	}
	
	private void comments(){
		u1.comment(p1, c1);
		u1.comment(p2, c2);
		u2.comment(p3, c3);
		u3.comment(p1, c3);
	}
	
	private void vote(){
		u1.vote(new Vote(u1,p1,VoteType.POSITIVE), p1);
		u1.vote(new Vote(u1,p2,VoteType.POSITIVE), p2);
		u3.vote(new Vote(u3,p2,VoteType.POSITIVE), p2);
	}

}
