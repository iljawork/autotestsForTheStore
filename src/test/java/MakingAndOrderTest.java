import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MakingAndOrderPage;

public class MakingAndOrderTest extends TestBase{
    private String massageInCaseOfFailedTest = "Заказ выполнен";
    private By loadingLocator = By.className("blockOverlay");
    @Test
    public void makingAnOrder_UsePromo_Promo_PromoUsedAndDeleted() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var email = "iljatest21@test.ru";
        var password = "tester1337";

        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.actUsePromo();
        page.assertUsePromo();
        page.deletePromo.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated((loadingLocator)));
        page.assertDeletePromo();
    }

    @Test
    public void makingAnOrder_MakingAnOrder_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var email = "iljatest20@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        wait.until(ExpectedConditions.invisibilityOfElementLocated((loadingLocator)));
        page.buttonMakingAndOrder.click();
        //assert
        page.assertBadMakingAnOrder();
    }


    @Test
    public void makingAnOrder_MakingAnOrder_GoodMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var headerOrderGetElement = "Оформление заказа";
        var email = "iljatest8@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(headerOrderGetElement, page.headerOrderGet.getText(), "Заказ не прошёл");
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutFirsName_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorFirstNameElement = "Имя для выставления счета обязательное поле.";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorFirstNameElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutLastName_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorLastNameElement = "Фамилия для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorLastNameElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutAddress_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorAddressElement = "Адрес для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorAddressElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutCity_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorCityElement = "Город / Населенный пункт для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorCityElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutArea_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorAreaElement = "Область для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorAreaElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutPostCode_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorPostCodeElement = "Почтовый индекс для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var phoneElement = "89999999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPhone.sendKeys(phoneElement);
        page.choicePay.click();
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorPostCodeElement, page.getError(), massageInCaseOfFailedTest);
    }

    @Test
    public void makingAnOrder_MakingAnOrderWithoutPhone_BadMakingAnOrder() {
        //arrange
        var page = new MakingAndOrderPage(driver);
        var errorPhoneElement = "Телефон для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var email = "iljatest6@test.ru";
        var password = "tester1337";
        page.arrangeMakingAnOrderTest();
        page.loginRegistrationEmailField.sendKeys(email);
        page.loginPasswordField.sendKeys(password);
        page.loginButton.click();
        page.header.makingAnOrder.click();
        //act
        page.fieldFirstName.sendKeys(firstNameElement);
        page.fieldLastName.sendKeys(lastNameElement);
        page.fieldAddress.sendKeys(addressElement);
        page.fieldCity.sendKeys(cityElement);
        page.fieldArea.sendKeys(areaElement);
        page.fieldPostCode.sendKeys(postCodeElement);
        page.choicePay.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated((loadingLocator)));
        page.buttonMakingAndOrder.click();
        //assert
        Assertions.assertEquals(errorPhoneElement, page.errorPhone.getText(), massageInCaseOfFailedTest);
    }

}





