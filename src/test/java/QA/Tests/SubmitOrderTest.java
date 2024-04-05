package QA.Tests;

import QA.TestComponents.BaseTest;
import QA.pageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        System.out.println(" Products " + products);
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.gotToCartPage();
        Thread.sleep(1000);
        boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        System.out.println(confirmMessage);
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    //To verify ZARA COAT 3 is diaplaying in orders page
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("akshithareddy@gmail.com", "Abcd1234");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }





    @DataProvider
    //it helps us to send different sets of data to run a test. For that create an two dimensional Array which accepts multiple Sets of data.
    public Object[][] getData() throws IOException {
        //here we are sending data in array we are using Hash Map and sendind data in key-value pairs
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","akshithareddy@gmail.com");
//        map.put("password","Abcd1234");
//        map.put("productName","ZARA COAT 3");
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","anshika@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("productName","ADIDAS ORIGINAL");
        //return new Object[][] { {map},{map1} };
        List<HashMap<String,String>> data = getJsonDataToMap("C://Users//akshi//OneDrive//Desktop//Akshitha Learning//SeleniumFrameworkDesign//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] { {data.get(0)},{data.get(1)} };
    }

}
