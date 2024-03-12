package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static strategies.VisibilityStrategy.isVisible;
import pages.AmazonBasePage;

public class AmazonCartPage {

    @FindBy(xpath = "//*[@id=\"activeCartViewForm\"]/div[2]")
    static WebElement productInCart;

    @FindBy(xpath = "//*[@id=\"sc-active-C131902c4-3d33-4741-b879-7ef467b463f9\"]/div[4]/div/div[2]/div[1]/span[1]/span/span[1]/span/span")
    static WebElement cantButton;

    @FindBy(id = "quantity_2")
    static WebElement quantityButton;

    @FindBy(xpath = "//*[@id=\"sc-subtotal-label-buybox\"]")
    static WebElement productQuantity;

    public static boolean checkProductInCart() {
        return isVisible(productInCart);
    }

    public static void clickCantButton() {
        cantButton.click();
    }

    public static void clickQuantityButton() {
        quantityButton.click();
    }

    public static void checkProductQuantity() {
        isVisible(productQuantity);
    }
}
