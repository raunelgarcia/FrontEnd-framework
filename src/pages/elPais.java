package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.commonFWUtils.MobilePage;

public class elPais extends MobilePage {

  @AndroidFindBy(id = "com.elpais.elpais:id/title")
  @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell)[1]")
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

  public void clickhandlePopout() {
    waitForVisibility(popout);
    popout.click();
  }
}
