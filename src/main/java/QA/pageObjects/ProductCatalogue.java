package QA.pageObjects;

import QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.security.PublicKey;
import java.util.List;


public class ProductCatalogue extends AbstractComponent
{
    WebDriver driver;
    //Constructor
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(this.driver,this);
    }
   //
    // Below is the Page Factory design pattern by using "@FindBy annotation" for this line (WebElement userEmail =  driver.findElement(By.id("userEmail"));)
    //this is for reducing the syntax for creating WebElement.
    //Page Factor is Exclusive only for driver.findElement construction

   // List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

    @FindBy(css=".mb-3")
    List<WebElement> products;
    @FindBy(css=".ng-animating")
            WebElement spinner;


    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By tostMessage = By.cssSelector("#toast-container");

    public  List<WebElement> getProductList()
    {
        waitForElementToApper(productBy);
        return products;
    }

    public  WebElement getProductByName(String productName)
    {
       WebElement prod = getProductList().stream().filter(product->
                    product.findElement(By.cssSelector("b")).getText().equals((productName))).
            findFirst().orElse(null);
       return prod;
    }
    public void addProductToCart(String productName) throws InterruptedException {
        WebElement prod = getProductByName(productName);
        Thread.sleep(2000);
        prod.findElement(addToCart).click();
        waitForElementToApper(tostMessage);
        waitForElementToDisapper(spinner);
    }

}
//9390996520