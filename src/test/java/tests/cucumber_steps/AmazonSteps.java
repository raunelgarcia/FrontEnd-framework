package tests.cucumber_steps;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import pages.AmazonBasePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utilities.AllureReport;
import utilities.DriverConfiguration;
import pages.AmazonCartPage;
import pages.AmazonProductPage;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class AmazonSteps {
    private AmazonBasePage amazonBasePage;
    private WebDriver driver;

    @ParameterType(".*")
    public String productString(String value) {
        return value;
    }

    @Before
    public void start() {
        DriverConfiguration configuration = new DriverConfiguration();
        driver = configuration.getDriver();
        amazonBasePage = new AmazonBasePage(driver);
        amazonBasePage.acceptCookies();
    }

    @Given("I am on the Amazon website")
    public void iAmOnTheAmazonWebsite() {
        assertTrue(amazonBasePage.amazonHomePage());
    }

    @When("I look for {productString} in the search bar")
    public void iLookForAProductInTheSearchBar(String product) {
        amazonBasePage.searchProduct(product);
    }

    @Then("I should be able to go to the {productString}")
    public void iShouldBeAbleToGoToTheProductPage(String productPage) {
        assertTrue(amazonBasePage.iAmInPage(productPage));
    }

    @And("add a random product to the cart")
    public void addARandomProductToTheCart() {
        amazonBasePage.clickProduct();
        AmazonProductPage.addToCart();
    }

    @Then("I should see the product in the cart")
    public void iShouldSeeTheProductInTheCart() {
        amazonBasePage.clickCart();
        assertTrue(AmazonCartPage.checkProductInCart());
    }

    @After
    public void closeDriver() throws IOException {
        // Accessibility.checkAccessibility(driver);
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
    @AfterAll
    public static void allure() throws IOException {
        AllureReport.allureCommands();
    }
}
