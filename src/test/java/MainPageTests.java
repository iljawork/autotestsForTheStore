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

public class MainPageTests {
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

    private By headerMyAccountLocator = By.className("post-title"); //Локатор заголовка "Мой аккаунт"

    @Test
    public void mainPage_GoToTheAccountPage_OpenAccountPage() {
        //arrange
        var headersElement = "Мой аккаунт";
        //act
        driver.findElement(enterToAccountLocator).click();
        //assert
        Assertions.assertEquals(headersElement, driver.findElement(headerMyAccountLocator).getText(), "Пользователь не попал на вкладку Мой аккаунт");
    }

    private By searchBarLocator = By.className("search-field"); //Локатор поисковой строки
    private By headerSearchResultLocator = By.className("entry-title"); //Локатор заголовка после поиска
    private By findButtonLocator = By.className("searchsubmit"); //Локатор кнопки поиска

    @Test
    public void mainPage_SearchUseSearchBar_SearchElements() {
        //arrange
        var searchQueryElement = "Часы";
        //act
        driver.findElement(searchBarLocator).sendKeys(searchQueryElement);
        driver.findElement(findButtonLocator).click();

        //assert
        Assertions.assertEquals("РЕЗУЛЬТАТЫ ПОИСКА: “ЧАСЫ”", driver.findElement(headerSearchResultLocator).getText(), "Поиск не сработал");
    }


    private By catalogLocator = By.id("menu-item-46"); //Локатор кнопки "каталог"
    private By headerCatalogLocator = By.className("entry-title"); //Локатор заголовка "Каталог"
    private By buttonAddToBasketProductLocator = By.xpath("//a[@href='?add-to-cart=15']"); //Локатор добавления товара в корзину
    private By myAccountLocator = By.id("menu-item-30"); //Локатор кнопки "Мой аккаунт"
    private By basketLocator = By.id("menu-item-29"); //Локатор кнопки "Корзина"
    private By headerBasketLocator = By.className("current"); //Локатор заголовка "Корзина"
    private By makingAnOrderLocator = By.id("menu-item-31"); //Локатор кнопки "Оформление заказа"
    private By headerMakingAnOrderLocator = By.className("entry-title"); //Локатор заголовка "Оформление заказа"

    @Test
    public void mainPage_MoveToNavigatePanel_MoveToPage() {
        //arrange
        var catalogNameElement = "КАТАЛОГ";
        var myAccountNameElement = "Мой аккаунт";
        var basketNameElement = "Корзина";
        var orderNameElement = "Оформление заказа";
        //act
        driver.findElement(catalogLocator).click();
        Assertions.assertEquals(catalogNameElement, driver.findElement(headerCatalogLocator).getText(), "Переход во вкладку каталог не сработал");
        driver.findElement(buttonAddToBasketProductLocator).click();
        driver.findElement(myAccountLocator).click();
        Assertions.assertEquals(myAccountNameElement, driver.findElement(headerMyAccountLocator).getText(), "Переход во вкладку Мой аккаунт не сработал");
        driver.findElement(basketLocator).click();
        Assertions.assertEquals(basketNameElement, driver.findElement(headerBasketLocator).getText(), "Переход во вкладку корзина не сработал");
        driver.findElement(makingAnOrderLocator).click();
        Assertions.assertEquals(orderNameElement, driver.findElement(headerMakingAnOrderLocator).getText(), "Переход во вкладку оформление заказа не сработал");
    }

    private By mainMenuButton = By.className("site-text"); // Локатор возвращения на главную страницу
    private By bookCategoryLocator = By.id("accesspress_storemo-2"); //Локатор блока "Книги"
    private By headerBookCategoryLocator = By.className("entry-title"); //Локатор заголовка "Книги"
    private By tabletCategoryLocator = By.id("accesspress_storemo-3"); //Локатор блока "Планшеты"
    private By headerTabletCategoryLocator = By.className("entry-title"); //Локатор заголовка "Планшеты"
    private By cameraCategoryLocator = By.id("accesspress_storemo-4"); //Локатор блока "Фотоаппараты"
    private By headerCameraCategoryLocator = By.className("entry-title"); //Локатор блока "Фотоаппараты"

    @Test
    public void mainPage_Category_OpenCategory() {
        //arrange
        var headerBookElement = "КНИГИ";
        var headerTabletElement = "ПЛАНШЕТЫ";
        var headerCameraElement = "ФОТО/ВИДЕО";
        //act
        driver.findElement(bookCategoryLocator).click();
        Assertions.assertEquals(headerBookElement, driver.findElement(headerBookCategoryLocator).getText(), "Переход во вкладку книги не сработал");
        driver.findElement(mainMenuButton).click();
        driver.findElement(tabletCategoryLocator).click();
        Assertions.assertEquals(headerTabletElement, driver.findElement(headerTabletCategoryLocator).getText(), "Переход во вкладку планшеты не сработал");
        driver.findElement(mainMenuButton).click();
        driver.findElement(cameraCategoryLocator).click();
        Assertions.assertEquals(headerCameraElement, driver.findElement(headerCameraCategoryLocator).getText(), "Переход во вкладку фотоаппараты не сработал");
    }

    private By productLocator = By.xpath("//aside[@id = 'accesspress_store_product-2']//li[@data-slick-index = '0']"); //Локатор кнопки "Товара из распродажи"
    private By saleLocator = By.cssSelector(".img-wrap .onsale"); //Локатор пометки "Скидка"

    @Test
    public void mainPage_SaleBloc_OpenSaleProduct() throws InterruptedException {
        //arrange
        var saleNameElement = "Скидка!";
        //act
        Thread.sleep(1000);
        driver.findElement(productLocator).click();
        Assertions.assertEquals(saleNameElement, driver.findElement(saleLocator).getText(), "Скидки на товар нет");
    }

    private By alreadySaleLocator = By.className("promo-widget-wrap-full");  //Лоткатор блока-кнопки "Уже в продаже"
    private By titleAlreadySaleLocator = By.cssSelector(".product_title.entry-title"); //Локатор заголовка "iPad"

    @Test
    public void mainPage_AlreadyAnSale_OpenProduct() {
        //arrange
        var titleLocator = "iPad 2020 32gb wi-fi";
        //act
        driver.findElement(alreadySaleLocator).click();
        //assertion
        Assertions.assertEquals(titleLocator, driver.findElement(titleAlreadySaleLocator).getText(), "Пользователь не папал на вкладку");
    }

    private By productNewLocator = By.xpath("//aside[@id = 'accesspress_store_product-3']//li[@data-slick-index = '0']"); //Локатор кнопки "Товара из новых постеплений"
    private By newLocator = By.xpath("//aside[@id = 'accesspress_store_product-3']//li[@data-slick-index = '0']//span[@class = 'label-new']"); //Локатор пометки "Новый"

    @Test
    public void mainPage_NewBloc_OpenNewProduct() {
        //arrange
        var saleNameElement = "Новый!";
        //act
        driver.findElement(productNewLocator);
        Assertions.assertEquals(saleNameElement, driver.findElement(newLocator).getText(), "Товар не новый");
    }

    private By viewedListLocated = By.className("product_list_widget"); //Локатор списка просмотренных товаров

    @Test
    public void mainPage_ViewedProducts_DisplayedProducts() {
        //arrange
        driver.findElement(alreadySaleLocator).click();
        driver.findElement(mainMenuButton).click();
        //assertion
        Assertions.assertEquals(1, driver.findElements((viewedListLocated)).size(), "Список просмотренных товаров не отображается");

    }


}





