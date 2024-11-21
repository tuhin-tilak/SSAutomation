package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.Modals;
import com.cucumbercraft.POMPages.RepayReinvest.ReviewTransactionPage;
import com.cucumbercraft.POMPages.RepayReinvest.Thank_You_Page;
import com.cucumbercraft.POMPages.RepayReinvest.Repayment;
import com.cucumbercraft.POMPages.RepayReinvest.SelectProduct;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class RepaymentSteps {
    private final WebDriver driver = DriverManager.getWebDriver();
    private final Repayment cashIn = new Repayment(driver);
    private final SelectProduct select = new SelectProduct(driver);
    private final Repayment cashin = new Repayment(driver);
    private final ReviewTransactionPage review = new ReviewTransactionPage(driver);
    private final Thank_You_Page thank = new Thank_You_Page(driver);
    private final Modals modal = new Modals(driver);
    static final Logger log = LogManager.getLogger(RepaymentSteps.class);

    @Then("^User enters Amount for Cashin$")
    public void userEnterAmountForCashin() {
        try {
            cashIn.verifyCashPage();
            cashIn.enterAmount();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Then("User enters {string} for Cashin")
    public void userEnterAmount(String str) {
        try {
            cashIn.verifyCashPage();
            cashIn.enterAmount(str);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Validate details with previous page and review transaction page as per fsd$")
    public void validateDetailsWithPreviousPageAndReviewTransactionPageAsPerFsdAnd() {
        try {
            ReviewTransactionPage review = new ReviewTransactionPage(driver);
            review.reviewAllocated();
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^User click on allocate full amount$")
    public void userClickOnAllocateFullAmount() {
        try {
            cashIn.clickFullAmountandConfirm(ReinvestSteps.getdata.get("Allocate Full Amount Index"));
//            cashIn.clickFullAmountandConfirm("yes");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on the confirm button$")
    public void userClicksOnTheConfirmButton() throws Exception {
        try {
            cashIn.clickCnfrmButton();
            log.info("Confirm button on enter amount page is clicked");
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @And("^Verify the error message displayed$")
    public void verifyTheErrorMessageDisplayed() {
        try {
            cashin.Error();
            log.info("Error message displayed");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the enter iban page$")
    public void verifyTheEnterIbanPage() {
        try {
            cashIn.verifyIbanPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the enter iban page for PB$")
    public void verifyTheEnterIbanPageForPB() {
        try {
            cashIn.verifyIbanPagePBReinvest();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User enters the iban$")
    public void userEntersTheIban() {
        try {
            cashIn.enterIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on checkbox$")
    public void clickOnCheckbox() {
        try {
            cashIn.clickCheckbox();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on checkbox IBAN not added$")
    public void clickOnCheckboxIban() {
        try {
            cashIn.clickCheckboxIBANNotadded();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Clicks on confirm button$")
    public void clicksOnConfirmButton() {
        try {
            cashIn.ibanClickConfirm();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the security page$")
    public void verifyTheSecurityPage() {
        try {
            cashIn.verifySecurityPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Enter the Otp received$")
    public void enterTheOtpReceived() {
        try {
            cashIn.enterSecurityCode();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on confirm button on security page$")
    public void clickOnConfirmButtonOnSecurityPage() {
        try {
            cashIn.clickConfirmBtnOtpPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the thank you message displayed$")
    public void verifyTheThankYouMessageDisplayed() {
        try {
            cashIn.verifyThankYouMessage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Clicks on cancel button$")
    public void clicksOnCancelButton() {
        try {
            cashIn.ibanClickCancel();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on the Allocate to cash-in button$")
    public void userClicksOnTheAllocateToCashInButtonandClickonconfirmbutton() {
        try {
            modal.cashinconfirmbuttonAllocatetocashinmodal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on the Allocate Go back button$")
    public void userClicksOnTheAllocateGoBackButton() {
        try {
            modal.cashingoBackBtnModal();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on allocate full amount and click on the cancel button$")
    public void userClickOnAllocateFullAmountAndClickOnTheCancelButton() {
        try {
            modal.clickAllocateFullAmountBtn();
            modal.cashinAllocateModalCancelBtn();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @And("^Validate the details with previous page and review transaction page as per fsd$")
    public void validateTheDetailsWithPreviousPageAndReviewTransactionPageAsPerFsd() {
        try {
            review.reviewAllocatedCashin();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User click on add iban button$")
    public void userClickOnAddIbanButton() {
        try {
            cashIn.clickIbanAddButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify the enter iban page slide$")
    public void verifyTheEnterIbanPageSlide() {
        try {
            cashIn.verifyIbanPageAddIban();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^User clicks on the cancel button$")
    public void userClicksOnTheCancelButton() {
        try {
            cashIn.clickCancelButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the details on review transaction page$")
    public void validateTheDetailsOnReviewTransactionPage() {
        try {
            if (getdata.get("MethodType").contentEquals("Reinvest") && getdata.get("Product").contentEquals("Prize Bond")) {
                review.reviewPBReinvest();
            }
            review.cashInReviewPB();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on allocate full amount button and click on confirm button$")
    public void clickOnAllocateFullAmountButtonAndClickOnConfirmButton() {
        try {
            modal.clickAllocateFullAmountBtn();
            cashIn.clickCnfrmButton();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Validate the detail on review transaction page$")
    public void validateTheDetailOnReviewTransactionPage() {
        try {
            review.reviewAllocatedCashin();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Click on the delay checkbox$")
    public void clickOnTheDelayCheckbox() {
        try {
            review.delayCheckbox();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Verify thank you page$")
    public void verifyThankYouPage() {
        try {
            thank.verifyThankYouPage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @And("^Thank you page is displayed$")
    public void thankYouPageIsDisplayed() {
        try {
            thank.verifyHeaderThankYouPage();
        } catch (Exception e) {
            Assert.fail();
        }
    }


    //Adding iban while cash-in journey

}
