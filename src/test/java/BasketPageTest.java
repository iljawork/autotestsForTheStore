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

public class BasketPageTest {
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
        driver.findElement(By.id("menu-item-29")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    private By nameProductInBasket = By.cssSelector("td.product-name"); //Локатор названия товара добавленного в корзину "Apple Watch 6"

    @Test
    public void basketPage_GoToBasketAndAddProduct_ProductAdded() {
        //arrange
        var nameProductElement = "Apple Watch 6";
        //act
        //assert
        Assertions.assertEquals(nameProductElement, driver.findElement(nameProductInBasket).getText(), "Товар не добавлен");
    }

    private By couponEntryFieldLocator = By.id("coupon_code"); //Локатор поля ввода купона
    private By buttonApplyCouponLocator = By.xpath("//button[@name='apply_coupon']"); //Локатор кнопки "Применить купон"
    private By totalСostLocator = By.cssSelector(".product-subtotal .woocommerce-Price-amount"); //Локатор общей стоимости
    private By applicationOfTheDiscount = By.className("coupon-sert500"); //Локатор применения скидки
    private By costToBePaid = By.xpath("//td[@data-title='Сумма'] //span[@class = 'woocommerce-Price-amount amount']"); //Локатор "к оплате"

    @Test
    public void basketPage_HowMatchPrice_ProductDiscount() {
        //arrange
        var couponLocatorElement = "sert500";
        var totalCostElement = "34990,00₽";
        var toBePaidElement = "34490,00₽";
        //act
        driver.findElement(couponEntryFieldLocator).sendKeys(couponLocatorElement);
        driver.findElement(buttonApplyCouponLocator).click();
        //assert
        Assertions.assertEquals(totalCostElement, driver.findElement(totalСostLocator).getText(), "Стоимость не совпадает");
        Assertions.assertEquals(1, driver.findElements(applicationOfTheDiscount).size(), "Скидка не появилась");
        Assertions.assertEquals(toBePaidElement, driver.findElement(costToBePaid).getText(), "Скидка не применилась");
    }

    private By discountRemoveLocator = By.className("woocommerce-remove-coupon"); //Локатор кнопки "Удалить скидку

    @Test
    public void basketPage_DeleteDiscount_DiscountRemoved() {
        //arrange
        var couponLocatorElement = "sert500";
        //act
        driver.findElement(couponEntryFieldLocator).sendKeys(couponLocatorElement);
        driver.findElement(buttonApplyCouponLocator).click();
        driver.findElement(discountRemoveLocator).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated((applicationOfTheDiscount)));
        //assert
        Assertions.assertEquals(0, driver.findElements(applicationOfTheDiscount).size(), "Скидка не удалилась");
    }

    private By buttonPlaceAnOrder = By.className("checkout-button"); //Локатор кнопки "Оформить заказ"
    private By headerMakingAnOrderLocator = By.className("entry-title"); //Локатор заголовка "Оформление заказа"

    @Test
    public void basketPage_GoToMakingAnOrder_GoToCheckout() {
        //arrange
        var orderNameElement = "Оформление заказа";
        //act
        driver.findElement(buttonPlaceAnOrder).click();
        //assert
        Assertions.assertEquals(orderNameElement, driver.findElement(headerMakingAnOrderLocator).getText(), "Пользователь не попал на вкладку оформление заказа");
    }

    private By removeProductLocator = By.className("remove"); //Локатор крестика
    private By textBasketIsEmptyLocator = By.className("cart-empty"); //Локатор текста "Корзина пуста"
    private By comeBackLocator = By.className("restore-item"); //Локатор "Возвращения"

    @Test
    public void basketPage_RemoveProductAndReturningBack_RemoveAndReturning() {
        //arrange
        var textBasketIsEmptyElement = "Корзина пуста.";
        var nameProductElement = "Apple Watch 6";
        //act
        driver.findElement(removeProductLocator).click();
        Assertions.assertEquals(textBasketIsEmptyElement, driver.findElement(textBasketIsEmptyLocator).getText());
        driver.findElement(comeBackLocator).click();
        Assertions.assertEquals(nameProductElement, driver.findElement(nameProductInBasket).getText(), "Товар не добавлен");
    }

    private By buttonGoToCatalogLocator = By.className("wc-backward"); //Локатор кнопки
    private By headerCatalogLocator = By.className("entry-title"); //Локатор заголовка товаров "Все товары"

    @Test
    public void basketPage_RemoveProductAndGoToCatalog_GoToCatalog() {
        //arrange
        var headerCatalogElement = "ВСЕ ТОВАРЫ";
        //act
        driver.findElement(removeProductLocator).click();
        driver.findElement(buttonGoToCatalogLocator).click();
        //assert
        Assertions.assertEquals(headerCatalogElement, driver.findElement(headerCatalogLocator).getText(), "Пользователь не перешёл во вкладку Каталог");
    }

}