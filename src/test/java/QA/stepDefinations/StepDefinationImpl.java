package QA.stepDefinations;

import QA.TestComponents.BaseTest;
import QA.pageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinationImpl extends BaseTest
{
    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce page")
    public void I_landed_on_Ecommerce_page() throws IOException
    {
        landingPage = launchApplication();
    }


//    @Given("Logged in with username akshithareddy@gmail.com and Abcd1234")
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username , String password)
    {
       productCatalogue = landingPage.loginApplication(username, password);

    }

    @When("^I add the product (.+) to the Cart$")
    public void I_add_the_product_to_the_Cart(String productName) throws InterruptedException
    {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_the_order(String productName) throws InterruptedException
    {
        CartPage cartPage = productCatalogue.gotToCartPage();
        Thread.sleep(1000);
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
         confirmationPage = checkoutPage.submitOrder();

    }

    @Then ("{string} message is displayed on confirmationPage")
    public void message_is_displayed_on_confirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        System.out.println(confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void message_is_displayed(String strArg1) throws Throwable
    {
        Assert.assertEquals( landingPage.getErrorMessage(),"Incorrect email or password.");
        driver.close();
    }


}
