package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPanel {
    @FindBy(id = "menu-item-30")
    public WebElement myAccount;
    @FindBy(id = "menu-item-29")
    public WebElement basket;
    @FindBy(id = "menu-item-31")
    public WebElement makingAnOrder;
    @FindBy(id = "menu-item-46")
    public WebElement catalog;
    @FindBy(id = "menu-item-26")
    public WebElement mainMenuButton;

    private WebDriver driver;

    public HeaderPanel(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
