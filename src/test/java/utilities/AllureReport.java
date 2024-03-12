package utilities;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AllureReport {

  public static void fillReportInfo() {
    Allure.addDescription("Browser: " + LocalEnviroment.getBrowser());
  }

  public static void attachScreenshot(WebDriver driver) {
    if (driver instanceof TakesScreenshot) {
      byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", screenshot);
      Allure.getLifecycle().updateTestCase(testResult -> testResult.setStatus(Status.FAILED));
    }
  }

  public static void allureCommands() throws IOException {
      String commandCd = "cmd.exe /c cd C:\\Users\\aalvarti\\IdeaProjects\\FrontEnd-framework";
      String command1 = "cmd.exe /c npx allure-commandline generate target/allure-results";
      String command2 = "cmd.exe /c npx allure-commandline open allure-report";

      Runtime.getRuntime().exec(commandCd);
      Runtime.getRuntime().exec(command1);
      Runtime.getRuntime().exec(command2);
  }
}
