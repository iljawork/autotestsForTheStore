package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MakingAndOrderPage {
    private WebDriver driver;
    public HeaderPanel header;

    @FindBy(css = ".columns-4 .type-product:nth-of-type(3) a.collection_title")//Локатор товара из каталога
    public WebElement productFromTheCatalog;
    @FindBy(xpath = "//button[@name='add-to-cart']")//Локатор кнопки добавить в корзину
    public WebElement addToBasket;
    @FindBy(className = "account")//Локатор кнопки "войти"
    public WebElement enterToAccount;
    @FindBy(id = "username") // Локатор авторизации поля "Имя или E=mail"
    public WebElement loginRegistrationEmailField;
    @FindBy(id = "password")//Локатор авторизации поля пароль
    public WebElement loginPasswordField;
    @FindBy(className = "woocommerce-form-login__submit")//Локатор кнопки "Аваторизации"
    public WebElement loginButton;
    @FindBy(className = "showcoupon")//Локатор кнопки "Нажмите для ввода купона"
    public WebElement buttonInputPromo;
    @FindBy(id = "coupon_code")//Локатор поля для ввода купона
    public WebElement fieldInputPromo;
    @FindBy(xpath = "//button[@name='apply_coupon']")//Локатор кнопки применить купон
    public WebElement buttonUsePromo;
    @FindBy(className = "woocommerce-remove-coupon")//Локатор удаления скидки
    public WebElement deletePromo;
    @FindBy(id = "place_order")//Локатор кнопки "Оформить заказ"
    public WebElement buttonMakingAndOrder;
    @FindBy(className = "woocommerce-error")//Локатор ошибки регистрации
    public WebElement error;
    @FindBy(className = "blockOverlay")//Локатор загрузки
    public WebElement loading;
    @FindBy(id = "billing_first_name")//Локатор поля имя
    public WebElement fieldFirstName;
    @FindBy(id = "billing_last_name")//Локатор поля фамилия
    public WebElement fieldLastName;
    @FindBy(id = "billing_address_1")//Локатор поля улицы
    public WebElement fieldAddress;
    @FindBy(id = "billing_city")//локатор поля город
    public WebElement fieldCity;
    @FindBy(id = "billing_state")//локатор поля область
    public WebElement fieldArea;
    @FindBy(id = "billing_postcode")//локатор поля почтовый индекс
    public WebElement fieldPostCode;
    @FindBy(id = "billing_phone")//локатор поля телефон
    public WebElement fieldPhone;
    @FindBy(id = "payment_method_cod")//радиобаттон выбора оплаты
    public WebElement choicePay;
    @FindBy(className = "post-title")//ЛОкатор заголовка "заказ получен"
    public WebElement headerOrderGet;
    @FindBy(xpath = "//li[@data-id = 'billing_phone'][2]")//Локатор ошибки отсутствия телефона
    public WebElement errorPhone;
    private String massageInCaseOfFailedTest = "Заказ выполнен";




    public MakingAndOrderPage(WebDriver driver){
        this.driver = driver;
        header = new HeaderPanel(driver);
        PageFactory.initElements(driver,this);
    }

    public void arrangeMakingAnOrderTest() {
        header.catalog.click();
        productFromTheCatalog.click();
        addToBasket.click();
        enterToAccount.click();
    }

    public void assertBadMakingAnOrder() {
        List<WebElement> errorElements = driver.findElements(By.className("woocommerce-error"));

        Assertions.assertEquals(1, errorElements.size(), massageInCaseOfFailedTest);
    }

    public String getError() {
        return error.getText();
    }

    public void actUsePromo() {
        var promoElement = "sert500";

        buttonInputPromo.click();
        fieldInputPromo.sendKeys(promoElement);
        buttonUsePromo.click();
    }
    public void assertUsePromo() {
        List<WebElement> useCoupe = driver.findElements(By.className("coupon-sert500"));

        Assertions.assertEquals(1, useCoupe.size(), "Скидка не применилась");
    }

    public void assertDeletePromo() {
        List<WebElement> useCoupe = driver.findElements(By.className("coupon-sert500"));

        Assertions.assertEquals(0, useCoupe.size(), "Скидка не удалилась");
    }



}

