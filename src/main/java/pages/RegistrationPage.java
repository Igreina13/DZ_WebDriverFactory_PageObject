package pages;

import exceptions.DateFormException;
import exceptions.PasswordFailedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationPage extends AbsPages {

    public RegistrationPage(WebDriver driver) {
        super(driver, "/form.html");
    }

    @FindBy(css = "[type='submit']")
    private WebElement btnSubmit;

    public WebElement getLevelLanguage(String level) {
        return driver.findElement(By.cssSelector(String.format("option[value='%s']", level)));
    }

    //Метод ввода текста
    public RegistrationPage setText(IdArgument idArgument, String textValue) {
        enterText(setLocator(idArgument), textValue);
        return this;
    }

    //Метод ввода даты рождения с проверкой
    public RegistrationPage setBirthdate(String text) {
        enterText(setLocator(IdArgument.BIRTHDATE), text);
        if (text.matches("^\\d{2}.\\d{2}.\\d{4}$")) {
            logger.info("Прошла проверка на формат даты");
        } else throw new DateFormException(text);
        return this;
    }

    //Метод ввода кредов с проверкой
    public void checkPasswordWithConfirm() {
        String password = setLocator(IdArgument.PASSWORD).getAttribute("value");
        String confirm = setLocator(IdArgument.CONFIRM_PASSWORD).getAttribute("value");

        if (password.equals(confirm))
            logger.info("Пароли верны");
        else
            throw new PasswordFailedException(password, confirm);
    }

    //парсинг даты
    public String parseDate(String dateB) {
        String[] parts = dateB.split("\\.");
        return String.format("%s-%s-%s", parts[2], parts[1], parts[0]);
    }

    public void setLevelLanguage(String levelLanguage) {

        if (!getLevelLanguage(levelLanguage).isSelected()) {
            setLocator(IdArgument.LANGUAGE_LEVEL).click();
            getLevelLanguage(levelLanguage).click();
            webDriverWait.until(ExpectedConditions.elementToBeSelected(getLevelLanguage(levelLanguage)));
            logger.info("Выбран элемент в выпадающем окне? - " + getLevelLanguage(levelLanguage).isSelected());
        } else
            logger.info("Элемент уже выбран");
    }

    public RegistrationPage clickRegistration() {
        btnSubmit.click();
        return this;
    }

    public RegistrationPage checkOutputData(String fullname, String email, String dateB, String levelLanguage) {
        String actualOutput = setLocator(IdArgument.OUTPUT).getText();
        String expectedOutput = String.format(
                "Имя пользователя: %s\nЭлектронная почта: %s\nДата рождения: %s\nУровень языка: %s",
                fullname,
                email,
                parseDate(dateB),
                levelLanguage);
        logger.info(actualOutput);
        logger.info(expectedOutput);
        if (actualOutput.equals(expectedOutput)) logger.warn("Полученные данные корректны");
        else logger.error("Полученные данные НЕ корректны");
        assertThat(actualOutput).isEqualTo(expectedOutput);
        return this;
    }
}
