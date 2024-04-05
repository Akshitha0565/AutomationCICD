package QA.Tests;

import QA.TestComponents.BaseTest;
import QA.TestComponents.Retry;
import QA.pageObjects.CartPage;
import QA.pageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTests extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws IOException, InterruptedException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("akshitharedd@gmail.com", "Abcd124");

        Assert.assertEquals( landingPage.getErrorMessage(),"Incorrect email or password.");
    }


    @Test
    public void productErrorValidation() throws InterruptedException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("buddu@gmail.com", "Reddy143");

        //Object for ProductCatalogue

        List<WebElement> products = productCatalogue.getProductList();
        System.out.println(" Products " + products);
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.gotToCartPage();

        boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");
        Assert.assertTrue(match);

    }

}
