package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.Excelutils;
import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.POMPages.BuyNow.*;
import com.cucumbercraft.POMPages.HomePage;
import com.cucumbercraft.POMPages.SignInPg;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BuyNowSteps extends MasterStepDefs{

    private final WebDriver driver = DriverManager.getWebDriver();
    private final HomePage homePage = new HomePage(driver);
    private final AdditionalInformation additionalInformation = new AdditionalInformation(driver);
    private final SignInPg signInPg=new SignInPg(driver);
    Excelutils excelRow = new Excelutils();
    PurchaseModel data;
    private AnpostPayment payment;
    private ThankYou thankYou;
    private YourOrder yourOrder;
    private JourneyHandler journeyHandler;
    private YourDetail yourDetail;
    private Review reviewPage;

    @Given("^Click buy now for \"([^\"]*)\"$")
    public void clickBuyNowFor(String testcase) {
        try {

            data = excelRow.getTestData(config.getBuyNowFilePath(),config.getBuyNowSheetName(),testcase);
            yourOrder = homePage.clickBuyNowOnHomePage();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @When("User select product for purchase")
    public void chooseProductForPurchase() {
        try {

            journeyHandler = yourOrder
                    .verifyProducts()
                    .addProductToCart(data.getProduct().get(0));

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Then("User can continue as guest or sign-in during the journey")
    public void selectJourney() {
        try {
            boolean isGuest = data.getJourney().contains("Guest");
            yourDetail = isGuest
                    ? journeyHandler.guestJourney()
                    : journeyHandler.signinJourney(data.getFirstUserEmail(), data.getPassword(), data.getMobileNum());

            if (data.getProduct().indexOf("Prize Bonds as a Gift") != 0) {
                journeyHandler
                        .getJourney(data.getJourney(), data)
                        .run();
            } else if(data.getProduct().indexOf("Prize Bonds as a Gift") == 0 && !isGuest){
                yourDetail
                        .validatePBApplicantDetails(data.getFirstUserEmail())
                        .agreeTermsPB()
                        .submitDetailsForPB();
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("User enter personal details on your details page for first applicant")
    public void userEnterPersonDetail() {
        try {

            String[] birthdate = data.getFirstUserDOB()
                    .replaceAll("-", "/")
                    .split("/");
            yourOrder = yourDetail.validateContentSoleYourDetails()
                    .withPrimaryApplicantSurname(data.getFirstUserSurname())
                    .withPrimaryApplicantBirthdate(birthdate)
                    .withPrimaryApplicantSSCN(data.getFirstUserSSCN())
                    .withPrimaryApplicantEmail(data.getFirstUserEmail())
                    .agreeTerms()
                    .submitDetails();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("Users enter personal details for both applicants as part of the guest journey")
    public void userEnterJointDetail() {
        try {
            String[] birthdatePrimary = data.getFirstUserDOB()
                    .replaceAll("-", "/")
                    .split("/");
            String[] birthdateSecondary = data.getFirstUserDOB()
                    .replaceAll("-", "/")
                    .split("/");
            yourOrder = yourDetail.validateContentSoleYourDetails()
                    .validateContentJointYourDetails()
                    .selectJoint()
                    .withPrimaryApplicantSurname(data.getFirstUserSurname())
                    .withPrimaryApplicantBirthdate(birthdatePrimary)
                    .withPrimaryApplicantSSCN(data.getFirstUserSSCN())
                    .withPrimaryApplicantEmail(data.getFirstUserEmail())
                    .withSecondaryApplicantSurname(data.getSecondUserSurname())
                    .withSecondaryApplicantBirthdate(birthdateSecondary)
                    .withSecondaryApplicantSSCN(data.getSecondUserSSCN())
                    .withSecondaryApplicantEmail(data.getSecondUserEmail())
                    .agreeTerms()
                    .submitDetails();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User enters person details Prize Bond Gift$")
    public void userEntersPersonDetails() {
        try {

            yourDetail.enterPBApplicantDetails(data.getFirstName(), data.getFirstUserSurname(), data.getFirstUserEmail(), data.getAddress());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Then("^User enters Prize Bond holder details$")
    public void userEntersJointDetail() {
        try {

            yourDetail.enterPBHolderDetails(data.getPbHolderFlag(), data).run();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }

    @Then("^Validate Your Order page$")
    public void validateYourOrder() {
        try {

            reviewPage = yourOrder
                    .assignDefaultValues(data.getProduct(), data.getAmount())
                    .addMultipleProduct(data)
                    .enterAmount()
                    .clickContReview();

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("^Validate product and amount on review page$")
    public void validateAndOnReviewPage() {
        try {
            reviewPage.verifyUserDetails(data.getProduct(), data.getFirstUserEmail(), data.getSecondUserEmail(), data)
                    .initializeProductFlags(data.getProduct())
                    .validateProductAndAmount(data.getProduct(), data.getAmount())
                    .selectAccountCDE(data.getSourceOfFunds(), data.getPurposeOfAccount())
                    .proceedToPayment(data.getAmlFlag(), data.getJourney());


        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("User selects customer level critical data elements")
    public void userSelectsCustomerLevelCDE() {
        try {
            System.out.println(data.getCDE());
            if (data.getAmlFlag().contains("N")) {
                payment = additionalInformation
                        .manageCde(data)
                        .submitCDEdetails();
            }
            payment = new AnpostPayment(driver);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

    }


    @Then("Enter payment details click pay")
    public void enterDetailsOnPaymentPage() {
        try {
            String[] paymentArr = data.getPaymentDetails().split(",");
            thankYou = payment.waitForPaymentPage()
                    .enterCardDetails(paymentArr[1], paymentArr[2], paymentArr[3])
                    .fillBillingCity(paymentArr[7])
                    .clickCheckbox()
                    .clickPayButton()
                    .retryPaymentIfFailed(paymentArr[1], paymentArr[2], paymentArr[3]);

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    @Then("Verify Thank You page")
    public void verifyThankYouPage() {
        try {
            thankYou.validateTyPage(data);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Given("Navigate to telesales {string}")
    public void navigateToTelesales(String testcase) {


            try {

                data=excelRow.getTestData(config.getBuyNowFilePath(), config.getBuyNowSheetName(), testcase);
                yourOrder=signInPg.loginTelesales();
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }


    }
}
