package com.cucumbercraft.POMPages.BuyNowEntryPoint;

import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OurProductPage {

    WebDriver driver;
    WebDriverUtil webUtil;

    public OurProductPage(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    public void clickBuyNow(String product) {
        product = product.equalsIgnoreCase("National Solidarity Bond 10Year") ? product.replaceAll("Bond.*", "Bonds") : product;


        webUtil.click(By.xpath("//a[@title='" + product + "']/following-sibling::div/a[@title='Buy now']"));

    }
}
