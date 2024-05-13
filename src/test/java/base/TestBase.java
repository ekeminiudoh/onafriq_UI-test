package base;

import helper.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class TestBase {
    public static ReadConfig readConfig = new ReadConfig();
    public static String baseUrl = readConfig.setApplicationURL();
    public static String browser = readConfig.getbrowser();
    public Duration WAIT = ofSeconds(120);
    public static WebDriver driver;
    public static Logger logger;
    public static JavascriptExecutor jsExecutor;
    private static final int IMPLICIT_WAIT_TIMEOUT_SECONDS = 45;
    private static final int EXPLICIT_WAIT_TIMEOUT_SECONDS = 20;
    public WebDriverWait wait;



//    private void configureLogger() {
//        logger = Logger.getLogger("DebuggingLog");
//        PropertyConfigurator.configure("log4j.properties");
//        logger.setLevel(Level.DEBUG);
//        logger.debug("Debug logging has started");
//    }

    public WebDriver getDriver() {
        return driver;
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time * 1000);//content of sleep is in MS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }

    private static void initializeWebDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setAcceptInsecureCerts(true);
                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("dom.notification.enabled", false);
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setProfile(profile);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }



    @BeforeMethod
    public void setUp() {
        initializeWebDriver(browser);
//        configureLogger();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(80));
        wait = new WebDriverWait(driver, ofSeconds(EXPLICIT_WAIT_TIMEOUT_SECONDS));
        jsExecutor = (JavascriptExecutor) driver;

//        switch (browser.toLowerCase()) {
//            case "chrome" -> setupChromeDriver();
//            case "edge" -> setupEdgeDriver();
//            case "firefox" -> setupFirefoxDriver();
//            default -> throw new IllegalStateException("Invalid browser specified: " + browser);
//        }

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


//    @BeforeClass
//    public void setUp() {
//        initializeWebDriver(browser);
//        configureLogger();
//        driver.get(baseUrl);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(ofSeconds(80));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT_SECONDS));
//        jsExecutor = (JavascriptExecutor) driver;
//    }
//
//    @AfterClass
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    private void setupChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
    }

    private void setupEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(options);
    }

    private void setupFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.firefoxdriver().setup();
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver(options);
    }
}
