package utilities.logger;

import org.slf4j.Logger;

public class MyLogger implements LoggerProvider {
  private final Logger logger = getLogger();

  public void doSomething() {
    logger.debug("Mensaje de depuración");
    logger.error("Ocurrió un error");
  }
}
