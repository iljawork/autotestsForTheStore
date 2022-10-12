import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pages.CatalogPage;

public class CatalogPageTests extends TestBase{
    private String massageInCaseOfFailedTest = "Неверный заголовок страницы, на которую перешли после клика";

    @Test
    public void catalogPage_Catalog_OpenCatalog() {
        //arrange
        var page = new CatalogPage(driver);
        var searchResults = "РЕЗУЛЬТАТЫ ПОИСКА: “СТИРАЛЬНЫЕ МАШИНЫ”";
        page.header.basket.click();
        //act
        page.header.mainMenuButton.click();
        page.sendKeysSearchQueryElement();
        page.findButton.click();
        //assert
        Assertions.assertEquals(searchResults, page.getTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void catalogPage_Catalog_OpenMAinMenu() {
        //arrange
        var page = new CatalogPage(driver);
        page.header.catalog.click();
        //act
        page.header.mainMenuButton.click();
    }

    @Test
    public void catalogPage_CategoryProduct_GoToCategory() throws InterruptedException {
        //arrange
        var page = new CatalogPage(driver);
        var headerCategoryElement = "СТИРАЛЬНЫЕ МАШИНЫ";
        page.header.catalog.click();
        //act
        page.choiceCategory.click();
        //assert
        Assertions.assertEquals(headerCategoryElement, page.getTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void catalogPage_ChoiceProductFromProducts_OpenProduct() {
        //arrange
        var page = new CatalogPage(driver);
        page.header.catalog.click();
        //act
        page.firstProduct.click();
        //assertion
        page.assertOpenProduct();
    }

    @Test
    public void catalogPage_ChoiceProductFormCatalog_OpenProduct() {
        //arrange
        var page = new CatalogPage(driver);
        var headerCardProductElement = "Apple Watch 6";
        page.header.catalog.click();
        //act
        page.cardProduct.click();
        //assertion
        Assertions.assertEquals(headerCardProductElement, page.getHeaderCardProduct(), massageInCaseOfFailedTest);

    }

    @Test
    public void catalogPage_GoToNextPage_OpenNextPage() {
        //arrange
        var page = new CatalogPage(driver);
        page.header.catalog.click();
        //act
        page.pageLocator.click();
        page.cardProduct.click();
    }

    @Test
    public void catalogPage_AddToBasked_ProductAdded() {
        //arrange
        var page = new CatalogPage(driver);
        var basketNameElement = "Корзина";
        page.header.catalog.click();
        //act
        page.buttonAddToBacked.click();
        page.moreDetailed.click();
        //assertion
        Assertions.assertEquals(basketNameElement, page.getHeaderBasket(), massageInCaseOfFailedTest);
    }

}