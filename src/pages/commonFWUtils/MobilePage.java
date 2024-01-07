package pages.commonFWUtils;

import static java.lang.Math.floor;
import static java.lang.Math.max;
import static java.lang.String.valueOf;
import static java.lang.Thread.sleep;
import static java.time.Duration.ZERO;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;
import static org.openqa.selenium.mobile.NetworkConnection.ConnectionType.ALL;
import static org.openqa.selenium.mobile.NetworkConnection.ConnectionType.NONE;
import static org.openqa.selenium.mobile.NetworkConnection.ConnectionType.WIFI;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.mobile.RemoteNetworkConnection;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.Direction;

/**
 *
 *
 * <pre>
 *     MobilePage base class for modeling a Mobile screen using the Page Object pattern.
 * </pre>
 */
public abstract class MobilePage extends BasePageClass {

  private static final PointerInput FINGER = new PointerInput(Kind.TOUCH, "finger");
  private WebDriver driver;

  protected MobilePage(WebDriver driver) {

    // This is using a hardcoded value because Appium uses its own FluentWait within
    // AppiumElementLocator class.
    // Any framework on top of Appium would just add its own wait overhead.
    // I don't like that, so I prefer to have Appium timeout quickly and to have the on top
    // framework do the retries
    this.driver = driver;
    if (driver != null) {
      AppiumFieldDecorator decorator = new AppiumFieldDecorator(driver, ZERO);
      PageFactory.initElements(decorator, this);
    }
  }

  public static boolean isAndroid() {
    return "ANDROID".equalsIgnoreCase(LocalEnvironment.getAppPlatform());
  }

  public static void waitForProcess(int milliSeconds) {
    try {
      sleep(milliSeconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  public static void waitForVisibility(WebElement element) {
    waitFor(ExpectedConditions.visibilityOf(element), HIGH_TIMEOUT, ChronoUnit.SECONDS, true);
  }

  public static void waitForAnimationToFinish() {
    waitForProcess(1250);
  }

  public AndroidDriver getAndroidDriver() {
    return (AndroidDriver) getDriver();
  }

  public IOSDriver getIOSDriver() {
    return (IOSDriver) getDriver();
  }

  public void disableWifi() {
    simulateNetworkType(NONE);
  }

  public void enableWifi() {
    simulateNetworkType(WIFI);
  }

  public void enableNetwork() {
    simulateNetworkType(ALL);
  }

  public void simulateNetworkType(NetworkConnection.ConnectionType connectionType) {
    System.out.printf("Simulating network connection type: %s%n", connectionType.toString());

    String browser = LocalEnvironment.getAppPlatform();
    if (browser.equals("ANDROID") || browser.equals("IOS")) {
      AppiumDriver appium = (AppiumDriver) getDriver();
      RemoteNetworkConnection networkConnection =
          new RemoteNetworkConnection(appium.getExecuteMethod());
      networkConnection.setNetworkConnection(connectionType);
    }
  }

  public void type(WebElement element, String text, boolean cleanFirst) {
    waitForVisibility(element);
    if (cleanFirst) {
      element.clear();
    }
    element.sendKeys(text);
  }

  public void swipe(Direction direction) {
    swipe(direction, 0.3, 0.7);
  }

  public void swipe(Direction direction, double minYRatio, double maxYRatio) {
    Dimension window = getDriver().manage().window().getSize();
    int width = window.getWidth();
    int height = window.getHeight();
    swipe(direction, width, height, minYRatio, maxYRatio, false);
  }

  public void swipeElement(WebElement me, Direction direction) {
    Dimension window = getDriver().manage().window().getSize();
    int width = window.getWidth();
    int height = window.getHeight();
    int meWidth = me.getLocation().getX();
    int meHeight = me.getLocation().getY();

    switch (direction) {
      case UP:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(meWidth / 2, meHeight),
            new Point(width / 2, height / 2),
            500);
        break;
      case LEFT:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(width / 2, height / 4),
            new Point(meWidth, meHeight),
            500);
        break;
      case RIGHT:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(me.getLocation().getX(), me.getLocation().getY()),
            new Point(width, me.getLocation().getY()),
            500);
        break;
      default:
        break;
    }
  }

  private void swipe(
      Direction direction,
      int width,
      int height,
      double minYRatio,
      double maxYRatio,
      boolean isByMobileElement) {
    int halfX = isByMobileElement ? (int) width : (int) floor(width / 2.0);
    int halfY = isByMobileElement ? (int) height : (int) floor(height / 2.0);
    int y = (int) floor(height * minYRatio);
    int y2 = (int) floor(height * maxYRatio);

    switch (direction) {
      case UP:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(halfX, y2),
            new Point(halfX, y),
            isAndroid() ? 500 : 1000);
        break;
      case DOWN:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(halfX, y),
            new Point(halfX, y2),
            isAndroid() ? 500 : 1000);
        break;
      case LEFT:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(max(0, width - 10), halfY),
            new Point(10, halfY),
            500);
        break;
      case RIGHT:
        W3cActions.swipe(
            isAndroid() ? getAndroidDriver() : getIOSDriver(),
            new Point(10, halfY),
            new Point(max(0, width - 10), halfY),
            500);
        break;
      default:
        break;
    }

    waitForAnimationToFinish();
  }

  public static <T> T doWithTryCatch(Supplier<T> action, T otherWise) {
    try {
      return action.get();
    } catch (RuntimeException var3) {
      return otherWise;
    }
  }

  public static <T extends WebElement> boolean isVisible(final T e) {
    return doWithTryCatch(e::isDisplayed, false);
  }

  public void scrollToElement(WebElement element, Direction direction, boolean minScroll) {
    long start = System.currentTimeMillis();
    long timeout = 45L;
    while (!isVisible(element) && System.currentTimeMillis() - start < timeout) {
      swipe(direction, 0.4, minScroll ? 0.5 : 0.6);
    }
  }

  public void performAction(Collection<Sequence> actions) {
    if (isAndroid()) getAndroidDriver().perform(actions);
    else getIOSDriver().perform(actions);
  }

  protected void tap(WebElement element) {
    waitForVisibility(element);
    System.out.printf("Tapping on [%s]", element.toString());
    Sequence tap = new Sequence(FINGER, 1);
    tap.addAction(
        FINGER.createPointerMove(
            Duration.ofMillis(0), viewport(), element.getLocation().x, element.getLocation().y));
    tap.addAction(FINGER.createPointerDown(LEFT.asArg()));
    tap.addAction(FINGER.createPointerUp(LEFT.asArg()));
    tap.addAction(FINGER.createPointerUp(LEFT.asArg()));

    performAction(Collections.singletonList(tap));
  }

  protected void tap(int x, int y) {
    System.out.printf("Tapping at (%s,%s)", valueOf(x), valueOf(y));

    Sequence tap = new Sequence(FINGER, 1);
    tap.addAction(FINGER.createPointerMove(Duration.ofMillis(0), viewport(), x, y));
    tap.addAction(FINGER.createPointerDown(LEFT.asArg()));
    tap.addAction(FINGER.createPointerUp(LEFT.asArg()));
    tap.addAction(FINGER.createPointerUp(LEFT.asArg()));

    performAction(Collections.singletonList(tap));
  }

  public void tapBack() {
    System.out.println("Going back");
    getDriver().navigate().back();
  }

  public static class W3cActions {

    public static void swipe(AppiumDriver driver, Point start, Point end, int duration) {

      Sequence swipe =
          new Sequence(FINGER, 1)
              .addAction(
                  FINGER.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
              .addAction(FINGER.createPointerDown(LEFT.asArg()))
              .addAction(
                  FINGER.createPointerMove(
                      ofMillis(duration), viewport(), start.getX(), start.getY()))
              .addAction(FINGER.createPointerDown(LEFT.asArg()));
      driver.perform(Collections.singletonList(swipe));
    }

    public static void tap(AppiumDriver driver, Point point, int duration) {

      Sequence tap =
          new Sequence(FINGER, 1)
              .addAction(
                  FINGER.createPointerMove(ofMillis(0), viewport(), point.getX(), point.getY()))
              .addAction(FINGER.createPointerDown(LEFT.asArg()))
              .addAction(new Pause(FINGER, ofMillis(duration)))
              .addAction(FINGER.createPointerDown(LEFT.asArg()));
      driver.perform(Collections.singletonList(tap));
    }
  }
}
