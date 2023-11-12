package scripts;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import pages.elPais;

public class Testing extends DriverConfig{
  private WebDriver driver;

  @Before
  public void init() {
   MutableCapabilities capabilities=generateCapabilities();
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
