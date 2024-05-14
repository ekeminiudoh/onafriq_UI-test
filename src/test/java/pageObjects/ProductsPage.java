package pageObjects;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends PageBase {

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"accordian\"]/div[1]/div[1]/h4/a")
    WebElement women;

    @FindBy(xpath = "//*[@id=\"Women\"]/div/ul/li[2]/a")
    WebElement tops;

    @FindBy(xpath = "/html/body/section/div/div[2]/div[2]/div/div[6]/div/div[1]/div[1]/a")
    WebElement fancy_green_top;

    @FindBy(xpath = "//*[@id=\"cartModal\"]/div/div/div[3]/button")
    WebElement continueShopping_button;

    @FindBy(xpath = "/html/body/section/div/div[2]/div[2]/div/div[4]/div/div[1]/div[1]/a")
    WebElement summer_white_top;

    @FindBy(xpath = "//*[@id=\"cartModal\"]/div/div/div[2]/p[2]/a/u")
    WebElement view_cart;


    // method to add items to cart

    public void addItemsToCart() {
        click(women);
        click(tops);
        scrollIntoViewWithJavaScript(fancy_green_top);
        click(fancy_green_top);
        click(continueShopping_button);
        scrollIntoViewWithJavaScript(summer_white_top);
        click(summer_white_top);
        click(view_cart);
    }
}
