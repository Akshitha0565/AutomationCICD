package QA.TestComponents;

import QA.pageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;

    public void initializeDriver() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\akshi\\OneDrive\\Desktop\\Akshitha Learning\\SeleniumFrameworkDesign\\src\\main\\java\\QA\\Resources\\GlobalData.properties");
        props.load(file);
        //Java Turnary Operator
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : props.getProperty("browser");
        //props.getProperty("browser");
        if (browserName.contains("chrome")) {
            driver = new ChromeDriver();
            ChromeOptions options = new ChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));//helps to run in full screen

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    //to get Json Data to map variable which is in getData() in SubmitOrderTest Class
    public List<HashMap<String ,String>> getJsonDataToMap(String filePath) throws IOException
    {
        //read Json to String
        String jsonContent =  FileUtils.readFileToString(new File(filePath));

        //Converting String To HashMap with new Dependency Jackson Databind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String ,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
        {});
        return data;
    }


    public File getScreenshot(String testCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source =  ts.getScreenshotAs(OutputType.FILE);
        File file = new File("C://Users//akshi//OneDrive//Desktop//Akshitha Learning//SeleniumFrameworkDesign//reports//"+ testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return file;
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
      initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
