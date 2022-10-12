package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {
    private WebDriver driver;
    public HeaderPanel header;

    @FindBy(className = "account")
    public WebElement enterToAccount;
    @FindBy(css = "#product1 li[aria-hidden = 'false'] .item-img span")
    public List<WebElement> allProductsLabels;
    @FindBy(className = "post-title")
    public WebElement headerMyAccount;
    @FindBy(className = "search-field")
    public WebElement searchBar;
    @FindBy(className = "entry-title")
    public WebElement headerPage;
    @FindBy(className = "searchsubmit")
    public WebElement findButton;
    @FindBy(xpath = "//a[@href='?add-to-cart=15']")
    public WebElement buttonAddToBasketProduct;
    @FindBy(className = "current")
    public WebElement headerBasket;
    @FindBy(id = "accesspress_storemo-2")
    public WebElement bookCategory;
    @FindBy(id = "accesspress_storemo-3")
    public WebElement tabletCategory;
    @FindBy(id = "accesspress_storemo-4")
    public WebElement cameraCategory;
    @FindBy(className = "promo-widget-wrap-full")
    public WebElement alreadySale;
    @FindBy(xpath = "//aside[@id = 'accesspress_store_product-3']//li[@data-slick-index = '0']//span[@class = 'label-new']")
    public WebElement newLocator;

    @FindBy(css = ".product_title.entry-title")
    public WebElement titleAlreadySale;



    public MainPage(WebDriver driver) {
        this.driver = driver;
        header = new HeaderPanel(driver);
        PageFactory.initElements(driver,this);
    }

    public boolean hasProductWithDiscount(int index) {
        return allProductsLabels.get(index).getAttribute("class").contains("onsale");
    }

    public void assertNewLabelsOnNewProducts() {
        var saleNameElement = "Новый!";

        Assertions.assertEquals(saleNameElement, newLocator.getText(), "Товар не новый");
    }

    public void assertDisplayingViewedProducts() {
        List<WebElement> viewedListLocator = driver.findElements(By.className("product_list_widget"));

        Assertions.assertEquals(1, viewedListLocator.size(), "Список просмотренных товаров не отображается");
    }

    public void sendKeysSearchBar() {
        var searchQueryElement = "Часы";

        searchBar.sendKeys(searchQueryElement);
    }

    public String getTitle() {
        return headerPage.getText();
    }

    public String getHeaderMyAccountTitle() {
        return headerMyAccount.getText();
    }

    public String getHeaderBasketTitle() {
        return headerBasket.getText();
    }

    public String getHeaderAlreadySaleTitle() {
        return titleAlreadySale.getText();
    }


}
