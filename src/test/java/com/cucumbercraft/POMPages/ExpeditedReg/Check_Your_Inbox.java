package com.cucumbercraft.POMPages.ExpeditedReg;

import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import com.cucumbercraft.stepdefinitions.MasterStepDefs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class Check_Your_Inbox extends MasterStepDefs {
    WebDriver driver;
    WebDriverUtil webUtil;

    private final By header = By.xpath("//*[@class='underlined-heading m39-registration-form__heading']");
    private final String headerContent = "Check your inbox";
    private final By paragraph = By.className("m39-registration-form__text");
    private final String paragraphContent = "We have sent you a confirmation email to verify your email address. The email verification link will remain active for 8 hours. If you do not click the link within this time you will need to start registration again.";
    private final By didnotRecieveMail = By.xpath("//a[contains(text(),'receive our email?')]");
    private final String didnotRecieveMailContent = "Didn't receive our email?";

    //popUP
    private final By popupHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h4");
    private final String popupHeaderContent = "Didn't get an email?";
    private final By label = By.xpath("//section[@class='dashboard-modal__content js-modal active']/p");
    private final String labelContent = "Before requesting the email is resent please:";
    private final String points = "//section[@class='dashboard-modal__content js-modal active']/ul/li[%s]";
    private final String firstPoint = "Allow 1-2 minutes for the code to arrive";
    private final String secondPoint = "Check your spam folder.";
    private final String thirdPoint = "Check that your device is online and that there are no network issues in your area.";
    private final By resendEmail = By.xpath("//button[@class='gtm-linkclick button button--primary button--alt']");
    private final By closeBtn = By.xpath("//button[@class='gtm-linkclick button button--secondary button--alt js-closeModal']");
    private final By resendSuccessfully = By.xpath("//div[contains(text(),'Verification email resent successfully.')]");

    public Check_Your_Inbox(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    public void verifyContent() throws Exception {
        webUtil.gettextlog(header, String::equalsIgnoreCase, headerContent);
        webUtil.gettextlog(paragraph, String::equalsIgnoreCase, paragraphContent);
        webUtil.gettextlog(didnotRecieveMail, String::equalsIgnoreCase, didnotRecieveMailContent);

    }

    public void clickDidNotEmailLink() throws InterruptedException {
        try {
            webUtil.click(didnotRecieveMail);
        } catch (Exception e) {
            throw new ExceptionUtils("Did'nt receive email link clicked Interrupted");
        }
    }

    private void verifyPopupContent() throws Exception {
        webUtil.gettextlog(popupHeader, String::equalsIgnoreCase, popupHeaderContent);
        webUtil.gettextlog(label, String::equalsIgnoreCase, labelContent);
        webUtil.gettextlog((By.xpath(String.format(points, "1"))), String::equalsIgnoreCase, firstPoint);
        webUtil.gettextlog((By.xpath(String.format(points, "2"))), String::equalsIgnoreCase, secondPoint);
        webUtil.gettextlog((By.xpath(String.format(points, "3"))), String::equalsIgnoreCase, thirdPoint);

    }

    public void clickResendBtnPopUp() throws Exception {
        verifyPopupContent();
        webUtil.gettextlog(resendEmail, String::equalsIgnoreCase, "Resend email");
        webUtil.click(resendEmail);
        webUtil.click(resendEmail);
        verifyResendBanner();
    }

    private void verifyResendBanner() throws Exception {
        webUtil.gettextlog(resendSuccessfully, String::equalsIgnoreCase, "Verification email resent successfully.");

    }

    public void clickCloseBtnPopUp() throws Exception {
        verifyPopupContent();
        webUtil.gettextlog(closeBtn, String::equalsIgnoreCase, "Close");

    }

    public void launchEmailToken(String token) {
        driver.get(token);
    }


    //for mailinator

    private final By openEmail = By.xpath("//table[@class='table-striped jambo_table']//tr[1]/td[4][normalize-space()='just now']");
    private final By mailSubject = By.xpath("//table[@class='table-striped jambo_table']/tbody/tr[1]/td[3]");

    private final By tokenLink = By.xpath("//kbd");
    private final By frameSwitch = By.xpath("//iframe[@id='html_msg_body']");

    public String emailVerificationToken(String emailID) throws Exception {
        webUtil.waitFor(3000);
        System.out.println(emailID.replaceAll("@mailinator.com", ""));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String verToken = null;
        String mailinatorUrl = properties.getProperty("mailinator");
        String mailinatorUrl1 = String.format(mailinatorUrl, (emailID.replaceAll("@mailinator.com", "")));
//        ((JavascriptExecutor) driver).executeScript("window.open('https://www.mailinator.com/v3/index.jsp?zone=public&query=SIT-ECR-004#/#inboxpane')");
//        String a = "window.open(".concat(mailinatorUrl1.concat(",'_blank'"));
//        System.out.println(a);
//        ((JavascriptExecutor)driver).executeScript(a);
        js.executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
//        driver.findElement(By.cssSelector("body")).sendKeys((Keys.CONTROL + "t"));
        driver.get(mailinatorUrl1);
//        driver.manage().window().maximize();

//        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(180));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(openEmail));
        boolean clickFirst = webUtil.waitUntilElementVisible(openEmail, 180).getText().trim().equalsIgnoreCase("just now");
        if (clickFirst) {
            webUtil.click(mailSubject);
        } else {
            throw new ExceptionUtils("Email not received");
        }

        WebElement web = driver.findElement(frameSwitch);
        driver.switchTo().frame(web);

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//        js.executeScript("document.getElementById('msg_body').onload = function () { this.contentWindow.scrollTo(0, 200) };");

//        web=driver.findElement(tokenLink);
//        js.executeScript("arguments[0].scrollIntoView();", web);
        verToken = webUtil.getText(tokenLink);
        return verToken;
    }


}