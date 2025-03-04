package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Helper {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Logger logger;

    public Helper(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.logger = LogManager.getLogger(this);
        PageFactory.initElements(driver, this);
    }
    // в методах храним общую логику над элементами

    public void enterText(WebElement element, String text) {
        element.clear();
        element.click();
        element.sendKeys(text);
    }
}
