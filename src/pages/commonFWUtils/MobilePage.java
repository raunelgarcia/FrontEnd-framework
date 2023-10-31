package pages.commonFWUtils;

import static java.time.Duration.ZERO;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 *
 *
 * <pre>
 *     MobilePage base class for modeling a Mobile screen using the Page Object pattern.
 * </pre>
 */
public abstract class MobilePage extends BasePageClass {

  private WebDriver driver;

  protected MobilePage(WebDriver driver) {

    // This is using a hardcoded value because Appium uses its own FluentWait within
    // AppiumElementLocator class.
    // Any framework on top of Appium would just add its own wait overhead.
    // I don't like that, so I prefer to have Appium timeout quickly and to have the on top
    // framework do the retries
    this.driver = driver;
    if (driver != null) {
      AppiumFieldDecorator decorator = new AppiumFieldDecorator(driver, ZERO);
      PageFactory.initElements(decorator, this);
    }
  }
  public static void waitForVisibility(WebElement element){
    waitFor(ExpectedConditions.visibilityOf(element), HIGH_TIMEOUT, ChronoUnit.SECONDS, true);
  }
}
