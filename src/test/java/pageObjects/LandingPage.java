package pageObjects;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends PageBase {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    WebDriver tdriver;

    public LandingPage(WebDriver driver) {
        super(driver);
        tdriver = driver;
        PageFactory.initElements(driver, this);

    }

    // Elements and method to perform actions

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
    WebElement signup_login_button;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]")
    WebElement emailAddress_field;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")
    WebElement password_field;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div/form/button")
    WebElement login_button;


    public void userSignIn(String username, String password) {
        click(signup_login_button);
        enterText(emailAddress_field, username);
        enterText(password_field, password);
        click(login_button);
    }
}
