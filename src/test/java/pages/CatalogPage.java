package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogPage {
    private WebDriver driver;
    public HeaderPanel header;

    @FindBy(className = "search-field")//Локатор поисковой строки
    public WebElement searchBar;
    @FindBy(className = "entry-title")//Локатор заголовка открытой вкладки
    public WebElement headerPage;
    @FindBy(className = "searchsubmit")//Локатор кнопки поиска
    public WebElement findButton;
    @FindBy(xpath = "//li[@class = 'cat-item cat-item-22']//a[1]")//Локатор выбора категории товаров
    public WebElement choiceCategory;
    @FindBy(css = ".product_list_widget li:first-child") //Локатор первого товара из списка
    public WebElement firstProduct;
    @FindBy(css = ".columns-4 .type-product:nth-of-type(3) a.collection_title") //Локатор карточки товара из Каталога
    public WebElement cardProduct;
    @FindBy(className = "product_title")//Локатор заголовка карточки выбранного товара
    public WebElement headerCardProduct;
    @FindBy(css = ".page-numbers li:nth-of-type(3)")//Локатор кнопок навигации
    public WebElement pageLocator;
    @FindBy(xpath = "//a[@data-product_id = '15']")//Локатор кнопки "В корзину"
    public WebElement buttonAddToBacked;
    @FindBy(className = "added_to_cart")//Локатор кнопки "Подробнее"
    public WebElement moreDetailed;
    @FindBy(className = "current")//Локатор заголовка "Корзина"
    public WebElement headerBasket;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        header = new HeaderPanel(driver);
        PageFactory.initElements(driver,this);
    }

    public void sendKeysSearchQueryElement() {
        var searchQueryElement = "СТИРАЛЬНЫЕ МАШИНЫ";

        searchBar.sendKeys(searchQueryElement);
    }

    public void assertOpenProduct() {
        List<WebElement> openProductLocator = driver.findElements(By.id("content"));

        Assertions.assertEquals(1, openProductLocator.size(), "Карточка с товаром не открылась");
    }

    public String getTitle() {
        return headerPage.getText();
    }
    public String getHeaderCardProduct() {
        return headerCardProduct.getText();
    }
    public String getHeaderBasket() {
        return headerBasket.getText();
    }


}
