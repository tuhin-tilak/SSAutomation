package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.POMPages.ExpeditedReg.Check_Your_Inbox;
import com.cucumbercraft.POMPages.ExpeditedReg.ExpeditedLetsGetStarted;
import com.cucumbercraft.POMPages.ExpeditedReg.Expedited_EnterEmail;
import com.cucumbercraft.POMPages.ExpeditedReg.Expedited_EnterMobileNumber;
import com.cucumbercraft.POMPages.IBAN.Add_IBAN;
import com.cucumbercraft.POMPages.RepayReinvest.Holdings;
import com.cucumbercraft.POMPages.RepayReinvest.SelectProduct;
import com.cucumbercraft.POMPages.WebReg.Web_CreatePassword;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExcelReader;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.framework.WebDriverUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class TitleSteps extends MasterStepDefs {
    WebDriver driver = DriverManager.getWebDriver();
    public static Map<String, String> data = new HashMap<>();
    String workBook = System.getProperty("user.dir") + properties.getProperty("ExcelPath");

    private final Holdings holdings = new Holdings(driver);
    private final SignInPg login = new SignInPg(driver);
    private final Dash_Board dashboard = new Dash_Board(driver);
    private final SelectProduct select = new SelectProduct(driver);
    private final Security_Page secure = new Security_Page(driver);
    Expedited_EnterMobileNumber mobile = new Expedited_EnterMobileNumber(driver);
    Web_CreatePassword createPassword = new Web_CreatePassword(driver);
    Expedited_EnterEmail email = new Expedited_EnterEmail(driver);
    ExpeditedLetsGetStarted letGS = new ExpeditedLetsGetStarted(driver);
    ProfileAndSettingsPg pfPg = new ProfileAndSettingsPg(driver);
    Check_Your_Inbox inbox = new Check_Your_Inbox(driver);
    Add_IBAN add = new Add_IBAN(driver);
    String url = properties.getProperty("OTPEndPoint");
    String OTPresponse;

    WebDriverUtil webUtil = new WebDriverUtil(driver);
    static Logger log = LogManager.getLogger(MasterStepDefs.class);


    public static ExcelReader testData = new ExcelReader();


    @Given("^User login with valid credential \"([^\"]*)\"$")
    public void userLoginwithValidCredential(String Tcno) throws Throwable {

        String path = System.getProperty("user.dir") + properties.getProperty("ExcelPath");
        String sheet_name = "Title_Validation";
        getdata = testData.reqExceldata(path, sheet_name, Tcno);
        Assertions.assertThat(getdata).isNotEmpty();
        try {
            login.LightLogin(getdata.get("Username"), getdata.get("Password"), getdata.get("Number"));
            ExtentCucumberAdapter.addTestStepLog("Login Successful");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("^User enters invalid credentials \"([^\"]*)\"$")
    public void userEntersInvalidCredentials(String Tcno) throws Throwable {

        String path = System.getProperty("user.dir") + properties.getProperty("ExcelPath");
        String sheet_name = "Title_Validation";

        Assertions.assertThat(getdata).isNotEmpty();
        try {

            login.EnterCredentials("UAT-WF-User22@mailinator.com", "Dummy!2022");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Enter and Re enter email for Title$")
    public void enterAndReEnterEmailforTitle() {
        try {
            email.enterEmailAddressforTitle();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Given("^User enters valid credentials \"([^\"]*)\"$")
    public void userEntersvalidCredentials(String Tcno) throws Throwable {

        String path = System.getProperty("user.dir") + properties.getProperty("ExcelPath");
        String sheet_name = "Title_Validation";

        Assertions.assertThat(getdata).isNotEmpty();
        try {

            login.EnterCredentials("AMLOnlineUser14-CDEN@mailinator.com", "Dummy!2025");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter the details for Title \"([^\"]*)\"$")
    public void userEnterTheDetailsTitle(String scenario) {
        try {

            updateTestDataConverter(getdata);


            letGS.enterdetails();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter the details for ECR \"([^\"]*)\"$")
    public void userEnterTheDetailsECR(String scenario) {
        try {

            updateTestDataConverter(getdata);


            letGS.enterdetailsecr();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^open Email for web registration")
    public void openEmailforWebRegistartion() {
        try {

            String token = inbox.emailVerificationToken(getdata.get("EmailAddress1"));
            inbox.launchEmailToken(token);
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^open Email for ECR")
    public void openEmailforECR() {
        try {

            String token = inbox.emailVerificationToken(getdata.get("EmailAddress"));
            inbox.launchEmailToken(token);
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^Enter all the details for mobile number$")
    public void enteralltheDetailsForMobileNumber() {
        try {

            mobile.enterMobileNumberdetailsforTitle();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("^Enter password web Registration Title$")
    public void enterPasswordForWebRegistration() {
        try {
            createPassword.enterPasswordWebTitle();
            createPassword.clickShowPassword();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^user enter invalid details for Title$")
    public void userEnterinvalidDetailsTitle() {
        try {


            letGS.enterinvaliddetails();

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("User navigates to Change email: number {string} email {string}")
    public void userNavigatesToChangeemail(String number, String email) throws Throwable {
        try {
            webUtil.waitForPageLoaded();
            webUtil.waitUntilElementVisible(pfPg.changeEmailbutton, 20);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.click(pfPg.changeEmailbutton);
            OTPresponse = apiDriver.getOTP(url, 200, number);
            webUtil.waitUntilElementVisible(pfPg.newEmailtxt, 20);
            webUtil.sendKeys(pfPg.newEmailtxt, email);
            webUtil.waitUntilElementVisible(pfPg.confirmbutton, 20);
            webUtil.click(pfPg.confirmbutton);
            webUtil.waitUntilElementVisible(pfPg.EmailEnterSecCodeText, 20);
            webUtil.sendKeys(pfPg.EmailEnterSecCodeText, OTPresponse);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            webUtil.waitUntilElementVisible(pfPg.EmailVerifyButton, 20);
            webUtil.click(pfPg.EmailVerifyButton);
            webUtil.waitForPageLoaded();
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));
            System.out.println("we are on this email change  page");
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(Util.takeScreenshotasFile(driver));

            ExtentCucumberAdapter.addTestStepLog("Successfully changed email");


        } catch (Exception e) {
            System.out.println("failed to change password ");
            e.printStackTrace();
        }
    }

    public Map<String, String> updateTestDataConverter(Map<String, String> updatedTestData) {

        for (String name : data.keySet()) {

            String url = data.get(name);
            if (url.equalsIgnoreCase("<Blank>")) {
                updatedTestData.replace(name, url, "");
            }
        }
        return updatedTestData;
    }

    @And("^User logout$")
    public void userLogout() {
        try {
            login.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("User logged out successfully");
    }

    @When("user clicks on View and Manage button to access summary")
    public void userSelectsTheProductAndClicksOnViewManageButton() {
        try {
            dashboard.clickProduct(getdata.get("Product"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @SneakyThrows
    @And("User click on Repay reinvest link on summary page")
    public void userClickOnRepayReinvestLinkOnSummaryPage() {
        holdings.repayReinvest_Click(getdata.get("Holding ID"));

    }

    @SneakyThrows
    @And("User choose {string}")
    public void userChooseOption(String option) {
        holdings.selectOption(option);
    }

    @And("user selects product from the dropdown list and proceeds to enter the desired amount")
    public void userSelectsProductFromDropdownAndEntersTheAmount() throws Throwable {
        select.userClicksOnDropdownMenuAndSelect(getdata.get("Products"), getdata.get("Amount"));
    }

    @And("User clicks confirm button to finalize product and amount for reinvestment")
    public void userClicksOnConfirmButton() {
        try {
            select.cofrmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Click confirm button on review page and finalize the transaction")
    public void clickOnTheReviewPageConfirmButton() {
        try {
            select.clickReviewConfirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User enter valid otp on the security code page$")
    public void userEnterTheValidOtp() {
        try {
            secure.enterOtp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Confirm transaction by clicking confirm button on verification code page")
    public void clickOnConfirmButton() {
        try {
            secure.clkCnfrmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("open Email and click on link {string}")
    public void openEmailAndClickOnLink(String email) {
        try {

            String token = inbox.emailVerificationToken(email);
            inbox.launchEmailToken(token);

        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @And("open link and validate Title {string}")
    public void openLinkAndValidateTitle(String link) {
        try {
            login.launchUrl(link);
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Given("Launch k13 application")
    public void LaunchK13application() {
        try {
            login.launchUrl("https://statesavings-qa.dev-anpost.com/");
            webUtil.clickLog(By.xpath("//button[@id='accept-recommended-btn-handler']"), "help");
            webUtil.clickLog(By.xpath("//a[text()='Help and Support']"), "help");
            webUtil.waitForPageLoaded();
            webUtil.scrollToView(By.xpath("//*[@href='/help-support/help-articles/how-to-purchase']"));
            webUtil.clickLog(By.xpath("//*[@href='/help-support/help-articles/how-to-purchase']"), "help");
            webUtil.clickLog(By.xpath("//a[text()='How to Purchase?']"), "help");
            webUtil.gettextlog(By.xpath("//*[@class='m14-help_content--article']/section"), String::equals, "");
            webUtil.waitForPageLoaded();
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }


    @Then("Validate Title {string}")
    public void validateTitle(String expected_page) {

        String title = driver.getTitle();

        webUtil.CompareString(title, String::equals, expected_page);


    }


    @And("Click on Back to your savings")
    public void clickOnBackToYourSavings() {
        driver.findElement(By.xpath("//a[text()='Back to Your Savings']")).click();
    }

    @And("Click on Backtostatesavings")
    public void clickOnBacktostatesavings() {
        driver.findElement(By.id("backLinkWithinLoginBlock")).click();

    }

    @And("Click on Backtostatesavings from Thank you")
    public void clickOnBacktostatesavingsfromThankyou() {
        driver.findElement(By.xpath("//a[@class='gtm-link-click header__nav-link header__nav-link--back ']")).click();
    }

    @And("User click on Backtostatesavings")
    public void userclickOnBacktostatesavings() {
        driver.findElement(By.xpath("//a[text()='Back to statesavings.ie']")).click();
    }


    @And("Click on Profile and Settings")
    public void clickOnProfileAndSettings() throws IOException, InterruptedException {
        add.navigateToProfileSetting();

    }


    @And("Validate Password reset page")
    public void validatePasswordResetPage() {
        webUtil.waitForPageLoaded();
        driver.findElement(By.id("reset-form")).isDisplayed();
        driver.getTitle();

    }
}
