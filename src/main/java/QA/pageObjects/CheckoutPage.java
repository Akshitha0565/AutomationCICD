package QA.pageObjects;

import QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckoutPage extends AbstractComponent
{
    WebDriver driver;
    //Constructor
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[placeholder='Select Country']")
    WebElement country;
    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectCountryName;
    @FindBy(css = ".action__submit")
    WebElement submit;
    By results = By.cssSelector(".ta-results");

    public void selectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToApper(results);
        selectCountryName.click();

    }
    public ConfirmationPage submitOrder()
    {
        submit.click();

        ConfirmationPage  confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;

    }


}
