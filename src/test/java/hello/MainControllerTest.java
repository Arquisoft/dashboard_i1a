package hello;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import asw.hello.Application;
import asw.hello.UserCredentials;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
@SuppressWarnings({"deprecation","unused"})
public class MainControllerTest {

    @Value("${local.server.port}")
    private int port;

    private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}

	@Test
	public void getLanding() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		/**
		assertThat(response.getBody(), containsString("Username:"));
		assertThat(response.getBody(), containsString("Password:"));
		assertThat(response.getBody(), containsString("Submit"));
		assertThat(response.getBody(), containsString("Reset"));
		*/	
	}
	
	@Test
	public void getAdmin() throws Exception {		
		UserCredentials admin = new UserCredentials("admin","admin");
		assertEquals("admin",admin.getUsername());
		assertEquals("admin",admin.getPassword());
		base=new URL("http://localhost:" + port + "/admin.html");
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		/**
		assertThat(response.getBody(), containsString("Votations live broadcast"));
		assertThat(response.getBody(), containsString("Number"));
		assertThat(response.getBody(), containsString("Votes"));
		assertThat(response.getBody(), containsString("Proposal"));
		assertThat(response.getBody(), containsString("Topic"));
		*/		
	}
}
