package persistence;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import hello.Application;
import persistence.model.Comment;
import persistence.model.Proposal;
import persistence.model.User;
import services.impl.CommentServiceImpl;
import services.impl.ProposalServiceImpl;
import services.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class DatabaseTest {

//	@Value("${local.server.port}")
//	private int port;
//	private URL base;
	private User u1, u2, u3, u4;
	private Proposal p1, p2, p3, p4;
	private Comment c1, c2, c3, c4;
	@Autowired
	private UserServiceImpl uS;
	@Autowired
	private CommentServiceImpl cS;
	@Autowired
	private ProposalServiceImpl pS;

	@Before
	public void setUp(){
//		this.base = new URL("http://localhost:" + port + "/");
		
													//Users
		
		u1 = new User((long) 1, "Gonzalo", "Menéndez Borge", "contraseña1", "mail1", "Spain", "1111", "Address1",
				new Date());
		u2 = new User((long) 2, "Jorge", "López Alonso", "contraseña2", "mail2", "Spain", "2222", "Address2",
				new Date());
		u3 = new User((long) 3, "Julián", "García Murias", "contraseña3", "mail3", "Spain", "3333", "Address3",
				new Date());
		u4 = new User((long) 4, "Sergio", "Mosquera Dopico", "contraseña4", "mail4", "Spain", "4444", "Address4",
				new Date());
		
													//Proposals
		
		p1 = new Proposal(u1, "Let me return to Ireland", "I was happy there and my friend Ortin is still there");
		p2 = new Proposal(u2, "Take SDI easier", "We think that this subject is so hard");
		p3 = new Proposal(u3, "Fire Fernando Hierro",
				"If he keeps training Real Oviedo we'll never move into Liga Santander");
		p4 = new Proposal(u4, "University bus to Piedrasblancas each half an hour",
				"Is horrible when you have to wait an hour for the bus because the ASW presentations took too long");
		
													//Comments
		
		c1 = new Comment("Gonzalo you can't leave us yet, we still have another ASW deliverable", u2, p1);
		c2 = new Comment("Jorge leave that and let's go eating a slice of spanish tortilla on San Fernando", u3,
				p2);
		c3 = new Comment("The coach is not important while Jon Erice keeps playing", u4, p3);
		c4 = new Comment("You Piedrasblancas people always complaining...", u1, p4);
	}

	@Test
	public void testUser() {
		assertTrue(uS.findAll().size()==0);
		addUsers();
		assertTrue(uS.findAll().size()==4);
		assertTrue(uS.checkExists(u1.getId()));
		assertTrue(uS.checkExists(u2.getId()));
		assertTrue(uS.checkExists(u3.getId()));
		assertTrue(uS.checkExists(u4.getId()));
		deleteUsers();
		assertTrue(uS.findAll().size()==0);
	}

	@Test
	public void testMakeProposal() {
		addUsers();
		assertTrue(pS.findAll().size()==0);
		addProposals();
		assertTrue(pS.findAll().size()==4);
		assertTrue(pS.checkExists(p1.getId()));
		assertTrue(pS.checkExists(p2.getId()));
		assertTrue(pS.checkExists(p3.getId()));
		assertTrue(pS.checkExists(p4.getId()));
		deleteProposals();
		assertTrue(pS.findAll().size()==0);
	}
	
	@Test
	public void testCommentProposal() {
		addUsers();
		addProposals();
		assertTrue(cS.findAll().size()==0);
		addComments();
		assertTrue(cS.findAll().size()==4);
		assertTrue(uS.checkExists(c1.getId()));
		assertTrue(uS.checkExists(c2.getId()));
		assertTrue(uS.checkExists(c3.getId()));
		assertTrue(uS.checkExists(c4.getId()));
		deleteComments();
		assertTrue(pS.findAll().size()==0);
	}
	
	private void addUsers(){
		uS.save(u1);
		uS.save(u2);
		uS.save(u3);
		uS.save(u4);
	}
	
	private void addProposals(){
		pS.save(p1);
		pS.save(p2);
		pS.save(p3);
		pS.save(p4);
	}
	
	private void addComments(){
		cS.save(c1);
		cS.save(c2);
		cS.save(c3);
		cS.save(c4);
	}
	
	private void deleteUsers(){
		for(User user : uS.findAll())
			uS.delete(user);
	}
	
	private void deleteProposals(){
		for(Proposal prop: pS.findAll())
			pS.delete(prop);
	}
	
	private void deleteComments(){
		for(Comment comment: cS.findAll())
			cS.delete(comment);
	}

}
