package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.commonFWUtils.MobilePage;

public class elPais extends MobilePage {

  @AndroidFindBy(id = "com.elpais.elpais:id/title")
  WebElement title;

  @AndroidFindBy(xpath = "(//*[@class='android.widget.Button'])[1]")
  WebElement popout;

  public elPais(WebDriver driver) {
    super(driver);
  }

  public void clickSpain() {
   waitForVisibility(title);
    title.click();
  }

  public void handlePopout() {
    waitForVisibility(popout);
    popout.click();
  }
}
