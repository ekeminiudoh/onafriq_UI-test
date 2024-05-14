package pageObjects;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends PageBase {

    public PaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"payment-form\"]/div[1]/div/input")
    WebElement nameOnCard_textField;

    @FindBy(xpath = "//*[@id=\"payment-form\"]/div[2]/div/input")
    WebElement cardNumber_inputField;

    @FindBy(xpath = "//*[@id=\"payment-form\"]/div[3]/div[1]/input")
    WebElement cvc_inputField;

    @FindBy(xpath = "//*[@id=\"payment-form\"]/div[3]/div[2]/input")
    WebElement expiration_inputField;

    @FindBy(xpath = "//*[@id=\"payment-form\"]/div[3]/div[3]/input")
    WebElement expiry_year_inputField;

    @FindBy(xpath = "//*[@id=\"submit\"]")
    WebElement pay_and_confirm_order_button;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div/p")
    WebElement confirmation_message;


    // method to make payment

    public void enterCardDetailsAndConfirmOrder(String cardName, String cardNumber, String cvc, String expiration, String year) {
        enterText(nameOnCard_textField, cardName);
        enterText(cardNumber_inputField, cardNumber);
        enterText(cvc_inputField, cvc);
        enterText(expiration_inputField, expiration);
        enterText(expiry_year_inputField, year);
        click(pay_and_confirm_order_button);
    }

    public boolean orderConfirmationMessage(String text) {
        waitForElement(confirmation_message);
        return confirmation_message.getText().contains(text);
    }
}
