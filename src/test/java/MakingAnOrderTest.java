import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MakingAnOrderTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(By.id("menu-item-46")).click();
        driver.findElement(By.cssSelector(".columns-4 .type-product:nth-of-type(3) a.collection_title")).click();
        driver.findElement(By.xpath("//button[@name='add-to-cart']")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private By enterToAccountLocator = By.className("account"); //Локатор кнопки "войти"
    private By loginRegistrationEmailFieldLocator = By.id("username"); // Локатор авторизации поля "Имя или E=mail"
    private By loginPasswordFieldLocator = By.id("password"); //Локатор авторизации поля пароль
    private By loginButtonLocator = By.className("woocommerce-form-login__submit"); //Локатор кнопки "Аваторизации"
    private By makingAnOrderLocator = By.id("menu-item-31"); //Локатор кнопки "Оформление заказа"

    private By buttonInputPromoLocator = By.className("showcoupon"); //Локатор кнопки "Нажмите для ввода купона"
    private By fieldInputPromoLocator = By.id("coupon_code"); //Локатор поля для ввода купона
    private By buttonUsePromoLocator = By.xpath("//button[@name='apply_coupon']"); //Локатор кнопки применить купон
    private By promoAppliedLocator = By.className("coupon-sert500"); //Локатор добовления скидки
    private By deletePromoLocator = By.className("woocommerce-remove-coupon"); //Локатор удаления скидки

    @Test
    public void makingAnOrder_UsePromo_Promo_PromoUsedAndDeleted() {
        //arrange
        var promoElement = "sert500";
        var email = "iljatest21@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(buttonInputPromoLocator).click();
        driver.findElement(fieldInputPromoLocator).sendKeys(promoElement);
        driver.findElement(buttonUsePromoLocator).click();
        Assertions.assertEquals(1, driver.findElements((promoAppliedLocator)).size(), "Скидка не применилась");
        driver.findElement(deletePromoLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated((promoAppliedLocator)));
        Assertions.assertEquals(0, driver.findElements((promoAppliedLocator)).size(), "Скидка не удалилась");
    }

    private By buttonMakingAndOrderLocator = By.id("place_order"); //Локатор кнопки "Оформить заказ"
    private By errorLocator = By.className("woocommerce-error"); //Локатор ошибки регистрации

    @Test
    public void makingAnOrder_MakingAnOrder_BadMakingAnOrder() throws InterruptedException {
        //arrange
        var email = "iljatest20@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        Thread.sleep(1000);
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(1, driver.findElements((errorLocator)).size(), "Пользователь оформил заказ");
    }

    private By fieldFirstNameLocator = By.id("billing_first_name"); //Локатор поля имя
    private By fieldLastNameLocator = By.id("billing_last_name"); //Локатор поля фамилия
    private By fieldAddressLocator = By.id("billing_address_1"); //Локатор поля улицы
    private By fieldCityLocator = By.id("billing_city"); //локатор поля город
    private By fieldAreaLocator = By.id("billing_state"); //локатор поля область
    private By fieldPostCodeLocator = By.id("billing_postcode"); //локатор поля почтовый индекс
    private By fieldPhoneLocator = By.id("billing_phone"); //локатор поля телефон
    private By choicePayLocator = By.id("payment_method_cod"); //радиобаттон выбора оплаты
    private By headerOrderGetLocator = By.className("post-title"); //ЛОкатор заголовка "заказ получен"

    @Test
    public void makingAnOrder_MakingAnOrder_GoodMakingAnOrder() {
        //arrange
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
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(headerOrderGetElement, driver.findElement(headerOrderGetLocator).getText(), "Заказ не прошёл");
    }

    private By errorFirstNameLocator = By.xpath("//li[@data-id = 'billing_first_name']"); //Локатор ошибки отсутствия Имени

    @Test
    public void makingAnOrder_MakingAnOrderWithoutFirsName_BadMakingAnOrder() {
        //arrange
        var errorFirstNameElement = "Имя для выставления счета обязательное поле.";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorFirstNameElement, driver.findElement(errorFirstNameLocator).getText(), "Заказ выполнен");
    }

    private By errorLastNameLocator = By.xpath("//li[@data-id = 'billing_last_name']"); //Локатор ошибки отсутствия Фамилии

    @Test
    public void makingAnOrder_MakingAnOrderWithoutLastName_BadMakingAnOrder() {
        //arrange
        var errorLastNameElement = "Фамилия для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorLastNameElement, driver.findElement(errorLastNameLocator).getText(), "Заказ выполнен");
    }

    private By errorAddressLocator = By.xpath("//li[@data-id = 'billing_address_1']"); //Локатор ошибки отсутствия адреса

    @Test
    public void makingAnOrder_MakingAnOrderWithoutAddress_BadMakingAnOrder() {
        //arrange
        var errorAddressElement = "Адрес для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorAddressElement, driver.findElement(errorAddressLocator).getText(), "Заказ выполнен");
    }

    private By errorCityLocator = By.xpath("//li[@data-id = 'billing_city']"); //Локатор ошибки отсутствия города

    @Test
    public void makingAnOrder_MakingAnOrderWithoutCity_BadMakingAnOrder() {
        //arrange
        var errorCityElement = "Город / Населенный пункт для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorCityElement, driver.findElement(errorCityLocator).getText(), "Заказ выполнен");
    }

    private By errorAreaLocator = By.xpath("//li[@data-id = 'billing_state']"); //Локатор ошибки отсутствия области

    @Test
    public void makingAnOrder_MakingAnOrderWithoutArea_BadMakingAnOrder() {
        //arrange
        var errorAreaElement = "Область для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var postCodeElement = "999999";
        var phoneElement = "89999999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorAreaElement, driver.findElement(errorAreaLocator).getText(), "Заказ выполнен");
    }

    private By errorPostCodeLocator = By.xpath("//li[@data-id = 'billing_postcode']"); //Локатор ошибки отсутствия индекса

    @Test
    public void makingAnOrder_MakingAnOrderWithoutPostCode_BadMakingAnOrder() {
        //arrange
        var errorPostCodeElement = "Почтовый индекс для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var phoneElement = "89999999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPhoneLocator).sendKeys(phoneElement);
        driver.findElement(choicePayLocator).click();
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorPostCodeElement, driver.findElement(errorPostCodeLocator).getText(), "Заказ выполнен");
    }

    private By errorPhoneLocator = By.xpath("//li[@data-id = 'billing_phone'][2]"); //Локатор ошибки отсутствия телефона

    @Test
    public void makingAnOrder_MakingAnOrderWithoutPhone_BadMakingAnOrder() throws InterruptedException {
        //arrange
        var errorPhoneElement = "Телефон для выставления счета обязательное поле.";
        var firstNameElement = "Илья";
        var lastNameElement = "Иванов";
        var addressElement = "Самара ул.Ленина д.1";
        var cityElement = "Самара";
        var areaElement = "Самарская";
        var postCodeElement = "999999";
        var email = "iljatest5@test.ru";
        var password = "tester1337";
        driver.findElement(enterToAccountLocator).click();
        driver.findElement(loginRegistrationEmailFieldLocator).sendKeys(email);
        driver.findElement(loginPasswordFieldLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(makingAnOrderLocator).click();
        //act
        driver.findElement(fieldFirstNameLocator).sendKeys(firstNameElement);
        driver.findElement(fieldLastNameLocator).sendKeys(lastNameElement);
        driver.findElement(fieldAddressLocator).sendKeys(addressElement);
        driver.findElement(fieldCityLocator).sendKeys(cityElement);
        driver.findElement(fieldAreaLocator).sendKeys(areaElement);
        driver.findElement(fieldPostCodeLocator).sendKeys(postCodeElement);
        driver.findElement(choicePayLocator).click();
        Thread.sleep(1000);
        driver.findElement(buttonMakingAndOrderLocator).click();
        //assert
        Assertions.assertEquals(errorPhoneElement, driver.findElement(errorPhoneLocator).getText(), "Заказ выполнен");
    }


}





