package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AmazonProductPage {

    @FindBy(id = "add-to-cart-button")
    static WebElement addToCartButton;


    public static void addToCart() {
        addToCartButton.click();
    }
}
