package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationAndAuthorizationPage {
    private WebDriver driver;
    public HeaderPanel header;

    @FindBy(className = "account")//Локатор кнопки "войти"
    public WebElement enterToAccount;
    @FindBy(id = "reg_username")//Локатор поля регистрации "Имя"
    public WebElement registrationNameField;
    @FindBy(id = "reg_email")//Локатор поля регистрации "Е-mail"
    public WebElement registrationEmailField;
    @FindBy(id = "reg_password")//Локатор поля регистрации "Пароль"
    public WebElement registrationPasswordField;
    @FindBy(className = "custom-register-button")//Локатор кнопки регистрации при попадании в авторизацию
    public WebElement registerButton;
    @FindBy(className = "woocommerce-form-register__submit")//Локатор кнопки регистрации
    public WebElement registerButton1;
    @FindBy(id = "username")// Локатор авторизации поля "Имя или E=mail"
    public WebElement loginRegistrationEmailField;
    @FindBy(id = "password")//Локатор авторизации поля пароль
    public WebElement loginPasswordField;
    @FindBy(className = "woocommerce-form-login__submit")//Локатор кнопки "Аваторизации"
    public WebElement loginButton;
    @FindBy(css = ".woocommerce-MyAccount-content strong")//Локатор текста при успешной авторизации
    public WebElement profileName;
    @FindBy(className = "woocommerce-error")//Локатор ошибки отсутствия имени
    public WebElement error;
    @FindBy(css = ".woocommerce-LostPassword a")//Локатор кнопки "Забыли пароль"
    public WebElement forgotPassword;
    @FindBy(id = "user_login")//Локатор поля email
    public WebElement fieldEmail;
    @FindBy(className = "woocommerce-Button")// Локатор кнопка Сброса пароля
    public WebElement buttonResetPassword;
    @FindBy(className = "woocommerce-message")//Локаотор сообщения о сброшенном пароле
    public WebElement massageSuccessfulResetPassword;

    public RegistrationAndAuthorizationPage(WebDriver driver) {
        this.driver = driver;
        header = new HeaderPanel(driver);
        PageFactory.initElements(driver,this);
    }

    public void successfulLogin() {
        var emailElement = "iljatest@test.ru";
        var passwordElement = "tester1337";

        loginRegistrationEmailField.sendKeys(emailElement);
        loginPasswordField.sendKeys(passwordElement);
    }
    public void wrongNameWrongRegister() {
        var emailElement = "iljatest256@test.ru";
        var passwordElement = "tester1337";
        var nameElement = "Вася";

        registrationNameField.sendKeys(nameElement);
        registrationEmailField.sendKeys(emailElement);
        registrationPasswordField.sendKeys(passwordElement);
    }
    public void withoutAPasswordWrongRegister() {
        var emailElement = "iljatest257@test.ru";
        var nameElement = "iljatest257";

        registrationNameField.sendKeys(nameElement);
        registrationEmailField.sendKeys(emailElement);
    }
    public void registrationByRegisteredName_WrongRegister() {
        var emailElement = "iljatest257@test.ru";
        var nameElement = "iljatest8";

        registrationNameField.sendKeys(nameElement);
        registrationEmailField.sendKeys(emailElement);
    }
    public void withoutAName_WrongLogin() {
        var passwordElement = "tester1337";

        loginPasswordField.sendKeys(passwordElement);
    }
    public void loginWithoutAPassword_WrongLogin() {
        var emailElement = "iljatest@test.ru";

        loginRegistrationEmailField.sendKeys(emailElement);
    }
    public void notRegisteredLoginByEmailWrongLogin() {
        var emailElement = "notregister@test.ru";
        var passwordElement = "tester";

        loginRegistrationEmailField.sendKeys(emailElement);
        loginPasswordField.sendKeys(passwordElement);
    }
    public void notRegisteredLoginByNameWrongLogin() {
        var emailElement = "notregister";
        var passwordElement = "tester";

        loginRegistrationEmailField.sendKeys(emailElement);
        loginPasswordField.sendKeys(passwordElement);
    }
    public void recoveryPassword_SuccessfulRecovery() {
        var emailElement = "iljatest@test.ru";

        header.myAccount.click();
        forgotPassword.click();
        fieldEmail.sendKeys(emailElement);
        buttonResetPassword.click();
    }

    public String getError() {
        return error.getText();
    }
    public String getProfileNameTitle() {
        return profileName.getText();
    }
    public String getMassage() {
        return massageSuccessfulResetPassword.getText();
    }



}
