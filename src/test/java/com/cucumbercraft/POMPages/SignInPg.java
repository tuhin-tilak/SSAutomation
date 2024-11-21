package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.BuyNow.YourDetail;
import com.cucumbercraft.POMPages.BuyNow.YourOrder;
import com.cucumbercraft.entity.UserDetails;
import com.cucumbercraft.framework.*;
import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Properties;
import java.util.Set;

@Log4j2
public class SignInPg {

    public static UserDetails userDetails;
    public final By notificationBox = By.xpath("//p[@class='notification-box__text']");
    public final String url = Settings.getInstance().getProperty("OTPEndPoint");
    private final By mailSubject = By.xpath("//table[@class='table-striped jambo_table']/tbody/tr[1]/td[3]");
    private final By mailTime = By.xpath("//table[@class='table-striped jambo_table']//tr[1]/td[4][normalize-space()='just now']");
    private final By chngPwdMail = By.xpath("//a[normalize-space()='Change your password']");
    private final By chngEmailMail = By.xpath("//p[text()='(The request will expire in 8 hours) ']//preceding::a");
    private final By msgBody = By.xpath("//iframe[@id='html_msg_body']");
    private final By newPwdtxtbx = By.xpath("//input[@id='txtNewPassword']");
    private final By nxtChngPwd = By.xpath("//div[@class='cta-container']//input[@value='Next']");
    private final By signinError = By.xpath("//div[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M34Banner_notificationBox']/p");
    private final By secrtyCodeErrMsg = By.xpath("//span[contains(@id,'SecurityCodeErrorMessage')]");
    private final By signOutButton = By.xpath("//a[text()='Sign Out']");
    public By userName = By.xpath("//input[@id='emailLoginAccount']");
    public By passwd = By.xpath("//input[@id='passwordLoginAccount']");
    public By signInBtn = By.id("login-form-submit");
    public By val1 = By.xpath("//h4[text()='Enter your details:']");
    public By email = By.xpath("//input[contains(@id,'emailAddressForgetPassword')]");
    public By nxtBttn = By.xpath("//button[normalize-space()='Next']");
    public By otpTextBox = By.id("txtCheckResetPasswordOtp");
    public By reqResetLink = By.xpath("//button[normalize-space()='Request reset link']");
    public By emailVerify = By.xpath("//h2[text()='Email address verified!']");
    public By confirmPassword = By.xpath("//p[@class='notification-box__text']");
    WebDriver driver;
    HomePage homepage;
    WebDriverUtil webUtil;
    APIReusuableLibrary apiDriver = new APIReusuableLibrary();
    PropertyConfig config = ConfigCache.getOrCreate(PropertyConfig.class);
    Security_Page securitycodepg;
    ProfileAndSettingsPg pfPg;
    Properties prop = Settings.getInstance();
    private String OTPresponse;
    private By fldUsername;
    private By fldPassword;
    private By btnSignIn;
    private By hdr;


    public SignInPg(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        homepage = new HomePage(driver);
        securitycodepg = new Security_Page(driver);
        pfPg = new ProfileAndSettingsPg(driver);
    }

    public void LightLogin(String username, String password, String number) throws Exception {
        userDetails = UserDetails.getUserDetails(username);
//        driver.get(prop.getProperty("ApplicationUrl_Preprod"));
        driver.get(config.getUrl());
        try {
            if (webUtil.isElementVisible(By.xpath("(//*[contains(text(),'Allow All')])[2]"), 5))
                driver.findElement(By.xpath("(//*[contains(text(),'Allow All')])[2]")).click();
            else
                driver.findElement(By.xpath("//*[contains(text(),'Allow All')]")).click();
        } catch (Exception ignored) {
        }

        ExtentCucumberAdapter.addTestStepLog("State Savings home page is displayed ");
        webUtil.click(homepage.signIn);
        ExtentCucumberAdapter.addTestStepLog("Sign In Page");

        if (webUtil.getText(val1).equalsIgnoreCase("Enter your details:")) {
            webUtil.sendKeys(userName, username);
            webUtil.sendKeys(passwd, password);


        } else {
            log.info("Sign in Page not displayed");
            ExtentCucumberAdapter.addTestStepLog("Username and password fields not displayed ");
            Assert.fail();
        }
        webUtil.click(signInBtn);


        if (webUtil.isElementDisplayed(signinError, 10)) {
            String msg = webUtil.getText(signinError);
            ExtentCucumberAdapter.addTestStepLog(msg);
            throw new ExceptionUtils(msg);
        }

        OTPresponse = apiDriver.getOTP(url, 200, number);
        System.out.println(OTPresponse);
        ExtentCucumberAdapter.addTestStepLog("OTP is received and captured");

        if (webUtil.getText(securitycodepg.val2).contains("Verification Code")) {
            webUtil.sendKeys(securitycodepg.otp, OTPresponse);
            System.out.println("Displayed " + driver.findElement(securitycodepg.confirmbtn).isDisplayed());
            System.out.println("Enabled " + driver.findElement(securitycodepg.confirmbtn).isEnabled());

            webUtil.javascriptClick(securitycodepg.confirmbtn);
//            webUtil.click(securitycodepg.confirmbtn);

            if (webUtil.isElementDisplayed(secrtyCodeErrMsg, 5)) {
                ExtentCucumberAdapter.addTestStepLog("OTP expired fetching once again");
                webUtil.waitFor(10000);
                OTPresponse = apiDriver.getOTP(url, 200, number);
                ExtentCucumberAdapter.addTestStepLog("OTP is received and captured");
                if (webUtil.getText(securitycodepg.val2).contains("Code")) {
                    webUtil.sendKeys(securitycodepg.otp, OTPresponse);
                    if (webUtil.isElementDisplayed(securitycodepg.confirmbtn, 15)) {
                        webUtil.click(securitycodepg.confirmbtn);
                    }
                } else {
                    log.info("U");
                    Assert.fail();
                }
            }
            if (webUtil.isElementDisplayed(pfPg.greetings, 15)) {
                System.out.println(webUtil.getText(pfPg.greetings));
                log.info("Login Successful");
                ExtentCucumberAdapter.addTestStepLog("Login Successful");
            } else {
                log.info("Login failed");
                if (driver.findElement(By.xpath("//*[contains(text(),'504')]")).getText().contains("504"))
                    driver.navigate().refresh();
            }

        } else
            throw new ExceptionUtils("username or pwd is wrong");

    }

    public void EnterCredentials(String username, String password) {
        typeInUsername(username)
                .typeInPassword(password)
                .clickBtnSignIn();
    }

    public void FirstTimeLogin(String username, String password, String number) throws Exception {
        OTPresponse = apiDriver.getOTP(url, 200, number);
        typeInUsername(username)
                .typeInPassword(password)
                .clickBtnSignIn()
                .typeInOTP(number)
                .clickBtnVerifyOTP(number);

    }

    public void signOut() throws Exception {
        if (webUtil.isElementclickable(signOutButton, 5)) {
            webUtil.click(signOutButton);
            webUtil.waitForPageLoaded();
        }

    }

    public void forgotPassword(String mail) throws Exception {


        webUtil.click(homepage.forgotPwd);
        webUtil.sendKeys(email, mail);
        webUtil.click(nxtBttn);


    }

    public void enterOtp(String number) throws Exception {
        String OTPresponse = apiDriver.getOTP(url, 200, number);
        webUtil.sendKeys(otpTextBox, OTPresponse);
        webUtil.click(reqResetLink);

    }

    public void openMailinator(String email) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");
        String window = webUtil.getDriver().getWindowHandles().stream().skip(1).findFirst().orElseThrow(() -> new ExceptionUtils("window not found"));
        webUtil.getDriver().switchTo().window(window);
        webUtil.getDriver().get(String.format(prop.getProperty("mailinator"), email.replaceAll("@mailinator.com", "")));

    }

    public void getLinkMailinator() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));
        boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");
        if (webUtil.getText(mailSubject).equalsIgnoreCase("Reset your Ireland State Savings Online Password") && clickFirst) {
            webUtil.click(mailSubject);
        } else {
            System.out.println("Email not received");
            throw new ExceptionUtils("Email not received");
        }
        WebElement element = webUtil.isElementDisplayed(msgBody, 10) ? webUtil.getDriver().findElement(msgBody) : null;
        webUtil.getDriver().switchTo().frame(element);
        webUtil.click(chngPwdMail);
    }

    public void getLinkMailinator1() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailTime));
        boolean clickFirst = webUtil.getText(mailTime).trim().equalsIgnoreCase("just now");
        if (webUtil.getText(mailSubject).equalsIgnoreCase("Request to change your email address for Ireland State Savings Online") && clickFirst) {
            webUtil.click(mailSubject);
        } else {
            System.out.println("Email not received");
            throw new ExceptionUtils("Email not received");

        }
        WebElement element = webUtil.isElementDisplayed(msgBody, 10) ? webUtil.getDriver().findElement(msgBody) : null;
        webUtil.getDriver().switchTo().frame(element);
        webUtil.isElementDisplayed(chngEmailMail, 20);
        webUtil.click(chngEmailMail);

    }


    public void launchUrl(String link) {

        driver.get(link);
    }

    public void verifyEmailPage() throws Exception {

        Set<String> St = driver.getWindowHandles();
        for (String s : St) {
            driver.switchTo().window(s);

        }
        webUtil.isElementDisplayed(emailVerify, 20);
        if (webUtil.getText(emailVerify).equalsIgnoreCase("Email address verified!")) {
            ExtentCucumberAdapter.addTestStepLog("Email address is verified");
            log.info(webUtil.getText(emailVerify));
        } else {
            Assert.fail("Email is not verified");
        }
    }


    public void newPwd(String newPwd) throws Exception {
        webUtil.skip_switchToNewWindow(2);
        webUtil.sendKeys(newPwdtxtbx, newPwd);
        webUtil.isElementDisplayed(nxtChngPwd, 20);
        webUtil.click(nxtChngPwd);
        Thread.sleep(5000);
        if (webUtil.getText(confirmPassword).equalsIgnoreCase("Congratulations! You have successfully reset your password. Sign in below")) {
            log.info("Password hanged");
            ExtentCucumberAdapter.addTestStepLog("Congratulations! You have successfully reset your password. Sign in below");
        }
    }

    public void validate_Sign_in_Block() {
        webUtil.gettextlog(val1, String::equals, "Enter your details:");
        webUtil.isElementDisplayedLog(userName, 10, "Email input field");
        webUtil.isElementDisplayedLog(passwd, 10, "Password input field");
        webUtil.isElementDisplayedLog(signInBtn, 10, "Sign in button");
    }

    public SignInPg typeInUsername(String username) {
        webUtil.sendKeys(userName, username);
        return this;
    }

    public SignInPg typeInPassword(String username) {
        webUtil.sendKeys(passwd, username);
        return this;
    }

    public SignInPg clickBtnSignIn() {
        webUtil.click(signInBtn);
        return this;
    }

    public SignInPg typeInOTP(String number) throws Exception {
        OTPresponse = apiDriver.getOTP(url, 200, number);
        webUtil.sendKeys(securitycodepg.otp, OTPresponse);

        return this;
    }

    public Dash_Board clickBtnVerifyOTP(String number) throws Exception {

        webUtil.javascriptClick(securitycodepg.confirmbtn);

        if (driver.findElements(secrtyCodeErrMsg).size() > 0) {
            enterOtp(number);
        }
        return new Dash_Board(driver);
    }

    public YourDetail clickBtnVerify(String number) throws Exception {

        webUtil.javascriptClick(securitycodepg.confirmbtn);

        if (driver.findElements(secrtyCodeErrMsg).size() > 0) {
            enterOtp(number);
        }
        return new YourDetail(driver);
    }

    public SignInPg validatErrorMsg(String msg) {
        webUtil.waitFor(4000);
        webUtil.gettextlog(notificationBox, String::equals, msg, "Error Message");
        return this;
    }

    public void feildErrorMessage(String scenario) {
        webUtil.waitForPageLoaded();
        switch (scenario) {
            case "Blank":
                webUtil.gettextlog(By.id("RFVEmailLogin"), String::equals, "Please enter your email address");
                webUtil.gettextlog(By.id("RFVPasswordLogin"), String::equals, "Please enter your password");
                break;
            case "Invalid":
                webUtil.gettextlog(By.id("REVPasswordRange"), String::equals, "Incorrect email address and/or password. Please try again.");
                break;
            case "Invalid Email":
                webUtil.gettextlog(notificationBox, String::equals, "Incorrect email address and/or password. Please try again.");
                break;
            case "Incorrect Password":
                webUtil.gettextlog(notificationBox, String::equals, "Warning! You have entered your details incorrectly once. Your account will be locked after three failed attempts.");
                break;
            case "Pending":
                webUtil.gettextlog(notificationBox, String::equals, "A registration is in progress for these details.");
                break;
            default:
                System.out.println("No error found on login page");
        }
    }


    public YourOrder loginTelesales() {
        fldUsername = By.id("username");
        fldPassword = By.id("password");
        btnSignIn = By.xpath("//input[@value='Sign in']");
        hdr = By.xpath("//h2[contains(text(),'Telesales')]");

        driver.get(config.getUrl() + "telesales");
        homepage.userpermission();
        webUtil.gettextlog(hdr, String::equals, "Telesales Login");
        webUtil.sendKeys(fldUsername, "saahil.sunilgulve@anpost.ie");
        webUtil.sendKeys(fldPassword, "fY_<cFgR-dV;");
        webUtil.clickLog(btnSignIn, "SignIn on telesales page");
        return new YourOrder(driver);
    }
}


