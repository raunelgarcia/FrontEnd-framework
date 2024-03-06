package pages;
import static strategies.VisibilityStrategy.isVisible;
import java.time.Duration;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class Amazon {

    @FindBy(id = "sp-cc-accept")
    WebElement acceptCookies;

    @FindBy(id = "twotabsearchtextbox")
    WebElement searchBar;

    @FindBy(xpath = "//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[7]/div/div/span/div")
    WebElement randomProduct;

    @FindBy(xpath = "//*[@id=\"activeCartViewForm\"]/div[2]")
    WebElement productInCart;

    @FindBy(id = "add-to-cart-button")
    WebElement addToCartButton;

    @FindBy (id = "nav-cart")
    WebElement cartButton;
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

    public void clickProduct() {
        randomProduct.click();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void clickCart() {
        cartButton.click();
    }

    public boolean checkProductInCart() {
        return isVisible(productInCart);
    }

}
