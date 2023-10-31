package pages.commonFWUtils;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.util.Optional.empty;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.LoggerFactory;

public class BasePageClass {

  private WebDriver driver;

  public static final String TIMEOUT_MESSAGE = "Test timed out after waiting";


  public static final long LOW_TIMEOUT = 2;

  public static final long MEDIUM_TIMEOUT = 4;

  public static final long HIGH_TIMEOUT = 8;

  public static final long SUPER_HIGH_TIMEOUT = 12;

  public static final long PRO_TIMEOUT = 29;

  public  BasePageClass() {
   driver=getDriver();
  }
  public WebDriver getDriver() {
    return new WebDriver() {
      @Override
      public void get(String url) {

      }

      @Override
      public String getCurrentUrl() {
        return null;
      }

      @Override
      public String getTitle() {
        return null;
      }

      @Override
      public List<WebElement> findElements(By by) {
        return null;
      }

      @Override
      public WebElement findElement(By by) {
        return null;
      }

      @Override
      public String getPageSource() {
        return null;
      }

      @Override
      public void close() {

      }

      @Override
      public void quit() {

      }

      @Override
      public Set<String> getWindowHandles() {
        return null;
      }

      @Override
      public String getWindowHandle() {
        return null;
      }

      @Override
      public TargetLocator switchTo() {
        return null;
      }

      @Override
      public Navigation navigate() {
        return null;
      }

      @Override
      public Options manage() {
        return null;
      }
    };
  }

  public <K> Optional<K> waitFor(
      ExpectedCondition<K> condition, long time, TemporalUnit unit, boolean shouldFail) {
    try {
      K result =
          new FluentWait<>(driver)
              .pollingEvery(Duration.ofMillis(1000))
              .withTimeout(Duration.of(time, unit))
              .withMessage(condition.toString())
              .ignoring(NoSuchElementException.class)
              .ignoring(StaleElementReferenceException.class)
              .until(
                  new ExpectedCondition<K>() {
                    final long start = currentTimeMillis();

                    @Override
                    public K apply(@Nullable WebDriver driver) {
                      if (true) {
                        long remainingTimeMs =
                            Duration.of(time, unit).toMillis() - (currentTimeMillis() - start);

                           System.out.printf(
                               "Remaining time for condition: %d ms. Condition is: %s%n",
                               remainingTimeMs, condition);
                      }
                      return condition.apply(driver);
                    }
                  });
      return Optional.of(result);
    } catch (TimeoutException toe) {
      if (shouldFail) {
        throw new FrameworkTimeoutException(condition.toString(), time, unit);
      }
    }
    return empty();
  }
}
