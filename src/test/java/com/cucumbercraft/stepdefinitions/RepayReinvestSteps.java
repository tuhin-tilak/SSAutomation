package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.Dash_Board;
import com.cucumbercraft.POMPages.RepayReinvest.*;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.ExcelReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class RepayReinvestSteps extends MasterStepDefs {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final SignInPg signInPg = new SignInPg(driver);
    private final Holdings holdings = new Holdings(driver);
    private final Dash_Board dashboard = new Dash_Board(driver);
    private final Cashin_Reinvest cashin_reinvest = new Cashin_Reinvest(driver);
    private final ReviewTransactionPage reviewTransactionPage = new ReviewTransactionPage(driver);
    private final Joint joint = new Joint(driver);

    Thank_You_Page thankYouPage = new Thank_You_Page(driver);
    private final ExcelReader testData = new ExcelReader();

    @Given("^User login using valid credentials for \"([^\"]*)\"$")
    public void userLoginUsingValidCredetialsAnd(String Tcno) throws IOException {

        String path = System.getProperty("user.dir") + properties.getProperty("ExcelPath");
        System.out.println(path);
//        String sheetname = "PreProdRegression";
        String sheetname = "Health_Report_UAT";
        getdata = testData.reqExceldata(config.getExcelpath(), config.getSheetName(), Tcno);

        try {
            signInPg.LightLogin(getdata.get("Username"), getdata.get("Password"), getdata.get("Number"));
            ExtentCucumberAdapter.addTestStepLog("Login Successful");
        } catch (Exception e) {
            Assert.fail("Login Failed", e);

        }
    }


    @When("^User select \"([^\"]*)\" and clicks on manage button$")
    public void userClickOnManageButton(String product) {
        try {
            dashboard.clickProduct(product);
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^User opens matured holding and selects method type$")
    public void userOpensMaturedHoldingAndSelectsMethodType() {
        try {
            holdings.repayReinvest_Click(getdata.get("Holding ID")).selectOption(getdata.get("MethodType"));
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^User choose product from dropdown and enter the amount$")
    public void userSelectsProductFromDropdownAndEnterTheAmount() {
        try {
            cashin_reinvest.selectingProduct(getdata.get("Products"), getdata.get("Amount"), getdata.get("POSB Account"));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User enter rest amount for CashIn$")
    public void userEnterRestForCashin() {
        try {

            cashin_reinvest.setCashIn_Enter_Amnt(getdata.get("Amount"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }


    }

    @And("^User clicks on allocate full amount button$")
    public void userClickonAlloactefullAmountButton() {
        try {
            cashin_reinvest.setAllocateFullAmt(getdata.get("Allocate Full Amount Index"));
//            cashin_reinvest.setAllocateFullAmt("3");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @When("^Validate IBAN status is displayed$")
    public void validateAddYourBankDetailsButtonIsDisplayed() {
        try {
            log.info("IBAN Status:" + cashin_reinvest.checkIBAN());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("^User Enters IBAN number and click on confirm bank details$")
    public void userEntersIBANNumberAndClickOnConfirmBankDetails() {
        try {
            cashin_reinvest.addIban(getdata.get("IBAN_Number"));
            cashin_reinvest.click_Checkbox_confirm();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^User then enters otp and press confirm$")
    public void userGivenThenEntersOtpAndPressConfirm() {
        try {
            cashin_reinvest.setOTPIBAN(getdata.get("Number")).enterOTPIBAN().clickConfirmOTP_PageIBAN();
            ExtentCucumberAdapter.addTestStepLog("OTP is generated");
        } catch (Exception e) {
            Assert.fail();
        }

    }

    @Then("^Validate OTP page and user enters valid otp$")
    public void validateOTPPageWhereUserEntersOtpWith() {
        try {
            cashin_reinvest
                    .setOTP(getdata.get("Number"))
                    .enterOTP()
                    .clickConfirmOTP_Page();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @When("^User clicks on manage button and select monthly instalment savings$")
    public void userClicksOnManageButtonAndSelectMonthlyInstalmentSavings() {
        try {
            dashboard.clickProduct(getdata.get("Product"));
            cashin_reinvest.clickmonthlysavings(getdata.get("POSB Account Number").replaceAll("[^0-9]", ""));
            ExtentCucumberAdapter.addTestStepLog("Instalment view and manage button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^user opens matured holding \"([^\"]*)\" and select method type \"([^\"]*)\"$")
    public void userOpensMaturedHoldingAndSelectMethodType(String id, String option) {
        try {
            holdings.userSelectsMatureProductAndClickOnButton(option, id);
            ExtentCucumberAdapter.addTestStepLog("Holding found and Reinvest/cashin button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @Then("^user enter \"([^\"]*)\" for Cashin$")
    public void userEnterForCashin(String amount) {
        try {
            cashin_reinvest.setCashIn_Enter_Amnt(amount);
            ExtentCucumberAdapter.addTestStepLog("Cashin amount entered");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^User clicks on confirm button$")
    public void userClicksOnConfirmButton() {
        try {
            cashin_reinvest.getConfirmButtonClick();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^Amount entered is less than maturity value user click on allocate to cash$")
    public void amountEnteredIsLessThanMaturityValueUserClickOnAllocateToCash() {
        try {
            cashin_reinvest.allocatetocash();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Substantiate particulars on review transaction page$")
    public void validatePreviousPageDetailsWithReviewTransactionPageAndCheckFeildTootipAsPerFsd() {
        try {
            reviewTransactionPage.reviewPagedetails(getdata.get("Holding ID"), getdata.get("Products"), getdata.get("Amount"), getdata.get("Allocate Full Amount Index"));
//            reviewTransactionPage.reviewPagedetails( getdata.get("Holding ID"), getdata.get("Products"), getdata.get("Amount"), "3");

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }

    @And("^User opens matured holding ID for Instalment Save and select method type$")
    public void userOpensMaturedHoldingIDAndSelectMethodType() {
        try {
            holdings.userSelectsMatureProductAndClickOnButton(getdata.get("MethodType"), getdata.get("Holding ID"));
            ExtentCucumberAdapter.addTestStepLog("Holding found and Reinvest/cashin button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^Amount entered is less than maturity value user clicks on allocate to cash$")
    public void amountEnteredIsLessThanMaturityValueUserClicksOnAllocateToCash() {
        try {
            cashin_reinvest.allocatetocashPrizeBonds();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User able to see allocate available funds modal$")
    public void userAbleToSeeAllocateAvailableFundsModal() {
        try {
            cashin_reinvest.availablefundsmodal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @And("^User click cancel button on review page$")
    public void userClickCancelButtonOnChooseProductPage() {
        try {
            cashin_reinvest.getCancelbutton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User redirected to thank you page$")
    public void userRedirectedToThankYouPage() {
        try {
            if (getdata.get("Product").contentEquals("Prize Bond") && getdata.get("MethodType").contentEquals("Cash-In")) {
                thankYouPage.verifyCashinPBThankYouPage();
            } else if (getdata.get("Product").contentEquals("Prize Bond") && getdata.get("MethodType").contentEquals("Reinvest")) {
                thankYouPage.verifyReinvestPBThankYouPage();
            } else {
                thankYouPage.verifyThankYouPage();
            }
            thankYouPage.clickBackBtn();
        } catch (Exception E) {
            Assert.fail(E.getMessage(), E);
        }
    }


    @And("^User click on Pending Transaction modal$")
    public void userClickOnPendingTransactionModal() {
        try {

            holdings.pendingTransaction(getdata.get("Holding ID"));
            cashin_reinvest.pendinTrnscModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User opens matured holding page selects method type$")
    public void userOpensMaturedHoldingPageSelectsMethodType() {
        try {
//            holdings.repayReinvest_Click(getdata.get("Holding ID")).reinvest_cashincontent().selectOption(getdata.get("MethodType"));
            joint.soleholding(getdata.get("Holding ID"));
        } catch (Exception e) {
            Assert.fail(e.getLocalizedMessage(), e);
        }
    }


    @And("^User validates error message with multiple combination \"([^\"]*)\"$")
    public void userValidatesErrorMessageWithMultipleCombination(int Name, DataTable table) {
        try {
            List<Map<String, String>> data = table.asMaps(String.class, String.class);

            cashin_reinvest.selectSingle(data.get(Name).get("Product"), data.get(Name).get("Amount"), "null");
//            cashin_reinvest.selectingProduct(getdata.get("Products"), getdata.get("Amount"), getdata.get("POSB Account Number"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User clicks on joint button$")
    public void userClicksOnJointButton() {
        try {
            joint.clickjoint();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User selects joint holding with mentioned {string}")
    public void userSelectsJointHoldingWithMentionedName(String name) {
        try {
            joint.selectJointWithName(name);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User verify notice button with mentioned \"([^\"]*)\"$")
    public void userVerifyNoticeButtonWithMentionedHoldingId(String holding) {
        try {
            joint.selectHoldingWithID(holding);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^User click on more details and verifies button present is same as on holdings page$")
    public void userClickOnMoreDetailsAndVerifiesButtonPresentIsSameAsOnHoldingsPage() {
        try {
            joint.jointMoredetails();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User clicks on button and verifies the modal content$")
    public void userClicksOnButtonAndVerifesYheModalContent() {
        try {
            joint.jointModals();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^Validate error message on OTP page with multiple combination$")
    public void validateErrorMessageOnOTPPageWithMultipleCombination() {
        try {
            cashin_reinvest.errorOTP(getdata.get("Number"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^User click on edit your order review page$")
    public void userClickOnEditYourOrderReviewPage() {
        try {
            reviewTransactionPage.clickEditOrder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("^User select more \"([^\"]*)\" and \"([^\"]*)\" to reinvest$")
    public void userSelectMoreAndToReinvest(String product, String amount) throws Exception {
        String[] products, amounts;
        if (product.contains(",")) {
            products = product.split(",");
            amounts = amount.split(",");
        } else {
            products = new String[1];
            amounts = new String[1];
            products[0] = product;
            amounts[0] = amount;
        }
        cashin_reinvest.slectMultipleEdit(products, amounts, getdata.get("POSB Account"));

    }


}