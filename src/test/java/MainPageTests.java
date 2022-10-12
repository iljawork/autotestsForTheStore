import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pages.MainPage;

public class MainPageTests extends TestBase {
    private String massageInCaseOfFailedTest = "Неверный заголовок страницы, на которую перешли после клика";

    @Test
    public void mainPage_GoToTheAccountPage_OpenAccountPage() {
        //arrange
        var page = new MainPage(driver);
        var headersElement = "Мой аккаунт";
        //act
        page.enterToAccount.click();
        //assert
        Assertions.assertEquals(headersElement, page.getHeaderMyAccountTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void mainPage_SearchUseSearchBar_SearchElements() {
        //arrange
        var page = new MainPage(driver);
        var searchResultsElement = "РЕЗУЛЬТАТЫ ПОИСКА: “ЧАСЫ”";
        //act
        page.sendKeysSearchBar();
        page.findButton.click();

        //assert
        Assertions.assertEquals(searchResultsElement, page.getTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void mainPage_MoveToNavigatePanel_MoveToPage() {
        //arrange
        var page = new MainPage(driver);
        var catalogNameElement = "КАТАЛОГ";
        var myAccountNameElement = "Мой аккаунт";
        var basketNameElement = "Корзина";
        var orderNameElement = "Оформление заказа";
        //act
        page.header.catalog.click();
        Assertions.assertEquals(catalogNameElement, page.getTitle(), massageInCaseOfFailedTest);
        page.buttonAddToBasketProduct.click();
        page.header.myAccount.click();
        Assertions.assertEquals(myAccountNameElement, page.getHeaderMyAccountTitle(), massageInCaseOfFailedTest);
        page.header.basket.click();
        Assertions.assertEquals(basketNameElement, page.getHeaderBasketTitle(), massageInCaseOfFailedTest);
        page.header.makingAnOrder.click();
        Assertions.assertEquals(orderNameElement, page.getTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void mainPage_Category_OpenCategory() {
        //arrange
        var page = new MainPage(driver);
        var headerBookElement = "КНИГИ";
        var headerTabletElement = "ПЛАНШЕТЫ";
        var headerCameraElement = "ФОТО/ВИДЕО";
        //act
        page.bookCategory.click();
        Assertions.assertEquals(headerBookElement, page.getTitle(), massageInCaseOfFailedTest);
        page.header.mainMenuButton.click();
        page.tabletCategory.click();
        Assertions.assertEquals(headerTabletElement, page.getTitle(), massageInCaseOfFailedTest);
        page.header.mainMenuButton.click();
        page.cameraCategory.click();
        Assertions.assertEquals(headerCameraElement, page.getTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void mainPage_OpenMainPAge_AllProductsOnSaleHaveSaleLabel() {
        //arrange
        var page = new MainPage(driver);

        //assert
        assertSaleLabelsOnProductsWithDiscount(page);
    }

    private void assertSaleLabelsOnProductsWithDiscount(MainPage page) {
        for (var i = 0; i < 4; i++) {
            Assert.assertTrue("У товара нет лейбла скидки", page.hasProductWithDiscount(i));
        }
    }

    @Test
    public void mainPage_AlreadyAnSale_OpenProduct() {
        //arrange
        var page = new MainPage(driver);
        var titleLocator = "iPad 2020 32gb wi-fi";
        //act
        page.alreadySale.click();
        //assertion
        Assertions.assertEquals(titleLocator, page.getHeaderAlreadySaleTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void mainPage_NewBloc_OpenNewProduct() {
        //arrange
        var page = new MainPage(driver);
        //assert
        page.assertNewLabelsOnNewProducts();
    }

    @Test
    public void mainPage_ViewedProducts_DisplayedProducts() {
        //arrange
        var page = new MainPage(driver);
        //act
        page.alreadySale.click();
        page.header.mainMenuButton.click();
        //assertion
        page.assertDisplayingViewedProducts();
    }


}





