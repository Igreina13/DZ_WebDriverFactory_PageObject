package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Helper;

public abstract class AbsPages extends Helper {

    public AbsPages(WebDriver driver, String pageUrl) {
        super(driver);
        fullPath = baseUrl + pageUrl;
    }

    private String baseUrl = System.getProperty("base.url");
    private String baseLocator = "#%s";
    private String fullPath = null;

    public WebElement setLocator(IdArgument idArgument) {
        return driver.findElement(By.cssSelector(String.format(this.baseLocator, idArgument.getId())));
    }

    public void open() {
        driver.get(fullPath);
    }
}

