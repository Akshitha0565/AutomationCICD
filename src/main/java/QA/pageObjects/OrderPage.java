package QA.pageObjects;

import QA.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent
{
    WebDriver driver;
    //Constructor
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="tr td:nth-child(3)")
    private List<WebElement> productNames;

    public boolean verifyOrderDisplay(String productName)
    {
        System.out.println(" productNames.get(0).getText() " + productNames.get(0).getText());
        System.out.println(" productNames " + productNames.size());
        Boolean match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }
}
