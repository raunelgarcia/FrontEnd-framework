package scripts;

import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import java.nio.file.Paths;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.commonFWUtils.LocalEnvironment;

public class DriverConfig extends LocalEnvironment {

  private MutableCapabilities capabilities=new DesiredCapabilities();

  public MutableCapabilities generateCapabilities() {
    if (LocalEnvironment.getAppPlatform().equalsIgnoreCase("Android")) {
      capabilities.setCapability(PLATFORM_NAME, "Android");
      capabilities.setCapability("appPackage", getAppIdentifier());  // Android
      capabilities.setCapability("automationName", "UiAutomator2");
      capabilities.setCapability("app",  Paths.get("src/resources/" + getAppFile()).toAbsolutePath().toString());
    } else  {
      capabilities.setCapability("automationName", "XCuiTest");
      capabilities.setCapability(PLATFORM_NAME, "iOS");
      capabilities.setCapability("bundleId", getAppIdentifier());
      capabilities.setCapability("udid", getDeviceUdid()); // iOS
      // Agrega más capacidades específicas de iOS aquí
    }
    capabilities.setCapability("deviceName", "Iphone");
      // Ruta a la APK de Android o al archivo .ipa de iOS

    return capabilities;
  }
}
