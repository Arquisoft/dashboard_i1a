package ccmber.steps;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import asw.hello.MainController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProposalSteps {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

//    private ConfigurableApplicationContext appContext;
    private WebDriver driver = new HtmlUnitDriver();
    private String baseUrl = "http://localhost:8080/";
//    private Map<String, String> users = new HashMap<>();

    @Before
    public void setUp() {
	driver.get(baseUrl);
    }

    @After
    public void tearDown() throws Exception {
	driver.quit();
    }

    @Given("^a list of users:$")
    public void a_list_of_users(List<User> users) throws Throwable {
	for (User u : users) {
	    LOG.debug("Inserting user..." + u.name + " - " + u.password);
	}
    }

    @When("a proposal's linked is clicked$")
    public void i_login_with_name_and_password(String name, String password) throws Throwable {
	LOG.debug("Clicking on a proposal's link");
	driver.findElement(By.xpath("//*[@id='link']")).click();
    }

    @Then("^I can see \"([^\"]*)\"$")
    public void i_can_see(String text) throws Throwable {
	LOG.debug("Checking text present: " + text);
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
	Assert.assertTrue("Text not found!", list.size() > 0);
    }

    public static class User {
	private String name;
	private String password;
    }
}
