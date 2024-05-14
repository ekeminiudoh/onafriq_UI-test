package testcase;

import base.TestBase;
import helper.ReadConfig;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.*;

import java.lang.reflect.Method;

import static utils.extentReports.ExtentTestManager.startTest;

public class E2E_SignIn_Browse_Checkout_Test extends TestBase {
    ReadConfig readConfig;
    SoftAssert softAssert;
    LandingPage landingPage;
    HomePage homePage;
    ProductsPage productsPage;
    CartPage cartPage;
    PaymentPage paymentPage;


    private void initializer() {
        readConfig = new ReadConfig();
        softAssert = new SoftAssert();
        landingPage = new LandingPage(driver);
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        paymentPage = new PaymentPage(driver);
    }


    @Test(priority = 1, description = " 1. Go to \"https://www.automationexercise.com/\"")
    public void TC_1 (Method method)  {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "go to url");
        initializer();
    }

    @Test(priority = 2, description = " 2. click on Sign-In. Sign-In using following credentials")
    public void TC_2 (Method method) throws InterruptedException {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "sign in");
        initializer();
        landingPage.userSignIn("qat@mailinator.com", "123456");
    }

    @Test(priority = 3, description = " 3. Get the label and associated price of those \n" +
            "item. Fetch them and sort it as per their price [Low to \n" +
            "High] and print it on Console")
    public void TC_3 (Method method) throws InterruptedException {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "view label/prices on console");
        initializer();
        homePage.getLabelAndPriceOfFeaturedItems();
    }

    @Test(priority = 4, description = " 4. Scroll Up -  Navigate to  Women >> Dress >> Women – \n" +
            "Tops Products. Select the Fancy Green Top and add to \n" +
            "cart. Select Summer White Top and add to cart as well. ")
    public void TC_4 (Method method) throws InterruptedException {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "add items to cart");
        initializer();
        productsPage.addItemsToCart();
    }

    @Test(priority = 5, description = " 5. View cart >> Proceed to checkout. Add the comments, \n" +
            "”Order placed.” Click on Place Order. Enter card details ")
    public void TC_5 (Method method) throws InterruptedException {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "payment");
        initializer();
        cartPage.placeOrder("Order placed.");
        paymentPage.enterCardDetailsAndConfirmOrder("Test Card", "4100 0000 0000", "123", "01", "1900");
    }

    @Test(priority = 6, description = " 6. Confirm order has been placed.")
    public void TC_6 (Method method) throws InterruptedException {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "order confirmation");
        initializer();
        softAssert.assertTrue
                (paymentPage.orderConfirmationMessage("Congratulations! Your order has been confirmed!"));

    }


}
