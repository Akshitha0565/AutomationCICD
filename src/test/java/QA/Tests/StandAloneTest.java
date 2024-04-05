package QA.Tests;

import QA.pageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest
{
    public static void main(String[] args)
    {
       String productName1 = "ADIDAS ORIGINAL";
     //String productName = "ZARA COAT 3";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("userEmail")).sendKeys("akshithareddy@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Abcd1234");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        System.out.println(" Products >> " + products);
        //to get all the products inthe page
       WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals((productName1))).
                findFirst().orElse(null);

        System.out.println(" prod >> " + prod);
       prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //for "Product Added Successfully" message at buttom right cornor
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        // for "Loading Icon" when we click add to cart
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        //for clicking on "cart button" by using cssSelector Regular expression ([attributr*='value'])
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        //checking weather the product added to cart is same of the product we want
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match =  cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName1));
        Assert.assertTrue(match);
       driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(confirmationMessage);

        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();



    }
}
