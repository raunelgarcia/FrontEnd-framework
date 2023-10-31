package scripts;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.commonFWUtils.MobilePage;
import pages.elPais;

public class Testing extends MobilePage {
@Before
public void init(){
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("platformName", "Android");
  capabilities.setCapability("udid", "emulator-5554");
  capabilities.setCapability("noReset", "true");
  capabilities.setCapability("app", "/Users/rgarcqui/Desktop/Docker/resources/el-pais-6-11-0.apk"); // Ruta al archivo APK de tu aplicación

  URL appiumServerURL = null; // URL del servidor Appium
  try {
    appiumServerURL = new URL("http://localhost:4723/wd/hub");
  } catch (MalformedURLException e) {
    throw new RuntimeException(e);
  }

  AppiumDriver driver = new AppiumDriver(appiumServerURL, capabilities);
}

  @Test
  public void pruebaAppium() throws MalformedURLException {

        elPais pais=new elPais();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println(driver.getPageSource());
    pais.clickSpain();
    pais.handlePopout();

    driver.quit();
  }
}
