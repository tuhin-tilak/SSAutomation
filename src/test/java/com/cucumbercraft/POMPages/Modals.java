package com.cucumbercraft.POMPages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.cucumbercraft.POMPages.BuyNow.YourDetail;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;

public class Modals {

    WebDriver driver;
    WebDriverUtil webUtil;


    //For PB
    // Allocate full amount button modal
    private final By allocateAmountHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']//h1");
    private final String headerContent = "Allocate full amount";
    private final By paragraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//p");
    private final By reinvestParagraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//p");
    private final String paragraphContent = "By allocating the full amount, any other selections you have made will be removed. Are you sure you want to proceed?";
    private final String pbParagraph = "//section[@class='dashboard-modal__content js-modal active']//div/p[%d]";
    private final By cancelBtn = By.xpath("//div[@class='dashboard-modal-btn--container']/button[contains(text(),'Cancel')]");
    private final By yesImSureBtn = By.xpath("//div[@class='dashboard-modal-btn--container']/button[contains(text(),'Yes')]");
    private final By closeBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']/button[@class='modal_close js-closeModal']");

    // Confirm Button Modal
    private final By confirmBtnModalHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final String confirmBtnModalHeaderContent = "You must allocate the full amount";
    private final By confirmBtnModalParagraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//p");
    private final String ibanNotAddedConfirmButtonModalParagraph = "//section[@class='dashboard-modal__content js-modal active']//p[%d]";
    private final String ibanNotAddedConfirmButtonModalButtons = "//section[@class='dashboard-modal__content js-modal active']/div/button[%d]";
    private final String confirmBtnModalParagraphContent = "Reinvest the remaining amount to a State Savings product or request the remaining amount as a cash repayment to your bank account ending";
    private final By confirmBtnModalAllocateToCashBtn = By.id("btnAllocateToCash");
    private final String confirmBtnModalAllocateToCashBtnContent = "Allocate to cash";
    private final By confirmBtnModalGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_btnAllocateToCashReInvest']/preceding-sibling::button");
    private final By pbGoBackButtonModal = By.xpath("//button[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_goBackButtonReinvest']");
    private final By pbAddIbanButtonModal = By.xpath("//button[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_addBankDetails']");
    private final String confirmBtnModalGoBackBtnContent = "Go back";
    public By confirmBtnModalcloseBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']/button");
    //Cancel Button Modal
    private final By cancelBtnModalHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final String cancelBtnModalHeaderContent = "Cancel transaction";
    private final By cancelBtnModalParagraph = By.xpath("//section[@class='dashboard-modal__content js-modal active']//div/p");
    private final String cancelBtnModalParagraphContent = "You have selected to cancel this transaction. Any fund allocations you have made will be cancelled. Are you sure you want to proceed?";
    private final By cancelBtnModalYesCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnk_CancelReInvest']");
    private final String cancelBtnModalYesCancelBtnContent = "Yes, cancel";
    private final By cancelBtnModalNoGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnk_CancelReInvest']//preceding-sibling::button");
    private final String cancelBtnModalNoGoBackBtnContent = "No, go back";
    private final By reviewPageYesCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']");
    private final By reviewPageGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Review_lnk_Cancel']//preceding-sibling::button");
    private final By securityPageYesCancelBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnCancelRRProcess']");
    private final By securityPageGoBackBtn = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnCancelRRProcess']//preceding-sibling::button");


    //Verify
    private final By cancelbtnmodalcontentHeader = By.xpath("//section[@class='dashboard-modal__content js-modal active']/h1");
    private final By cancelbtnmodalcontentVerify = By.xpath("//section[@class='dashboard-modal__content js-modal active']/div[1]");
    private final By cancelbtnmodalgobackBtn = By.xpath("//div[@class='dashboard-modal-btn--container']//button[contains(text(),'No, go back')]");
    private final By cancelbtnmodalcancelBtn = By.xpath("//div[@class='dashboard-modal-btn--container']//button[contains(text(),'No, go back')]/following-sibling::input");

    //For FT Product
    String ftProductParagraph = "As this product has matured you must allocate the full amount. Please review your product allocation(s) and adjust accordingly.";
    private final By ftProductGoBackBtn = By.xpath("//section[@class='dashboard-modal__content js-modal active']//div/button");
    String ftProductMoreAmountPara = "The amount(s) entered exceed the available amount. Please review your product allocation(s) and adjust accordingly.";


    //For Cash-In Modal Allocate Button
    private final By cashinYesSureBtnModal = By.xpath("//a[@class='gtm-cta button button--primary']");
    private final By cashincancelBtnModal = By.xpath("//a[@class='gtm-cta button button--primary']/preceding-sibling::button");


    //For Cash-In Modal Cancel Button
    private final By cashinYesCancelBtnModal = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Detail_lnk_CancelCashIn']");
    private final By cashinGoBackBtnModal = By.xpath("//input[@id='p_lt_WebPartZone2_zoneContent_pageplaceholder_p_lt_ctl00_Repay_ReInvest_Security_btnCancelRRProcess']/preceding-sibling::button");

    //For Cash-In Modal Confirm Button
    private final By cashinConfirmBtnAllocateToCashinBtnModal = By.id("btnAllocateToCash");
    private final By cashinGoBackBtnmodal = By.xpath("(//section[contains(@class,'modal active')]//button)[2]");
    private final String cashinModalPara = "As this product has matured you must allocate the full amount. Please review your product allocation(s) and adjust accordingly. Alternatively you may allocate the residual amount to cash.";


    //Allocate full amount button for cash-in
    private final By allocatebutton = By.xpath("//div[@class='product-details-card--row']//button[@class='button button--secondary button--alt']");
    public static String amount;


    public Modals(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    /**
     * When amount entered for reinvest is greater than the maturity value
     *
     * @throws Exception
     */
    private void verifyMoreAmountModal() throws Exception {
        if (webUtil.getText(allocateAmountHeader).trim().contentEquals("Available amount exceeded")) {
            if (!webUtil.getText(paragraph).trim().contentEquals(ftProductMoreAmountPara))
                throw new ExceptionUtils("More amount entered Modal Paragraph Content not verified");
        } else {
            throw new ExceptionUtils("More amount entered Modal Header content not verified");
        }
    }

    public void clickOnCloseButtonModal() throws Exception {
        verifyMoreAmountModal();
        webUtil.waitUntilElementVisible(cancelbtnmodalcontentVerify, 20);
        if (webUtil.getText(cancelbtnmodalcontentVerify).trim().contentEquals("Close")) {
            try {
                webUtil.click(cancelbtnmodalcontentVerify);
            } catch (Exception e) {
                throw new ExceptionUtils("More amount entered for reinvest modal " + e.getMessage());
            }
        } else
            throw new ExceptionUtils("Modal close button not verified");
    }


    private void cancelbtnModalContentVerify() throws Exception {
        if (webUtil.isElementDisplayed(cancelbtnmodalcontentVerify, 20)) {
            if (webUtil.getText(cancelbtnmodalcontentHeader).contentEquals("Cancel transaction")) {
                if (webUtil.getText(cancelbtnmodalcontentVerify).contentEquals("You have selected to cancel this transaction. Are you sure you want to proceed?")) {
                    if (!driver.findElement(cancelbtnmodalcancelBtn).getAttribute("value").contentEquals("Yes, Cancel")) {
                        throw new ExceptionUtils("Cancel button modal yes, cancel button content not verified");
                    }
                    if (!webUtil.getText(cancelbtnmodalgobackBtn).contentEquals("No, go back")) {
                        throw new ExceptionUtils("Cancel button modal go, Back button content not verified");
                    }
                } else {
                    throw new ExceptionUtils("Cancel button modal paragraph content not verified");
                }
            } else {
                throw new ExceptionUtils("cancel button modal paragraph x-path not found!");
            }
        } else {
            throw new ExceptionUtils("Cancel button header x-path not found!");
        }
    }

    public void cancelBtnModalYesCancelBtn() throws Exception {
        cancelbtnModalContentVerify();
        if (webUtil.isElementclickable(cancelbtnmodalcancelBtn, 20)) {
            try {
                if (!driver.findElement(cancelbtnmodalcancelBtn).getAttribute("value").equals("Yes, cancel")) {
                    throw new ExceptionUtils("cancel button model Yes cancel button content not verified");
                }
                webUtil.click(cancelbtnmodalcancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
    }

    public void cancelBtnModalgoBaclBtn() throws Exception {
        cancelbtnModalContentVerify();
        if (webUtil.isElementclickable(cancelbtnmodalgobackBtn, 20)) {
            try {
                if (!webUtil.getText(cancelbtnmodalgobackBtn).contentEquals("No, go back")) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(cancelbtnmodalgobackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }


    //Cancel button content code
    private void cancelModalContentCheck() throws Exception {
        if (webUtil.getText(cancelBtnModalHeader).equalsIgnoreCase(cancelBtnModalHeaderContent)) {
            if (!webUtil.getText(cancelBtnModalParagraph).equalsIgnoreCase(cancelBtnModalParagraphContent)) {
                throw new ExceptionUtils("cancel button Modal paragraph content not verified");
            }
        } else {
            throw new ExceptionUtils("cancel button modal Modal Header content not verified");
        }
    }

    public void yesCancelBtn() throws Exception {
        if (webUtil.isElementclickable(cancelBtnModalYesCancelBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!driver.findElement(cancelBtnModalYesCancelBtn).getAttribute("value").equals(cancelBtnModalYesCancelBtnContent)) {
                    throw new ExceptionUtils("cancel button model Yes cancel button content not verified");
                }
                webUtil.click(cancelBtnModalYesCancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
    }

    public void noBackBtn() throws Exception {
        if (webUtil.isElementclickable(cancelBtnModalNoGoBackBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(cancelBtnModalNoGoBackBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(cancelBtnModalNoGoBackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }


    //Cancel button content code for review page review page
    public void reviewPageYesCancelBtn() throws Exception {
        if (webUtil.isElementclickable(reviewPageYesCancelBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(reviewPageYesCancelBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Yes button content not verified!");
                }
                webUtil.click(reviewPageYesCancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else {
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
        }
    }

    public void reviewPagenoBackBtn() throws Exception {
        if (webUtil.isElementclickable(reviewPageGoBackBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(reviewPageGoBackBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(reviewPageGoBackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }

    //Cancel button content code for Security page review page
    public void securityPageYesCancelBtn() throws Exception {
        if (webUtil.isElementclickable(securityPageYesCancelBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(securityPageYesCancelBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Yes button content not verified!");
                }
                webUtil.click(securityPageYesCancelBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else {
            throw new ExceptionUtils("Yes, Cancel button x-path not found!");
        }
    }

    public void securityPagenoBackBtn() throws Exception {
        if (webUtil.isElementclickable(securityPageGoBackBtn, 20)) {
            try {
                cancelModalContentCheck();
                if (!webUtil.getText(securityPageGoBackBtn).contains(cancelBtnModalNoGoBackBtnContent)) {
                    throw new ExceptionUtils("cancel button modal Go back button content not verified!");
                }
                webUtil.click(securityPageGoBackBtn);
            } catch (Exception e) {
                Assert.fail(e.getMessage());
            }
        } else
            throw new ExceptionUtils("No, Go back button x-path not found!");
    }


    //Confirm button content code
    private void confirmModalContentCheck() throws Exception {
        System.out.println(webUtil.getText(confirmBtnModalHeader));
        if (webUtil.getText(confirmBtnModalHeader).contentEquals(confirmBtnModalHeaderContent)) {
            if (!webUtil.getText(confirmBtnModalParagraph).contains(confirmBtnModalParagraphContent + " ~")) {
                throw new ExceptionUtils("confirm button modal paragraph content is changed");
            }
            if (!driver.findElement(confirmBtnModalAllocateToCashBtn).getAttribute("value").equals(confirmBtnModalAllocateToCashBtnContent)) {
                throw new ExceptionUtils("confirm button modal Yes cancel button content is changed");
            }
            if (!webUtil.getText(confirmBtnModalGoBackBtn).equals(confirmBtnModalGoBackBtnContent)) {
                throw new ExceptionUtils("confirm button modal Go back button content is changed");
            }
        } else {
            throw new ExceptionUtils("confirm button modal Header content is not same");
        }
    }

    public void allocateToCashBtn() throws Exception {
        try {
//            confirmModalContentCheck();
            webUtil.click(confirmBtnModalAllocateToCashBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void goBackModal() throws Exception {
        try {
            confirmModalContentCheck();
            webUtil.click(confirmBtnModalGoBackBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void goBackModalPB() {
        try {
            AddIbanPBAllocateModal();
            webUtil.click(pbGoBackButtonModal);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void addIbanPBModal() {
        try {
            AddIbanPBAllocateModal();
            if (webUtil.getText(pbAddIbanButtonModal).contentEquals("Add IBAN details"))
                webUtil.click(pbAddIbanButtonModal);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //Allocate full amount modal content
    public void allocateFullAmountModal() throws Exception {
        webUtil.waitUntilElementVisible(allocateAmountHeader, 10);
        if (webUtil.getText(allocateAmountHeader).contentEquals(headerContent)) {
            if (getdata.get("MethodType").contentEquals("Reinvest")) {
                if (!webUtil.getText(reinvestParagraph).contentEquals(paragraphContent))
                    throw new ExceptionUtils("allocate full amount button modal paragraph content is changed");
            } else {
                if (!webUtil.getText(paragraph).contentEquals(paragraphContent))
                    throw new ExceptionUtils("allocate full amount button modal paragraph content is changed");
            }
        } else {
            throw new ExceptionUtils("allocate full amount button modal header content is changed");
        }
    }

    public void allocateButtonModalPB() throws Exception {
        webUtil.waitUntilElementVisible(allocateAmountHeader, 20);
        if (webUtil.getText(allocateAmountHeader).contentEquals(headerContent)) {
//            System.out.println(webUtil.getText(By.xpath(String.format(pbParagraph,1))).trim());
//            System.out.println(webUtil.getText(By.xpath(String.format(pbParagraph,2))).trim());
//            System.out.println(webUtil.getText(By.xpath(String.format(pbParagraph,3))).trim());
            if (!webUtil.getText(By.xpath(String.format(pbParagraph, 1))).trim().
                    contentEquals("You have selected to allocate all available funds to Prize Bonds.")) {
                throw new ExceptionUtils("Modal content 1st paragraph not verified!");
            }
            if (!webUtil.getText(By.xpath(String.format(pbParagraph, 2))).trim().
                    contentEquals("Each prize bond has a value of €6.25 so the amount will be rounded down to" +
                            (" the nearest multiple of €6.25. Any remaining funds will be transferred to your bank account ending ~").
                                    concat(getdata.get("IBAN_Number").substring(getdata.get("IBAN_Number").length() - 4)).concat("."))) {
                throw new ExceptionUtils("Modal content 2nd paragraph not verified!");
            }
        }
    }

    public void AddIbanPBAllocateModal() throws Exception {
        webUtil.waitUntilElementVisible(allocateAmountHeader, 20);
        if (webUtil.getText(allocateAmountHeader).contentEquals(headerContent)) {
            if (!webUtil.getText(By.xpath(String.format(pbParagraph, 1))).trim().
                    contentEquals("You have selected to allocate all available funds to Prize Bonds.")) {
                throw new ExceptionUtils("Modal content 1st paragraph not verified!");
            }
            if (!webUtil.getText(By.xpath(String.format(pbParagraph, 2))).trim().
                    contentEquals("Each prize bond has a value of €6.25 so the amount will be rounded down to the nearest multiple of " +
                            "€6.25. Any remaining funds will be transferred to your bank account.")) {
                throw new ExceptionUtils("Modal content 2nd paragraph not verified!");
            }
            if (!webUtil.getText(By.xpath(String.format(pbParagraph, 3))).trim().contentEquals("As you have not added IBAN details you can add your " +
                    "bank details now or go back and allocate the remainder of your funds to a State Savings product.")) {
                throw new ExceptionUtils("Modal content 3rd paragraph not verified!");
            }
        } else {
            throw new ExceptionUtils("allocate full amount button modal header content is changed");
        }
    }

    public void cancelBtn() throws Exception {
        try {
            allocateFullAmountModal();
            webUtil.click(cancelBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void yesSureBtn() throws Exception {
        try {
            String[] prod = getdata.get("Products").split(",");
            String index = getdata.get("Allocate Full Amount Index");
//            System.out.println(Arrays.toString(prod));
            System.out.println(index);
//            System.out.println(prod[Integer.parseInt(index)-1]);
            if (index.contentEquals("null"))
                allocateFullAmountModal();
            else if (!(index.contentEquals("null") || index.contentEquals("Yes") || index.contentEquals("No"))) {
                if (prod[Integer.parseInt(index) - 1].contentEquals("Prize Bonds"))
                    allocateButtonModalPB();
            } else
                allocateFullAmountModal();
            webUtil.click(yesImSureBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    /**
     * Reinvest Choose Product page Confirm button Modal
     *
     * @throws Exception
     */
    //For FT product
    private void ftProductConfirmBtnModal() throws Exception {
        if (webUtil.getText(confirmBtnModalHeader).trim().contentEquals(confirmBtnModalHeaderContent)) {
            if (!webUtil.getText(confirmBtnModalParagraph).trim().contentEquals(ftProductParagraph)) {
                throw new ExceptionUtils("confirm button modal paragraph content not verified");
            }
            if (!webUtil.getText(ftProductGoBackBtn).trim().contentEquals(confirmBtnModalGoBackBtnContent)) {
                throw new ExceptionUtils("confirm button modal Go back button content not verified");
            }
        } else {
            throw new ExceptionUtils("confirm button modal Header content not verified!");
        }
    }

    /**
     * When amount entered is lesser and confirm button is clicked modal displayed go back button
     */
    public void goBackModalFt() {
        try {
            ftProductConfirmBtnModal();
            webUtil.click(ftProductGoBackBtn);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }


    /**
     * Cashin Modals
     */

    /**
     * Allocate button confirm button modal
     *
     * @throws Exception
     */
    public void cashinAllocateModalConfirmBtn() throws Exception {
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(cashinYesSureBtnModal, 20)) {
            try {
                if (webUtil.getText(cashinYesSureBtnModal).contains("Yes, I'm sure"))
                    webUtil.click(cashinYesSureBtnModal);
                else
                    throw new ExceptionUtils("Allocate cash modal Yes, I'm Sure Button content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cash in allocate modal -> Confirm Button");
            }
        } else {
            throw new ExceptionUtils("Cashin Yes sure button of allocate modal x-path may have changed");
        }
    }

    /**
     * Allocate button cancel button modal
     *
     * @throws Exception
     */
    public void cashinAllocateModalCancelBtn() throws Exception {
        if (webUtil.isElementDisplayed(cashincancelBtnModal, 20)) {
            try {
                if (webUtil.getText(cashincancelBtnModal).contains("Cancel"))
                    webUtil.click(cashincancelBtnModal);
                else
                    throw new ExceptionUtils("Allocate cash modal cancel button content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cashin allocate modal -> cancel button");
            }
        } else {
            throw new ExceptionUtils("Cashin Allocate modal cancel button x-path may have been changed");
        }
    }

    /**
     * Cancel button modal Yes Cancel button
     *
     * @throws Exception
     */
    public void cashinCancelBtnModalYesCancelBtn() throws Exception {
        if (webUtil.isElementDisplayed(cashinYesCancelBtnModal, 20)) {
            try {
                if (webUtil.getText(cashinYesCancelBtnModal).contains("Yes, Cancel"))
                    webUtil.click(cashinYesCancelBtnModal);
                else
                    throw new ExceptionUtils("Cancel button modal cancel content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cancel modal -> yes cancel button ");
            }
        } else {
            throw new ExceptionUtils("Cashin Cancel modal Yes cancel button x-path may have been changed");
        }
    }

    /**
     * Cancel button modal Go Back button
     *
     * @throws Exception
     */
    public void cashinCancelBtnModalGoBackBtn() throws Exception {
        if (webUtil.isElementDisplayed(cashinGoBackBtnModal, 20)) {
            try {
                if (!webUtil.getText(cashinGoBackBtnModal).contains("No, go back"))
                    webUtil.click(cashinGoBackBtnModal);
                else
                    throw new ExceptionUtils("Cash in cancel button modal go back button content is changed");
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe click is interrupted in cancel modal -> Go Back button ");
            }
        } else {
            throw new ExceptionUtils("Cashin Cancel modal Go-Back button x-path may have been changed");
        }
    }


    /**
     * Allocate full amount button
     */
    public void clickAllocateFullAmountBtn() throws Exception {
        webUtil.waitForPageLoaded();
        if (webUtil.isElementDisplayed(allocatebutton, 20)) {
            if (!webUtil.getText(allocatebutton).contains("Allocate full amount")) {
                throw new ExceptionUtils("Allocate button content is changed");
            }
            try {
                webUtil.click(allocatebutton);
            } catch (Exception e) {
                throw new ExceptionUtils("Allocate full amount button at cash in journey is interrupted");
            }
        } else {
            throw new ExceptionUtils("Maybe x-path for allocate full amount is changed");
        }
    }


    /**
     * you must allocate full amount modal content check
     *
     * @throws Exception
     */
    public void confirmAllocateToCashModalContent() throws Exception {
        ExtentCucumberAdapter.addTestStepLog("Validating Allocate to Cash Modal Content");
        webUtil.gettextlog(confirmBtnModalHeader, String::equals, confirmBtnModalHeaderContent);
        webUtil.gettextlog(confirmBtnModalParagraph, String::equals, cashinModalPara);
        webUtil.gettextlog(cashinGoBackBtnmodal, String::equals, confirmBtnModalGoBackBtnContent);
        webUtil.gettextlog(cashinConfirmBtnAllocateToCashinBtnModal, String::equals, confirmBtnModalAllocateToCashBtnContent);

    }

    /**
     * you must allocate full amount allocate to cash button click
     *
     * @throws Exception
     */
    public void cashinconfirmbuttonAllocatetocashinmodal() throws Exception {
        confirmAllocateToCashModalContent();
        webUtil.click(cashinConfirmBtnAllocateToCashinBtnModal);

    }

    /**
     * you must allocate full amount modal go back button click
     *
     * @throws Exception
     */
    public void cashingoBackBtnModal() throws Exception {
        confirmAllocateToCashModalContent();
        if (webUtil.isElementDisplayed(cashinGoBackBtnmodal, 20)) {
            try {
                webUtil.click(cashinGoBackBtnmodal);
            } catch (Exception e) {
                throw new ExceptionUtils("Maybe cash in confirm button modal cancel button is interrupted");
            }
        } else {
            throw new ExceptionUtils("Cancel button of confirm button of cash in journey x-path is changed");
        }
    }

    public void confirmButtonReinvestIbanNotAdded() throws Exception {
        if (webUtil.getText(allocateAmountHeader).trim().contentEquals(confirmBtnModalHeaderContent)) {
            if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalParagraph, 1))).trim().contentEquals("Reinvest the remaining amount to a Ireland State Savings product or request the remaining amount as a cash repayment to your bank account.")) {
                throw new ExceptionUtils("Modal 1st Paragraph not verified!");
            }
            if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalParagraph, 2))).trim().contentEquals("As you have not added IBAN details you can add your bank details now or go back.")) {
                throw new ExceptionUtils("Modal 2nd Paragraph not verified!");
            }
        } else
            throw new ExceptionUtils("Modal Header not verified!");
    }

    public void confirmButtonReinvestIbanNoteAddedClickAddIbanButton() throws Exception {
        webUtil.waitUntilElementVisible(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1)), 20);
        confirmButtonReinvestIbanNotAdded();
        if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 2))).trim().contentEquals("Add IBAN details")) {
            throw new ExceptionUtils("Add IBAN button content not verified!");
        }
        webUtil.click(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 2)));
    }

    public void confirmButtonReinvestIbanNoteAddedClickGoBackButton() throws Exception {
        webUtil.waitUntilElementVisible(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1)), 20);
        confirmButtonReinvestIbanNotAdded();
        if (!webUtil.getText(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1))).trim().contentEquals(confirmBtnModalGoBackBtnContent)) {
            throw new ExceptionUtils(confirmBtnModalGoBackBtnContent.concat("button content not verified!"));
        }
        webUtil.click(By.xpath(String.format(ibanNotAddedConfirmButtonModalButtons, 1)));
    }

    public Modals validateBuyNowModalContent() {
        WebElement element = driver.findElement(By.xpath("//section[contains(@class,'modal active')]"));

        String actualModalHdr = element.findElement(By.xpath(".//h1")).getText();
        String actualContent = element.findElement(By.xpath(".//p")).getText();
        String expectedModalHdr = "Registered for Ireland State Savings Online?";
        String expectedContent = "If you are already registered with Ireland State Savings Online, you may sign in now for fast, easy purchase.\n" +
                "\n" +
                "Alternatively, click ‘Buy now’ to proceed with your purchase.";
        webUtil.CompareString(actualModalHdr, String::equals, expectedModalHdr);
        webUtil.CompareString(actualContent, String::equals, expectedContent);
        return this;
    }


    public YourDetail clickGuest() {
        By btnBuyNow = By.xpath("//section[@data-name='sign-in-modal']//a[contains(@class,'add-product-guest') or text()='Buy now']");
        webUtil.click(btnBuyNow);
        return new YourDetail(driver);
    }

    public SignInPg clickSignIn() {
        By btnSignIn = By.xpath("//section[@data-name='sign-in-modal']//a[contains(@class,'add-product-sign-in') or text()='Sign in now']");
        webUtil.click(btnSignIn);
        return new SignInPg(driver);
    }


}
