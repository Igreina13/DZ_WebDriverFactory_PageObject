package factory;

import exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {

    private String browser = System.getProperty("browser", "chrome");

    public WebDriver create(String mode) {
        WebDriver driver;

        switch (browser) {

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                //Если начинается с "-" передаём в options
                if (mode.charAt(0) == '-') options.addArguments(mode);
                driver = new FirefoxDriver(options);

                if (mode.equals("maximize")) {
                    driver.manage().window().maximize();
                }
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                if (mode.charAt(0) == '-') options.addArguments(mode);

                if (mode.equals("maximize")) {
                    options.addArguments("start-maximized");
                }
                driver = new ChromeDriver(options);
            }
            default -> {
                throw new RuntimeException(String.format("Browser <%s> is not supported by the factory", browser));
            }
        }

        //неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        return driver;
    }

    public void webDriverManagerSetup() {
        switch (browser) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                return;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                return;
            }
        }
        throw new BrowserNotSupportedException(browser);
    }
}
