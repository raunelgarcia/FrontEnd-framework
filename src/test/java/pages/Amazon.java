package pages;
import static strategies.VisibilityStrategy.isVisible;
import java.time.Duration;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class Amazon {

    @FindBy(id = "sp-cc-accept")
    WebElement acceptCookies;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBar;
    private final WebDriver driver;
    public Amazon(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);
    }
    public void acceptCookies () {
        if (isVisible(acceptCookies))
            acceptCookies.click();
    }
    public void searchProduct(String product) {
        searchBar.sendKeys(product);
        searchBar.submit();
    }
    public boolean amazonHomePage() {
        String expectedTitle = "Amazon.es: compra online de electrónica, libros, deporte, hogar, moda y mucho más.";
        return driver.getTitle().contains(expectedTitle);
    }
    public boolean iAmInPage(String productPage) {
        return driver.getTitle().contains(productPage);
    }


}
