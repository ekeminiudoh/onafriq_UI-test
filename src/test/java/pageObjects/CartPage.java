package pageObjects;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends PageBase {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"do_action\"]/div[1]/div/div/a")
    WebElement proceedToCheckout;

    @FindBy(xpath = "//*[@id=\"ordermsg\"]/textarea")
    WebElement comment_textField;

    @FindBy(xpath = "//*[@id=\"cart_items\"]/div/div[7]/a")
    WebElement place_order;

    // method to place order

    public void placeOrder(String text) {
        click(proceedToCheckout);
        scrollIntoViewWithJavaScript(comment_textField);
        enterText(comment_textField, text);
        click(place_order);
    }
}
