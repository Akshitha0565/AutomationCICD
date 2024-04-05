package QA.AbstractComponents;

import QA.pageObjects.CartPage;
import QA.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//for Reusable Code
public class AbstractComponent
{
    WebDriver driver;
    WebDriverWait wait;
    //Constructor
    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[routerlink*='cart']")
        WebElement cartHeader;
    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;


    public void waitForElementToApper(By findBy)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }
    public void waitForWebElementToApper(WebElement findBy)
    {
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }
    public void waitForElementToDisapper(WebElement ele)
    {
     wait.until(ExpectedConditions.invisibilityOf(ele));

    }

    public CartPage gotToCartPage()
    {
        cartHeader.click();
        CartPage cartPage= new CartPage(driver);
        return cartPage;
    }
    public OrderPage goToOrdersPage()
    {
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;

    }

}
