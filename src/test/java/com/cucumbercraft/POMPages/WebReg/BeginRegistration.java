package com.cucumbercraft.POMPages.WebReg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BeginRegistration {

    public By beginRegistration1 = By.xpath("//*[text()='Begin Registration']");
    public By beginRegistration2 = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M37GreyBlockWithButton_lnkBeginRegistration']");
    public By bannerReg = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M01YourSavingsMainBanner_lblBannerTitle']");

    WebDriver driver;

    public BeginRegistration(WebDriver driver) {
        this.driver = driver;
    }


}
