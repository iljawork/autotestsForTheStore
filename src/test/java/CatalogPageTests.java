import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CatalogPageTests {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(catalogLocator).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("D:\\Skrin\\screenshot1.png"));
        driver.quit();
    }

    private By catalogLocator = By.id("menu-item-46"); //Локатор кнопки каталог
    private By mainMenuButton = By.className("site-text");  //Локатор кнопки главное меню
    private By searchBarLocator = By.className("search-field"); //Локатор поисковой строки
    private By headerSearchResultLocator = By.className("entry-title"); //Локатор заголовка после поиска
    private By findButtonLocator = By.className("searchsubmit"); //Локатор кнопки поиска

    @Test
    public void catalogPage_Catalog_OpenCatalog() {
        //arrange
        var searchQueryElement = "СТИРАЛЬНЫЕ МАШИНЫ";
        //act
        driver.findElement(mainMenuButton).click();
        driver.findElement(searchBarLocator).sendKeys(searchQueryElement);
        driver.findElement(findButtonLocator).click();
        //assert
        Assertions.assertEquals("РЕЗУЛЬТАТЫ ПОИСКА: “СТИРАЛЬНЫЕ МАШИНЫ”", driver.findElement(headerSearchResultLocator).getText(), "Поиск не сработал");
    }

    private By goToMainMenuLocator = By.xpath("//a[@href = 'http://intershop5.skillbox.ru']"); //Локатор кнопки Главня

    @Test
    public void catalogPage_Catalog_OpenMAinMenu() {
        //act
        driver.findElement(goToMainMenuLocator).click();
    }

    private By choiceCategoryLocator = By.className("cat-item-22"); //Локатор выбора категории товаров
    private By headerCategoryLocator = By.className("entry-title"); //Локатор заголовка выбранного товара

    @Test
    public void catalogPage_CategoryProduct_GoToCategory() {
        //arrange
        var headerCategoryElement = "СТИРАЛЬНЫЕ МАШИНЫ";
        //act
        driver.findElement(choiceCategoryLocator).click();
        //assert
        Assertions.assertEquals(headerCategoryElement, driver.findElement(headerCategoryLocator).getText(), "Пользователь попал не в ту категорию");
    }

    private By firstProductLocator = By.cssSelector(".product_list_widget li:first-child"); //Локатор первого товара из списка
    private By openProductLocator = By.id("content"); //Локатор открывающейся вкладки

    @Test
    public void catalogPage_ChoiceProductFromProducts_OpenProduct() {
        //act
        driver.findElement(firstProductLocator).click();
        //assertion
        Assertions.assertEquals(1, driver.findElements((openProductLocator)).size(), "Карточка с товаром не открылась");
    }

    private By cardProductLocator = By.cssSelector(".columns-4 .type-product:nth-of-type(3) a.collection_title"); //Локатор карточки товара из Каталога
    private By headerCardProductLocator = By.className("product_title"); //Локатор заголовка карточки выбранного товара

    @Test
    public void catalogPage_ChoiceProductFormCatalog_OpenProduct() {
        //arrange
        var headerCardProductElement = "Apple Watch 6";
        //act
        driver.findElement(cardProductLocator).click();
        //assertion
        Assertions.assertEquals(headerCardProductElement, driver.findElement(headerCardProductLocator).getText(), "Пользователь не попал на страницу с товаром");

    }

    private By pageLocator = By.cssSelector(".page-numbers li:nth-of-type(3)");

    @Test
    public void catalogPage_GoToNextPage_OpenNextPage() {
        //act
        driver.findElement(pageLocator).click();
        driver.findElement(cardProductLocator).click();
    }

    private By buttonAddToBackedLocator = By.xpath("//a[@data-product_id = '15']");
    private By moreDetailedLocator = By.className("added_to_cart");

    private By headerBasketLocator = By.className("current"); //Локатор заголовка "Корзина"

    @Test
    public void catalogPage_AddToBasked_ProductAdded() {
        //arrange
        var basketNameElement = "Корзина";
        //act
        driver.findElement(buttonAddToBackedLocator).click();
        driver.findElement(moreDetailedLocator).click();
        //assertion
        Assertions.assertEquals(basketNameElement, driver.findElement(headerBasketLocator).getText(), "Пользователь не попал в корзину");
    }

}