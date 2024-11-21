package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.*;
import com.cucumbercraft.POMPages.RepayReinvest.*;
import com.cucumbercraft.framework.ExcelReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.cucumbercraft.framework.DriverManager.getWebDriver;

public class ReinvestSteps extends MasterStepDefs {
    private final WebDriver driver = getWebDriver();

    //POM Pages
    private final SignInPg login = new SignInPg(driver);
    private final Holdings holdings = new Holdings(driver);
    private final SelectProduct select = new SelectProduct(driver);
    private final Dash_Board dashboard = new Dash_Board(driver);
    private final Modals modal = new Modals(driver);
    private final Prize_Bond prize = new Prize_Bond(driver);
    private final Security_Page secure = new Security_Page(driver);
    private final ReviewTransactionPage review = new ReviewTransactionPage(driver);
    private final Thank_You_Page thank = new Thank_You_Page(driver);
    private final Cashin_Reinvest cashin = new Cashin_Reinvest(driver);

    //Excel
    public static ExcelReader testData = new ExcelReader();
    public static Map<String, String> getdata = new HashMap<>();

    @Given("^User login using valid credential \"([^\"]*)\"$")
    public void userLoginUsingValidCredential(String Tcno) throws Throwable {
//        String path = properties.getProperty("ExcelPath");
        String path = System.getProperty("user.dir") + properties.getProperty("ExcelPath");
//        String sheet_name = "PreProdRegression";
        String sheet_name = "Health_Report_UAT";
        getdata = testData.reqExceldata(config.getExcelpath(), config.getSheetName(), Tcno);
        Assertions.assertThat(getdata).isNotEmpty();
        try {
            login.LightLogin(getdata.get("Username"), getdata.get("Password"), getdata.get("Number"));
            ExtentCucumberAdapter.addTestStepLog("Login Successful");


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User logout from portal$")
    public void userLogoutFromPortal() {
        try {
            login.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("^User login using valid credential \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
    public void userLoginUsingValidCredential(String usrname, String pws, String number) throws Throwable {
        try {
            login.LightLogin(usrname, pws, number);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("user clicks View and Manage button to access summary")
    public void userSelectsTheProductAndClicksOnManageButton() {
        try {
            dashboard.clickProduct(getdata.get("Product"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User open the matured holding$")
    public void userOpenTheMaturedHolding() {
        try {
            holdings.clickRepRein(getdata.get("Holding ID"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify repay reinvest link is displayed$")
    public void verifyRepayReinvestLinkIsDisplayed() {
        try {
            holdings.checkRepReinvestLink();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    private void userClickonManagebtn(String pro) {
        try {
            dashboard.clickProduct(pro);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("User selects any matured holding and opt for {string}")
    public void userOpensMaturedHoldingAndSelects(String MethodType) {
        try {
//            Summary summary= SummaryFactory.getSummary(getdata.get("Product"),driver );
//            summary.clickReinvestCashin(getdata.get("Holding ID"));
//            ChooseOption option=OptionContext.selectOption(driver,MethodType);
//            option.setOption(MethodType);
//            option.clickOption();
//            ************

//            InvestmentStrategy  strategy= InvestmentStrategyFactory.createStrategy(driver,MethodType);
//            context.setStrategy(strategy);
//            context.getStrategy().clickInvestmentType();
//            context.executeInvestment(getdata.get("Products"), getdata.get("Amount"),getdata.get("Allocate Full Amount Index"));
//            ************
            holdings.selectMatureProduct(MethodType, getdata.get("Holding ID"));
            if (MethodType.equals("Cash-in") || MethodType.equals("null"))
                holdings.get_Last_For_Digit_IBAN();

        } catch (Exception e) {

            Assert.fail(e.getMessage());
        }
    }

    @And("^User opens non matured holding$")
    public void userOpensNonMaturedHolding() {
        try {
            holdings.selectMatureProduct(getdata.get("MethodType"), getdata.get("Holding ID"));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("user selects a product from the dropdown list and proceeds to enter the desired amount")
    public void userSelectsProductFromDropdownAndEntersTheAmount() throws Throwable {
        select.userClicksOnDropdownMenuAndSelect(getdata.get("Products"), getdata.get("Amount"));
    }

    @And("^User click on allocate full amount button$")
    public void userClickOnAllocateFullAmountAndClickOnConfirmButton() {
        try {
            select.allocate_fullamountandclickmodelconfrmbutton(Integer.parseInt(getdata.get("Allocate Full Amount Index")));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on allocate modal confirm button$")
    public void userClickOnAllocateModalConfirmButton() {
        try {
            modal.yesSureBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on PB allocate modal Add IBAN button$")
    public void userClickOnPBAllocateModalAddIBANButton() {
        try {
            modal.addIbanPBModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on PB allocate modal goBack button$")
    public void userClickOnPBAllocateModalGoBackButton() {
        try {
            modal.goBackModalPB();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on allocate modal cancel button$")
    public void userClickOnAllocateModalCancelButton() {
        try {
            modal.cancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Validate details with previous page and review transaction page,and check feilds, tootip as per fsd$")
    public void validateDetailsWithPreviousPageAndReviewTransactionPageAndCheckFeildsTootipAsPerFsd() throws Throwable {
        select.reviewPage1();
    }


    @And("User clicks on confirm button to finalize product and amount for reinvestment")
    public void userClicksOnConfirmButton() {
        try {
            select.cofrmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the details on the review page$")
    public void validateTheDetailsOnTheReviewPage() {
        try {
            review.reviewPagedetails(getdata.get("Holding ID"), getdata.get("Products"), getdata.get("Amount"), getdata.get("Allocate Full Amount Index"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("^Verify content on the otp page$")
    public void verifyContentonTheOtpPage() {
        try {
            secure.vrfySecurityPage();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Verify the error message displayed on otp page$")
    public void userEnterTheValidOtpAndVerifyTheErrorMessageDisplayed() {
        try {
            secure.otpPageError();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^User enter the valid otp on the security code page$")
    public void userEnterTheValidOtp() {
        try {
            secure.enterOtp();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Confirm the transaction by clicking confirm button on verification code page")
    public void clickOnConfirmButton() {
        try {
            secure.clkCnfrmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on cancel button on the security code page$")
    public void clickOnCancelButton() {
        try {
            secure.clickCancelBtn();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^user click on didnn't recive link$")
    public void userClickOnDidnnTReciveLink() {
        try {
            secure.didnotLink();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("Click confirm button on review page to finalize the transaction")
    public void clickOnTheReviewPageConfirmButton() {
        try {
            select.clickReviewConfirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on the review page cancel button$")
    public void clickOnTheReviewPageCancelButton() {
        try {
            select.clickReviewCancelButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the review page checkbox$")
    public void UserClickOnTheReviewPageCheckbox() {
        try {
            select.clickReviewCheckBox();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Verify error message displayed on review page$")
    public void verifyErrorMessageDisplayedOnReviewPage() {
        try {
            select.verifyErrorMessageReviewPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on the reinvest and cashin link$")
    public void clickOnTheReinvestAndCashinLink() {
        try {
//            prize.sort();
            prize.reinvestOption();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Check if the Repay-Reinvest link displayed$")
    public void checkIfTheRepayReinvestLinkDisplayed() {
        try {
            prize.checkReinvestLink();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("^Click on the reinvest tile$")
    public void clickOnTheReinvestTile() {
        try {
            prize.chooseReinvest();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("^Verify the choose amount page$")
    public void verifyTheChooseAmountPage() {
        try {
            prize.contentVerify();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("User enter the amount on choose amount page")
    public void enterTheAmountForReinvest() {
        try {
            prize.enterAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("Click confirm button to finalize the chosen amount on choose amount page")
    public void clickOnConfirmButtonOfAmountPage() {
        try {
            prize.clickConfirmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Click on cancel button of Amount Page$")
    public void clickOnCancelButtonOfAmountPage() {
        try {
            prize.clickCancelButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on allocate full amount of Amount page$")
    public void clickOnAllocateFullAmountOfAmountPage() {
        try {
            prize.clickAllocateFullAmount();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Verify displayed error message content on Amount Page$")
    public void verifyDisplayedErrorMessageContentOnAmountPage() {
        try {
            prize.error();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User click on confirm button on choose amount page$")
    public void userClickOnConfirmButtonOnChooseAmountPage() {
        try {
            prize.clickConfirmButton();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    @And("^User click on choose bond range window$")
    public void userClickOnChooseBondRangeWindow() {
        try {
            prize.clickBondRangeWindow();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the choose bond range window$")
    public void verifyTheChooseBondRangeWindow() {
        try {
            prize.verifyBondRangeWindow();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User selects all prize bonds$")
    public void userSelectsAllPrizeBonds() {
        try {
            prize.selectAllPBRange();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User select from given list$")
    public void userSelectFromGivenList() {
        try {
            prize.SelectPrizeBonds(getdata.get("Prize Bond Range"));
//            prize.SelectPrizeBonds("ACK816063 - ACK816070");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("Select from bond range: {string}")
    public void userSelectBondRange(String str) {
        try {
            prize.SelectPrizeBonds(str);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on the confirm button$")
    public void userClickOnTheConfirmButton() {
        try {
            prize.clickPrizeBondConfirmBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the details on review transaction page prize amount$")
    public void validateTheDetailsOnReviewTransactionPagePrizeAmount() {
        try {
            review.bondRangeReviewPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify error message displayed on prize bond range window$")
    public void verifyErrorMessageDisplayedOnPrizeBondRangeWindow() {
        try {
            prize.verifyBondRangeWindowError();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify selected prize bond is not displayed in choose bond range$")
    public void verifySelectedPrizeBondIsNotDisplayedInChooseBondRange() throws Exception {

        try {
            prize.goBackHomepage();
            userClickonManagebtn("Prize Bond");
            prize.reinvestOption();
            prize.chooseReinvest();
            prize.clickBondRangeWindow();
            if (!prize.chooseBondRangesVerify()) {
                log.info("Selected prize bonds are not in the list");
            } else {
                log.info("Selected prize bonds are in the list");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @And("^User click on cancel button$")
    public void userClickOnCancelButton() throws Exception {
        try {
            select.cancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on allocate to cash modal$")
    public void userClicksOnAllocateToCashModal() {
        try {
            modal.allocateToCashBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^User clicks on cancel button modal$")
    public void userClicksOnCancelButtonModal() {
        try {
            modal.goBackModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on yes cancel button modal$")
    public void userClickOnYesCancelButtonModal() {


    }

    @And("^User click on go back button modal$")
    public void userClickOnGoBackCancelButtonModal() {
        try {
            if (getdata.get("MethodType").contentEquals("Cash-In")) {
                modal.cashinCancelBtnModalGoBackBtn();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^user click on goback button modal$")
    public void userClickOnGobackButtonModal() {
        try {
            modal.goBackModalFt();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on close button modal$")
    public void userClickOnCloseButtonModal() {
        try {
            modal.clickOnCloseButtonModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on review Page yes cancel button modal$")
    public void userClickOnReviewPageYesCancelButtonModal() {
        try {
            modal.cancelBtnModalYesCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on review Page go back button modal$")
    public void userClickOnReviewPageGoBackCancelButtonModal() {
        try {
            modal.cancelBtnModalgoBaclBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on Security Page yes cancel button modal$")
    public void userClickOnSecurityPageYesCancelButtonModal() {
        try {
            modal.securityPageYesCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on Security Page go back button modal$")
    public void userClickOnSecurityPageGoBackCancelButtonModal() {
        try {
            modal.securityPagenoBackBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("^User click on the manage button and select the parent Account$")
    public void UserclickonthemanagebuttonandselecttheparentAccount() {
        try {
            Cashin_Reinvest cashin_reinvest = new Cashin_Reinvest(driver);
            dashboard.clickProduct(getdata.get("Product"));
            cashin_reinvest.clickmonthlysavings(getdata.get("POSB Account Number").replaceAll("[^0-9]", ""));
            ExtentCucumberAdapter.addTestStepLog("Instalment view and manage button clicked");
        } catch (Exception e) {
            Assert.fail(e.getMessage(), e);
        }
    }

    @And("^Click on back button thank you page$")
    public void clickOnBackButtonThankYouPage() throws Throwable {
        try {
            if (getdata.get("Product").contentEquals("Prize Bond") && getdata.get("MethodType").contentEquals("Cash-In")) {
                thank.verifyCashinPBThankYouPage();
            } else if (getdata.get("Product").contentEquals("Prize Bond") && getdata.get("MethodType").contentEquals("Reinvest")) {
                thank.verifyReinvestPBThankYouPage();
            } else {
                thank.verifyThankYouPage();
            }
            thank.clickBackBtn();
        } catch (Exception E) {
            Assert.fail(E.getMessage(), E);
        }
    }

    @And("^Click on The cancel button$")
    public void clickOnTheCancelButton() {
        try {
            secure.clickCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User select prize bond settings from profile and settings menu$")
    public void userSelectPrizeBondSettingsFromProfileAndSettingsMenu() {
        try {
            prize.clickPrizeBondSettings();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User decides \"([^\"]*)\" for existing winnings$")
    public void userDecidesForExistingWinnings(String option) {
        try {
            prize.clickPrizePaymentOption(option);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @And("^User clicks confirm on PPO modal$")
    public void userClicksConfirmOnPPOModal() {
        try {
            prize.prizePaymentModal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^User verifies the notification banner displayed for PPO settings changed$")
    public void userVerifiesTheNotifiactionDisplayedForPPOSettingsChanged() {
        try {
            prize.validateNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @And("^User validates if one option is enabled then other option is disabled$")
    public void userValidatesIfOneOptionIsEnabledThenOtherOptionIsDisabled() {
        prize.validatePPoption();
    }

    @And("^User add IBAN according to \"([^\"]*)\"\"([^\"]*)\"\"([^\"]*)\"$")
    public void userAddIBANAccordingTo(String journey, String IBAN, String number) {
        try {
            prize.addBankJourney(journey, IBAN, number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^User click add bank details on PPO modal$")
    public void userClickAddBankDetailsOnPPOModal() {
        try {
            prize.addBankDetailsModal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^Validate both PrizePayment option are disabled$")
    public void validateBothPrizePaymentOptionAreDisabled() {
        prize.checkAutoreinvest();
        prize.checkTransfer();
    }


    @When("User selects the product and clicks on manage button")
    public void userSelectTheProductAndClicksOnManageButton() {
        dashboard.clickProduct("Savings Certificate");
//        dashboard.clickProduct("Saving Bond");

    }
}