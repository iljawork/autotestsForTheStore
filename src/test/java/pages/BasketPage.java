package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasketPage {

    private WebDriver driver;
    public HeaderPanel header;

    @FindBy(css = ".columns-4 .type-product:nth-of-type(3) a.collection_title") //Локатор товара из каталога
    public WebElement productFromTheCatalog;
    @FindBy(xpath = "//button[@name='add-to-cart']") //Локатор кнопки добавить в корзину
    public WebElement addToBasket;
    @FindBy(css = "td.product-name")//Локатор названия товара добавленного в корзину "Apple Watch 6"
    public WebElement nameProductInBasket;
    @FindBy(id = "coupon_code")//Локатор поля ввода купона
    public WebElement couponEntryField;
    @FindBy(xpath = "//button[@name='apply_coupon']")//Локатор кнопки "Применить купон"
    public WebElement buttonApplyCoupon;
    @FindBy(css = ".product-subtotal .woocommerce-Price-amount")//Локатор общей стоимости
    public WebElement totalСost;
    @FindBy(xpath = "//td[@data-title='Сумма'] //span[@class = 'woocommerce-Price-amount amount']")//Локатор "к оплате"
    public WebElement costToBePaid;
    @FindBy(className = "woocommerce-remove-coupon")//Локатор кнопки "Удалить скидку
    public WebElement discountRemove;
    @FindBy(className = "checkout-button")//Локатор кнопки "Оформить заказ"
    public WebElement buttonPlaceAnOrder;
    @FindBy(className = "entry-title")//Локатор заголовка "Оформление заказа"
    public WebElement headerMakingAnOrder;
    @FindBy(className = "remove")//Локатор крестика
    public WebElement removeProduct;
    @FindBy(className = "cart-empty")//Локатор текста "Корзина пуста"
    public WebElement textBasketIsEmpty;
    @FindBy(className = "restore-item")//Локатор "Возвращения"
    public WebElement comeBackButton;
    @FindBy(className = "wc-backward")//Локатор кнопки
    public WebElement buttonGoToCatalog;
    @FindBy(className = "entry-title")//Локатор заголовка товаров "Все товары"
    public WebElement headerCatalog;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        header = new HeaderPanel(driver);
        PageFactory.initElements(driver,this);
    }

    public void sendKeysCouponField() {
        var couponLocatorElement = "sert500";

        couponEntryField.sendKeys(couponLocatorElement);
    }

    public void arrangeBasketTest() {
        header.catalog.click();
        productFromTheCatalog.click();
        addToBasket.click();
        header.basket.click();
    }

    public void assertUseSaleInBasket() {
        var totalCostElement = "34990,00₽";
        var toBePaidElement = "34490,00₽";
        List<WebElement> applicationOfTheDiscountLocator = driver.findElements(By.className("coupon-sert500"));

        Assertions.assertEquals(totalCostElement, totalСost.getText(), "Стоимость не совпадает");
        Assertions.assertEquals(1, applicationOfTheDiscountLocator.size(), "Скидка не появилась");
        Assertions.assertEquals(toBePaidElement, costToBePaid.getText(), "Скидка не применилась");
    }

    public void deleteDiscount() {
        var couponLocatorElement = "sert500";

        couponEntryField.sendKeys(couponLocatorElement);
        buttonApplyCoupon.click();
        discountRemove.click();
    }

    public void assertDeleteDiscount() {
        List<WebElement> applicationOfTheDiscountLocator = driver.findElements(By.className("coupon-sert500"));

        Assertions.assertEquals(0, applicationOfTheDiscountLocator.size(), "Скидка не удалилась");
    }

    public void removeProductAndReturningBack() {
        var textBasketIsEmptyElement = "Корзина пуста.";

        removeProduct.click();
        Assertions.assertEquals(textBasketIsEmptyElement, textBasketIsEmpty.getText());
        comeBackButton.click();
    }

    public String getHeaderProductInBasketTitle() {
        return nameProductInBasket.getText();
    }

    public String getHeaderMakingAnOrderTitle() {
        return headerMakingAnOrder.getText();
    }

    public String getHeaderCatalogLocatorTitle() {
        return headerCatalog.getText();
    }

}
