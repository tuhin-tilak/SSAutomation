package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.Notifications_Resource;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class NotificationSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    WebDriverUtil webUtil = new WebDriverUtil(driver);
    PortalLoginSteps login = new PortalLoginSteps();
    Notifications_Resource notify = new Notifications_Resource();
    ProfileSettingsSteps profile = new ProfileSettingsSteps();


    @When("^User change the profile password with \"([^\"]*)\" and provide new \"([^\"]*)\"$")
    public void userChangeTheProfilePasswordWithAndProvideNew(String number, String password2) throws Throwable {
        profile.PasswordChangetab(number);
//        profile.Changepasswordto(password2);
        log.info("Successfully logged in");
    }

    @And("^click on the Notification menu and navigates to the Notification Page$")
    public void clickOnTheNotificationMenuAndNavigatesToTheNotificationPage() {


        webUtil.click(notify.notificationPath);


    }

    @And("^User is able to view the Notification DropDown$")
    public void userIsAbleToViewTheNotificationDropDown() throws Exception {
//        Assert.assertTrue(webUtil.getText(notify.dropDown).equalsIgnoreCase("All Notifications"));
    }

    @Then("^User selects the \"([^\"]*)\" and able to view the respective notifications$")
    public void userSelectsTheAndAbleToViewTheRespectiveNotifications(String selectedAction) {

        System.out.println("User selects the alert from dropdown");
        webUtil.selectDropDown(notify.dropDown, select -> select.selectByVisibleText(selectedAction));

    }

    //Alert

    @And("^clicks on the Notifications and Setting tab and is navigated to notifications and settings page$")
    public void clicksOnTheNotificationsAndSettingTabAndIsNavigatedToNotificationsAndSettingsPage() {

        webUtil.click(notify.notificationSetting);
        System.out.println("notification setting tab is clicked");
        log.info("Notification setting tab is clicked");


    }

    @Then("^User disabled the alerts and able to view the Confirm change page$")
    public void userDisabledTheAlertsAndAbleToViewTheConfirmChangePage() {


        webUtil.click(notify.alerts);
        webUtil.gettextlog(notify.profile_Change, String::equals, "You have updated your notification settings, please confirm these changes.");

    }

    @And("^Clicks on the Confirm and is able to view the alert message$")
    public void clicksOnTheConfirmAndIsAbleToViewTheAlertMessage() {

        try {


            webUtil.click(notify.Notification_Confirm_Button);
            System.out.println("the alert is disabled");
            webUtil.gettextlog(notify.alert_Meassge, String::equals, "Thank you, your notification settings have been updated");


        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }

    }

    @Then("User clicks message toggle button and able to view the Confirm change modal")
    public void userDisabledTheMessageAndAbleToViewTheConfirmChangePage() {
        webUtil.waitFor(3000);
        webUtil.click(notify.message);
        Assert.assertTrue(webUtil.getText(notify.profile_Change).contentEquals("You have updated your notification settings, please confirm these changes."));


    }

    @Then("Validate notification {string}")
    public void validateNotification(String expectedMessage) {
        WebElement element = driver.findElements(notify.notificationMessage).get(0);
        element.findElement(By.name("show-notification-button")).click();
        String actualMessage = element.findElement(By.tagName("p")).getText();
        webUtil.CompareString(actualMessage, String::equals, expectedMessage);
    }
}
