package utilities;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

  public static void take(WebDriver driver) {
    try {
      File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

      String screenshotDir = "screenshots/";
      new File(screenshotDir).mkdirs();

      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
      String timestamp = dateFormat.format(new Date());
      String screenshotPath = screenshotDir + "screenshot_" + timestamp + ".png";

      Files.copy(
          screenshotFile.toPath(),
          new File(screenshotPath).toPath(),
          StandardCopyOption.REPLACE_EXISTING);
      Logger.infoMessage("Screenshot stored in: " + screenshotPath);
    } catch (Exception e) {
      Logger.errorMessage("Error while taking screenshot: " + e.getMessage());
    }
  }
}
