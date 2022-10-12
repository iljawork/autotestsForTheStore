import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pages.RegistrationAndAuthorizationPage;

public class RegistrationAndAuthorizationTest extends TestBase {
    private String massageInCaseOfFailedTest = "Пользователь не авторизовался";

    @Test
    public void loginPage_Register_SuccessfulLogin() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var nameElement = "iljatest";
        //act
        page.enterToAccount.click();
        page.successfulLogin();
        page.loginButton.click();
        //assert
        Assertions.assertEquals(nameElement, page.getProfileNameTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void registerPage_RegistrationWithTheWrongName_WrongRegister() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorNameRegisterElement = "Error: Пожалуйста введите корректное имя пользователя.";
        //act
        page.enterToAccount.click();
        page.registerButton.click();
        page.wrongNameWrongRegister();
        page.registerButton1.click();
        //assert
        Assertions.assertEquals(errorNameRegisterElement, page.getError(), "Пользователь зарегистрирован");
    }

    @Test
    public void registerPage_RegistrationWithoutAPassword_WrongRegister() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorPasswordRegisterElement = "Error: Введите пароль для регистрации.";
        //act
        page.enterToAccount.click();
        page.registerButton.click();
        page.withoutAPasswordWrongRegister();
        page.registerButton1.click();
        //assert
        Assertions.assertEquals(errorPasswordRegisterElement, page.getError(), "Пользователь зарегистрирован");
    }

    @Test
    public void registerPage_RegistrationByRegisteredName_WrongRegister() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorAlreadyRegisteredElement = "Error: Учетная запись с таким именем пользователя уже зарегистрирована.";
        //act
        page.enterToAccount.click();
        page.registerButton.click();
        page.registrationByRegisteredName_WrongRegister();
        page.registerButton1.click();
        //assert
        Assertions.assertEquals(errorAlreadyRegisteredElement, page.getError(), "Пользователь зарегистрирован");
    }

    @Test
    public void loginPage_LoginWithoutAName_WrongLogin() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorNameNecessarilyElement = "Error: Имя пользователя обязательно.";
        //act
        page.enterToAccount.click();
        page.withoutAName_WrongLogin();
        page.loginButton.click();
        //assert
        Assertions.assertEquals(errorNameNecessarilyElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void loginPage_LoginWithoutAPassword_WrongLogin() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorPasswordNecessarilyElement = "Пароль обязателен.";
        //act
        page.enterToAccount.click();
        page.loginWithoutAPassword_WrongLogin();
        page.loginButton.click();
        //assert
        Assertions.assertEquals(errorPasswordNecessarilyElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void loginPage_NotRegisteredLoginByEmail_WrongLogin() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorUnknownEmailAddressElement = "Неизвестный адрес почты. Попробуйте еще раз или введите имя пользователя.";
        //act
        page.enterToAccount.click();
        page.notRegisteredLoginByEmailWrongLogin();
        page.loginButton.click();
        //assert
        Assertions.assertEquals(errorUnknownEmailAddressElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void loginPage_NotRegisteredLoginByName_WrongLogin() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var errorUnknownNameElement = "Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.";
        //act
        page.enterToAccount.click();;
        page.notRegisteredLoginByNameWrongLogin();
        page.loginButton.click();
        //assert
        Assertions.assertEquals(errorUnknownNameElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void loginPage_RecoveryPassword_SuccessfulRecovery() {
        //arrange
        var page = new RegistrationAndAuthorizationPage(driver);
        var notificationElement = "Password reset email has been sent.";
        //act
        page.recoveryPassword_SuccessfulRecovery();
        //assert
        Assertions.assertEquals(notificationElement, page.getMassage(), massageInCaseOfFailedTest);
    }
}






