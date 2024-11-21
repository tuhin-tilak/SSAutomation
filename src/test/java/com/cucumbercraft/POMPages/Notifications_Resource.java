package com.cucumbercraft.POMPages;

import org.openqa.selenium.By;

public class Notifications_Resource {
    public By notificationPath = By.xpath("//li[@class='primary-nav__notifications']//a[@class='gtm-nav header__nav-link header__nav-link--dashboard' and contains(text(),'Notifications')]");
    public By notificationSetting = By.xpath("//a[@class='gtm-sidenav' and contains(text(),'Notification Settings')]");
    public By annualStatement = By.xpath("//a[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_annualStatementLink']");
    public By alerts = By.xpath("//label[@for='alertsNotification']//span[2]");
    public By profile_Change = By.xpath("//p[@id='NotificationsConfirmationMessage']");
    public By dropDown = By.id("notificationTypeFilter");
    public By action = By.xpath("//select[@class='select product-select']//option");
    public By confirm_Message = By.xpath("//p[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_ConfirmationMessage']");
    public By Notification_Confirm_Button = By.id("btnConfirmChanges");
    public By alert_Meassge = By.xpath("//div[@class='notification-box__text']/strong");
    public By message = By.xpath("//label[@for='messagesNotification']");
    public By notificationMessage = By.id("notification_");
}
