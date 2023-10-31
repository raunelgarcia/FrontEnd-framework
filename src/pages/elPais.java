package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import java.time.temporal.ChronoUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.commonFWUtils.MobilePage;

public class elPais extends MobilePage {

  @AndroidFindBy(id="com.elpais.elpais:id/title")
  WebElement title;

  @AndroidFindBy(xpath="(//*[@class='android.widget.Button'])[1]")
  WebElement popout;


 //private By title= By.id("title");

 // private By popout= By.xpath("(//*[@class='android.widget.Button'])[1]");

public void clickSpain(){
waitFor(ExpectedConditions.visibilityOf(title),HIGH_TIMEOUT,ChronoUnit.SECONDS,false);
  title.click();
}

  public void handlePopout(){
    waitFor(ExpectedConditions.visibilityOf(popout),HIGH_TIMEOUT,ChronoUnit.SECONDS,true);
    popout.click();
  }
}
