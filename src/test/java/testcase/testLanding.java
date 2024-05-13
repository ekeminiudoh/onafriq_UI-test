package testcase;

import base.TestBase;
import helper.ReadConfig;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.HomePage;
import pageObjects.LandingPage;

import java.lang.reflect.Method;

import static utils.extentReports.ExtentTestManager.startTest;

public class testLanding extends TestBase {
    ReadConfig readConfig;
    LandingPage landingPage;
    SoftAssert softAssert;
    HomePage homePage;


    private void initializer() {
        readConfig = new ReadConfig();
        landingPage = new LandingPage(driver);
        softAssert = new SoftAssert();
        homePage = new HomePage(driver);
    }


    @Test(priority = 1, description = "Verify that user can launch URL and login")
    public void TC_1 (Method method) throws InterruptedException {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "sign in");
        initializer();
        landingPage.clickLogin();
        landingPage.enterUsername(readConfig.setUserName());
        landingPage.enterPassword(readConfig.setPassword());
        landingPage.clickLoginButton();
        sleep(5);
        homePage.getLabelAndPriceOfFeaturedItems();
        softAssert.assertTrue(homePage.logoutButtonIsDisplayed());

    }
}
