package pageObjects;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends PageBase {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
    WebElement logout_Button;

    public boolean logoutButtonIsDisplayed() {
        waitForElement(logout_Button);
        return logout_Button.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='features_items']//div[@class='col-sm-4']//div[@class='productinfo text-center']")
    List<WebElement> featuredItems;

    @FindBy(xpath = "//div[@class='features_items']")
    WebElement featuresItems;

    public void getLabelAndPriceOfFeaturedItems(){
        scrollIntoViewWithJavaScript(featuresItems);
        List<String> itemNames = new ArrayList<>();
        List<Double> itemPrices = new ArrayList<>();
        for(WebElement item : featuredItems) {
            String label = item.findElement(By.tagName("p")).getText();
            String priceText = item.findElement(By.tagName("h2")).getText().replace("Rs.","");
            double price = Double.parseDouble(priceText);

            itemNames.add(label);
            itemPrices.add(price);

            List<String> combined = new ArrayList<>();
            for (int i = 0; i< itemNames.size(); i++) {
                combined.add(itemNames.get(i) +" "+ "Rs" +" "+ itemPrices.get(i));
            }

            combined.sort((item1, item2) -> {
                double price1 = Double.parseDouble(item1.substring(item1.lastIndexOf("RS.") + 1));
                double price2 = Double.parseDouble(item2.substring(item2.lastIndexOf("RS.") + 1));
                return Double.compare(price1, price2);
            });
            System.out.println("Sorted Featured Items (Low to High):");
            combined.forEach(System.out::println);
        }
    }


}
