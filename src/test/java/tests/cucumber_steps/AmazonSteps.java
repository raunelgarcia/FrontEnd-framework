package tests.cucumber_steps;
import io.cucumber.java.After;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import pages.Amazon;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utilities.DriverConfiguration;


import static org.junit.Assert.assertTrue;

public class AmazonSteps {
    private Amazon controller;
    private WebDriver driver;

    @ParameterType(".*")
    public String productString(String value) {
        return value;
    }

    @Before
    public void start() {
        DriverConfiguration configuration = new DriverConfiguration();
        driver = configuration.getDriver();
        controller = new Amazon(driver);
        controller.acceptCookies();
    }

    @Given("I am on the Amazon website")
    public void iAmOnTheAmazonWebsite() {
        assertTrue(controller.amazonHomePage());
    }

    @When("I look for {productString} in the search bar")
    public void iLookForAProductInTheSearchBar(String product) {
        controller.searchProduct(product);
    }

    @Then("I should be able to go to the {productString}")
    public void iShouldBeAbleToGoToTheProductPage(String productPage) {
        assertTrue(controller.iAmInPage(productPage));
    }
    @And("add a random product to the cart")
    public void addARandomProductToTheCart() {
        controller.clickProduct();
        controller.addToCart();
    }
    @Then ("I should see the product in the cart")
    public void iShouldSeeTheProductInTheCart() {
        controller.clickCart();
        assertTrue(controller.checkProductInCart());
    }
    @After
    public void closeDriver() {
        // Accessibility.checkAccessibility(driver);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
