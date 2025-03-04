
import com.github.javafaker.Faker;
import factory.WebDriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.IdArgument;
import pages.RegistrationPage;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FullAuthTests {
    private WebDriver driver;
    private Faker faker = new Faker();
    private static final Logger logger = LogManager.getLogger(FullAuthTests.class);
    private RegistrationPage registrationPage;
    private static WebDriverFactory webDriverFactory = new WebDriverFactory();

    @BeforeAll
    public static void init() {
        webDriverFactory.webDriverManagerSetup();
    }

    @BeforeEach
    public void startDriver() {
        logger.info("Открытие браузера");
        driver = new WebDriverFactory().create("maximize");
        registrationPage = new RegistrationPage(driver);
        registrationPage.open();
    }

    /**
     * Задание №1
     * Проверка пароля: Добавить логику для проверки
     * совпадения пароля и его подтверждения.
     * Тестирование кнопки отправки: Убедиться, что после нажатия кнопки
     * данные корректно выводятся на страницу.
     */

    @Test
    @DisplayName("Регистрационная форма:заполнение")
    public void resultTest() {
        String fullname = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String dateB = "13.05.1986";
        String password = System.getProperty("password", "7774");
        String languageLevel = "beginner";

        logger.info("Переход по ссылке");
        registrationPage.open();
        registrationPage.setText(IdArgument.USERNAME, fullname)
                .setText(IdArgument.EMAIL, email)
                .setText(IdArgument.PASSWORD, password)
                .setText(IdArgument.CONFIRM_PASSWORD, password)
                .setBirthdate(dateB)
                .checkPasswordWithConfirm();
        registrationPage.setLevelLanguage(languageLevel);
        registrationPage.clickRegistration().checkOutputData(fullname, email, dateB, languageLevel);
    }

    @AfterEach
    void tearDown() {
        logger.info("Закрытие браузера и сессии драйвера");
        if (driver != null) driver.quit();
    }
}

