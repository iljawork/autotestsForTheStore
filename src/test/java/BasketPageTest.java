import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasketPage;

public class BasketPageTest extends TestBase {
    private String massageInCaseOfFailedTest = "Неверный заголовок страницы, на которую перешли после клика";


    @Test
    public void basketPage_GoToBasketAndAddProduct_ProductAdded() {
        //arrange
        var page = new BasketPage(driver);
        var nameProductElement = "Apple Watch 6";
        page.arrangeBasketTest();
        //act
        //assert
        Assertions.assertEquals(nameProductElement, page.getHeaderProductInBasketTitle(), "Товар не добавлен");
    }

    @Test
    public void basketPage_HowMatchPrice_ProductDiscount() {
        //arrange
        var page = new BasketPage(driver);
        page.arrangeBasketTest();
        //act
        page.sendKeysCouponField();
        page.buttonApplyCoupon.click();
        //assert
        page.assertUseSaleInBasket();
    }

    @Test
    public void basketPage_DeleteDiscount_DiscountRemoved() {
        //arrange
        var page = new BasketPage(driver);
        page.arrangeBasketTest();
        //act
        page.deleteDiscount();
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By.className("coupon-sert500"))));
        //assert
        page.assertDeleteDiscount();
    }

    @Test
    public void basketPage_GoToMakingAnOrder_GoToCheckout() {
        //arrange
        var page = new BasketPage(driver);
        var orderNameElement = "Оформление заказа";
        page.arrangeBasketTest();
        //act
        page.buttonPlaceAnOrder.click();
        //assert
        Assertions.assertEquals(orderNameElement, page.getHeaderMakingAnOrderTitle(), massageInCaseOfFailedTest);
    }

    @Test
    public void basketPage_RemoveProductAndReturningBack_RemoveAndReturning() {
        //arrange
        var page = new BasketPage(driver);
        var nameProductElement = "Apple Watch 6";
        page.arrangeBasketTest();
        //act
        page.removeProductAndReturningBack();
        //assert
        Assertions.assertEquals(nameProductElement, page.getHeaderProductInBasketTitle(), "Товар не добавлен");
    }

    @Test
    public void basketPage_RemoveProductAndGoToCatalog_GoToCatalog() {
        //arrange
        var page = new BasketPage(driver);
        var headerCatalogElement = "ВСЕ ТОВАРЫ";
        page.arrangeBasketTest();
        //act
        page.removeProduct.click();
        page.buttonGoToCatalog.click();
        //assert
        Assertions.assertEquals(headerCatalogElement, page.getHeaderCatalogLocatorTitle(), massageInCaseOfFailedTest);
    }

}