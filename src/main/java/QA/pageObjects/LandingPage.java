package QA.pageObjects;

import QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends AbstractComponent
{
    WebDriver driver;
    //Constructor
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
   //
    // Below is the Page Factory design pattern by using "@FindBy annotation" for this line (WebElement userEmail =  driver.findElement(By.id("userEmail"));)
    //this is for reducing the syntax for creating WebElement.
    @FindBy(id="userEmail")
   WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userpasscode;

    @FindBy(id="login")
    WebElement submit;
    @FindBy(css = "[class*='flyInOut']")
    WebElement erroeMessage;

    // ng-tns-c4-38 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error

    public ProductCatalogue loginApplication(String email,String password)
    {
        userEmail.sendKeys(email);
        userpasscode.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;

    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
    public String getErrorMessage()
    {
        waitForWebElementToApper(erroeMessage);
       return erroeMessage.getText();
    }
}
