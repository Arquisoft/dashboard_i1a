package hello;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import asw.hello.Application;
import asw.hello.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@SuppressWarnings({ "deprecation", "unused" })
public class MainControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), containsString("Votations live broadcast"));
		assertThat(response.getBody(), containsString("Number"));
		assertThat(response.getBody(), containsString("Votes"));
		assertThat(response.getBody(), containsString("Proposal"));
		assertThat(response.getBody(), containsString("Topic"));
	}

	@Test
	public void getUser() throws Exception {
		String userURI = base.toString() + "/user";
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		UserInfo expected = new UserInfo("pepe", 0);
	}

	@Test
	public void testLoginCorrect() {
		String body = "";
		try {
			MvcResult result = this.mockMvc.perform(
					post("/login").param("username", "admin").param("password", "admin")
					.accept(MediaType.TEXT_HTML_VALUE)).andExpect(status().isOk())
					.andReturn();
			body = result.getResponse().getContentAsString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// An HTML
		assertTrue(body.startsWith("<!DOCTYPE HTML>"));
		// Not a JSON
		assertTrue(!body.startsWith("{"));
		assertTrue(!body.endsWith("}"));
		// Not an XML
		assertTrue(!body.startsWith("<user>"));

		Assert.hasText(body, "Votations live broadcast");
		Assert.hasText(body, "Number");
		Assert.hasText(body, "Votes");
		Assert.hasText(body, "Proposal");
		Assert.hasText(body, "Topic");
	}
	
	@Test
	public void testLoginWrong() {
		String body = "";
		try {
			MvcResult result = this.mockMvc.perform(
					post("/login").param("username", "chorizo").param("password", "pamplona")
					.accept(MediaType.TEXT_HTML_VALUE)).andExpect(status().isOk())
					.andReturn();
			body = result.getResponse().getContentAsString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// An HTML
		assertTrue(body.startsWith("<!DOCTYPE HTML>"));
		// Not a JSON
		assertTrue(!body.startsWith("{"));
		assertTrue(!body.endsWith("}"));
		// Not an XML
		assertTrue(!body.startsWith("<user>"));

		Assert.hasText(body, "User");
		Assert.hasText(body, "Password");
		Assert.hasText(body, "The user or password was not correct");
	}
}
