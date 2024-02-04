package scripts;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import pages.Direction;
import pages.elPais;

public class Testing extends DriverConfig {
  private AppiumDriver driver;

  @Before
  public void init() {
    MutableCapabilities capabilities = generateCapabilities();
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
    for (int i = 0; i < 3; i++) {
      pais.swipe(Direction.UP, driver);
    }
    pais.clickSpain();
  }

  @After
  public void quit() {
    driver.quit();
  }
}
