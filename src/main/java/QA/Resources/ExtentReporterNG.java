package QA.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG
{

    public static ExtentReports getReporterObject()
    {
        //Object for ExtentSparkReporter
        String path = "C://Users//akshi//OneDrive//Desktop//Akshitha Learning//SeleniumFrameworkDesign//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation results");
        reporter.config().setDocumentTitle("Test results");

        //Object for ExtentReports
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Akshitha Bussu");
        return extent;
    }
}
