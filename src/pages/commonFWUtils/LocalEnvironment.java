//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages.commonFWUtils;

import java.util.Optional;

public class LocalEnvironment  {
  public static final Integer DEFAULT_VERSION = 1;
  private static final String OS = ((String)get("bamboo_TAE_OS_NAME").orElse("nix")).toLowerCase();
  private static final String ARCH = (String)get("bamboo_TAE_OS_ARCH").orElse("");

  public LocalEnvironment() {
  }

  public static boolean isWindows() {
    return OS.contains("win");
  }

  public static boolean isMac() {
    return OS.contains("mac");
  }

  public static boolean isUnix() {
    return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
  }

  public static boolean is64Bits() {
    return ARCH.contains("64");
  }

  public static String getCountry() {
    return ((String)get("bamboo_TAE_COUNTRY").orElse("")).toLowerCase();
  }

  public static String getEnvironment() {
    return ((String)get("bamboo_TAE_ENVIRONMENT").orElse("")).toLowerCase();
  }

  public static String getAppIdentifier() {
    return ((String)get("bamboo_TAE_APP_IDENTIFIER").orElse("")).replace("${country}", getCountry()).replace("${env}", getEnvironment());
  }

  public static String getAppVersion() {
    return ((String)get("bamboo_TAE_APP_VERSION").orElse("")).replace("${env}", getEnvironment());
  }

  public static String getAppFile() {
    return (String)get("local_TAE_APP_FILE").orElse("");
  }

  public static String getAppPlatform() {
    return ((String)get("bamboo_TAE_APP_PLATFORM").orElse("")).toLowerCase();
  }

  public static Boolean isApi() {
    return !isWeb() && !isMobile();
  }

  public static Boolean isWeb() {
    String value = (String)get("bamboo_TAE_APPLICATION").orElse("");
    return !value.isEmpty();
  }

  public static Boolean isMobile() {
    String platformMobile = getAppPlatform();
    return "ANDROID".equalsIgnoreCase(platformMobile) || "AND".equalsIgnoreCase(platformMobile) || "IOS".equalsIgnoreCase(platformMobile);
  }

  public static String getAppPlatformB() {
    return ((String)get("bamboo_TAE_BROWSER").orElse("")).toLowerCase();
  }

  public static String getAppDevice() {
    return ((String)get("bamboo_TAE_APP_DEVICE").orElse("")).toLowerCase();
  }

  public static String getDeviceUdid() {
    return ((String)get("bamboo_TAE_UDID").orElse("")).toLowerCase();
  }

  public static Integer getVersion() {
    return (Integer)get("bamboo_TAE_VERSION").map(Integer::parseInt).orElse(DEFAULT_VERSION);
  }

  public static String getLanguage() {
    return ((String)get("bamboo_TAE_LANGUAGE").orElse("")).toLowerCase();
  }

  public static Optional<String> get(String key) {
    Optional<String> property = Optional.ofNullable(Optional.ofNullable(System.getenv(key)).orElse(System.getProperty(key)));
    if (!property.isPresent()) {
      String inUpperCase = key.toUpperCase();
      property = Optional.ofNullable(Optional.ofNullable(System.getenv(inUpperCase)).orElse(System.getProperty(inUpperCase)));
      if (!property.isPresent()) {
        String inLowerCase = key.toLowerCase();
        property = Optional.ofNullable(Optional.ofNullable(System.getenv(inLowerCase)).orElse(System.getProperty(inLowerCase)));
      }
    }

    return property;
  }
}
