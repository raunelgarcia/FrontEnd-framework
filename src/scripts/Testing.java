package scripts;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.elPais;

public class Testing {
  private WebDriver driver;

  @Before
  public void init() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("udid", "emulator-5554");
    capabilities.setCapability("noReset", "true");
    capabilities.setCapability(
        "app",
        "/Users/rgarcqui/Desktop/Docker/resources/el-pais-6-11-0.apk"); // Ruta al archivo APK de tu
    // aplicación

    try {
      // URL del servidor Appium
      URL appiumServerURL = new URL("http://localhost:4723/wd/hub");
      driver = new AppiumDriver(appiumServerURL, capabilities);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void pruebaAppium() {
    elPais pais = new elPais(driver);
    pais.clickSpain();
  }

  @After
  public void quit(){
    driver.quit();
  }
}
