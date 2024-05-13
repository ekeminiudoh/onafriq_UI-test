package utils.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static final ExtentReports extentReports = new ExtentReports();


    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports");
        reporter.config().setReportName("ONAFRIQ UI AUTOMATION REPORT");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Test Name", "UI Automation");
        extentReports.setSystemInfo("Author", "Ekemini Udoh");
        return extentReports;
    }
}
