import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestingRegistrationAndAuthorization {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://intershop5.skillbox.ru/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private By enterToAccountLocator = By.className("account"); //Локатор кнопки "войти"
    private By registrationNameFieldLocator = By.id("reg_username"); //Локатор поля регистрации "Имя"
    private By registrationEmailFieldLocator = By.id("reg_email"); //Локатор поля регистрации "Е-mail"
    private By registrationPasswordFieldLocator = By.id("reg_password"); //Локатор поля регистрации "Пароль"
    private By registerButtonLocator = By.className("custom-register-button"); //Локатор кнопки регистрации при попадании в авторизацию
    private By registerButton1Locator = By.className("woocommerce-form-register__submit"); //Локатор кнопки регистрации

    private By loginRegistrationEmailFieldLocator = By.id("username"); // Локатор авторизации поля "Имя или E=mail"
    private By loginPasswordFieldLocator = By.id("password"); //Локатор авторизации поля пароль
    private By loginButtonLocator = By.className("woocommerce-form-login__submit"); //Локатор кнопки "Аваторизации"
    private By profileNameLocator = By.cssSelector(".woocommerce-MyAccount-content strong"); //Локатор текста при успешной авторизации

    @Test
    public void loginPage_Register_SuccessfulLogin() {
        //arrange
        var emailElement = "iljatest@test.ru";
        var passwordElement = "tester1337";
        var nameElement = "iljatest";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(loginPasswordFieldLocator).sendKeys(passwordElement);
        driver.findElement(loginButtonLocator).click();
        //assert
        Assertions.assertEquals(nameElement, driver.findElement(profileNameLocator).getText(), "Пользователь не авторизовался");
    }

    private By errorLocator = By.className("woocommerce-error"); //Локатор ошибки отсутствия имени

    @Test
    public void registerPage_RegistrationWithTheWrongName_WrongRegister() {
        //arrange
        var emailElement = "iljatest256@test.ru";
        var passwordElement = "tester1337";
        var nameElement = "Вася";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(registerButtonLocator).click();
        driver.findElement(registrationNameFieldLocator).sendKeys(nameElement);
        driver.findElement(registrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(registrationPasswordFieldLocator).sendKeys(passwordElement);
        driver.findElement(registerButton1Locator).click();
        //assert
        Assertions.assertEquals("Error: Пожалуйста введите корректное имя пользователя.", driver.findElement(errorLocator).getText(), "Пользователь зарегистрирован");
    }

    @Test
    public void registerPage_RegistrationWithoutAPassword_WrongRegister() {
        //arrange
        var emailElement = "iljatest257@test.ru";
        var nameElement = "iljatest257";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(registerButtonLocator).click();
        driver.findElement(registrationNameFieldLocator).sendKeys(nameElement);
        driver.findElement(registrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(registerButton1Locator).click();
        //assert
        Assertions.assertEquals("Error: Введите пароль для регистрации.", driver.findElement(errorLocator).getText(), "Пользователь заркгистрирован");
    }

    @Test
    public void registerPage_RegistrationByRegisteredName_WrongRegister() {
        //arrange
        var emailElement = "iljatest257@test.ru";
        var nameElement = "iljatest8";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(registerButtonLocator).click();
        driver.findElement(registrationNameFieldLocator).sendKeys(nameElement);
        driver.findElement(registrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(registerButton1Locator).click();
        //assert
        Assertions.assertEquals("Error: Учетная запись с таким именем пользователя уже зарегистрирована.", driver.findElement(errorLocator).getText(), "Пользователь заркгистрирован");
    }


    @Test
    public void loginPage_LoginWithoutAName_WrongLogin() {
        //arrange
        var passwordElement = "tester1337";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginPasswordFieldLocator).sendKeys(passwordElement);
        driver.findElement(loginButtonLocator).click();
        //assert
        Assertions.assertEquals("Error: Имя пользователя обязательно.", driver.findElement(errorLocator).getText(), "Пользователь не авторизовался");
    }

    @Test
    public void loginPage_LoginWithoutAPassword_WrongLogin() {
        //arrange
        var emailElement = "iljatest@test.ru";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(loginButtonLocator).click();
        //assert
        Assertions.assertEquals("Пароль обязателен.", driver.findElement(errorLocator).getText(), "Пользователь не авторизовался");
    }

    @Test
    public void loginPage_NotRegisteredLoginByEmail_WrongLogin() {
        //arrange
        var emailElement = "notregister@test.ru";
        var passwordElement = "tester";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(loginPasswordFieldLocator).sendKeys(passwordElement);
        driver.findElement(loginButtonLocator).click();
        //assert
        Assertions.assertEquals("Неизвестный адрес почты. Попробуйте еще раз или введите имя пользователя.", driver.findElement(errorLocator).getText(), "Пользователь не авторизовался");
    }

    @Test
    public void loginPage_NotRegisteredLoginByName_WrongLogin() {
        //arrange
        var emailElement = "notregister";
        var passwordElement = "tester";
        //act
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(emailElement);
        driver.findElement(loginPasswordFieldLocator).sendKeys(passwordElement);
        driver.findElement(loginButtonLocator).click();
        //assert
        Assertions.assertEquals("Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.", driver.findElement(errorLocator).getText(), "Пользователь не авторизовался");
    }

    private By myAccountLocator = By.id("menu-item-30"); //Локатор кнопки "Мой аккаунт"
    private By forgotPasswordLocator = By.cssSelector(".woocommerce-LostPassword a"); //Локатор кнопки "Забыли пароль"
    private By fieldEmailLocator = By.id("user_login"); //Локатор поля email
    private By buttonResetPassword = By.className("woocommerce-Button"); // Локатор кнопка Сброса пароля
    private By massageSuccessfulResetPassword = By.className("woocommerce-message"); //Локаотор сообщения о сброшенном пароле

    @Test
    public void loginPage_RecoveryPassword_SuccessfulRecovery() {
        //arrange
        var emailElement = "iljatest@test.ru";
        //act
        driver.findElement(myAccountLocator).click();
        driver.findElement(forgotPasswordLocator).click();
        driver.findElement(fieldEmailLocator).sendKeys(emailElement);
        driver.findElement(buttonResetPassword).click();
        //assert
        Assertions.assertEquals("Password reset email has been sent.", driver.findElement(massageSuccessfulResetPassword).getText(), "Пользователь не авторизовался");
    }


}






