package pageObjects;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

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
    WebElement featuresItem;

    public void getLabelAndPriceOfFeaturedItems(){
        scrollIntoViewWithJavaScript(featuresItem);

        List<Map<String, Object>> itemList = new ArrayList<>();
        for(WebElement item : featuredItems){
            String name =item.findElement(By.tagName("p")).getText();
            String priceText = item.findElement(By.tagName("h2")).getText().replace("Rs.","");
            double price = Double.parseDouble(priceText);
            Map<String, Object> itemInfo = new HashMap<>();
            itemInfo.put("ItemName", name);
            itemInfo.put("ItemPrice", price);
            itemList.add(itemInfo);
        }

        itemList.sort(Comparator.comparingDouble(m -> (double) m.get("ItemPrice")));
        for (Map<String, Object> itemInfo : itemList) {
            System.out.println("Item Name: " + itemInfo.get("ItemName"));
            System.out.println("Item Price: " + itemInfo.get("ItemPrice"));
            System.out.println();
        }
    }

}