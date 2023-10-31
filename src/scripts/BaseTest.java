package scripts;

import org.openqa.selenium.WebDriver;
import pages.commonFWUtils.MobilePage;

public class BaseTest extends MobilePage {
public WebDriver getDriver(WebDriver driver){
  return driver;
}
}
