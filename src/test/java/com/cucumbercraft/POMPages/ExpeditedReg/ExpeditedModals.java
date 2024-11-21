package com.cucumbercraft.POMPages.ExpeditedReg;

import com.cucumbercraft.framework.DriverFactory;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExpeditedModals {
    private final WebDriver driver;
    private final WebDriverUtil webUtil;
    private static final Logger log = LogManager.getLogger(DriverFactory.class);


    // Continue registration modal Xpath

    private final By contiBackbtn = By.xpath("//a[@class='gtm-cta button button--secondary button--alt js-closeModal']");
    private final By iHaveMyPinbtn = By.xpath("//a[@href='/your-savings/register/expedited-email']");
    private final By closebtn = By.xpath("//div[@class='modal_close js-closeModal']");

    //content of continue registration modal
    private final String modaltext = "//section[@class='dashboard-modal__content js-modal dashboard-popup active']%s";

    private final String regheader = "Continue registration";
    private final String regPara1 = "To continue with registration for Ireland State Savings Online you will need the PIN you received from Ireland State Savings.";
    private final String regPara2 = "Please allow 2-3 weeks from the date you purchased your Ireland State Savings product for your PIN to arrive.";

    //PIN NO Longer Valid modal
    private final By modalHeader = By.xpath("//section[@class='dashboard-modal__content js-modal dashboard-popup active']/h1");
    private final String modalHeaderContent = "Your PIN is no longer valid";
    private final String modalParagraph = "//section[@class='dashboard-modal__content js-modal dashboard-popup active']/p[%s]";
    private final By continueBtn = By.xpath("//div[@class='dashboard-modal-btn--container']//a[contains(text(),'Continue')]");
    private final By backToSSA = By.xpath("//a[@class='gtm-cta button button--secondary js-closeModal']");

    //Cancel Registration modal(Mobile page)
    private final By cancelHeader = By.xpath("//section[@class='resistance_modal js-modal active']/h1");
    private final String expCancelHeader = "Cancel registration";
    private final By cancelPara = By.xpath("//p[contains(text(),'Are you sure you want to cancel?')]");
    private final String expCancelPara = "Are you sure you want to cancel? The information you have entered will not be saved.";
    private final By goBackBtn = By.xpath("//button[contains(text(),'Go back')]");
    private final By cancelRegist = By.xpath("//a[@href='/begin-registration']");

    //Pin Reissue Request Exceeded
    private final String reIssueCont = "//section[@class='dashboard-modal__content js-modal active']%s";
    private final String expReissueHeader = "PIN re-issue requests exceeded";
    private final String expReissuePara1 = "You have exceeded the number of PIN re-issue requests permitted.";
    private final String expReissuePara2 = "To register for State Savings Online you must complete the full registration process. Select ‘Continue Registration’ below to proceed.";
    private final By reissueCancel = By.xpath("//div[@class='dashboard-modal-btn--container dashboard-modal-btn--container-margin-medium']/button");
    private final By reissueContiReg = By.xpath("//div[@class='dashboard-modal-btn--container dashboard-modal-btn--container-margin-medium']//a");


    public ExpeditedModals(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
    }

    //Continue Registration modal (I have my pin modal)
    public void clickIHavePinBtn() throws Exception {
        webUtil.scrollToView(iHaveMyPinbtn);

        if (webUtil.getText(iHaveMyPinbtn).equalsIgnoreCase("I have my PIN")) {
            try {
                webUtil.click(iHaveMyPinbtn);
                log.info("Clicked on I have my PIN button");
            } catch (Exception e) {
                throw new ExceptionUtils("I have my PIN Button click Interrupted");
            }
        } else {
            throw new ExceptionUtils("I have my PIN button content not matched");
        }

    }

    public void clickConRegBackBtn() throws Exception {
        webUtil.scrollToView(contiBackbtn);
        if (webUtil.isElementclickable(contiBackbtn, 20)) {
            if (webUtil.getText(contiBackbtn).equalsIgnoreCase("Back to StateSavings.ie")) {
                try {
                    webUtil.click(contiBackbtn);
                    log.info("Back to StateSaving.ie page");
                } catch (Exception e) {
                    throw new ExceptionUtils("Back to StateSaving.ie Button click Interrupted");
                }
            } else {
                throw new ExceptionUtils("content not matched for Back to StateSaving.ie button");
            }
        } else {
            throw new ExceptionUtils("Back to StateSaving.ie button xpath may have changed");
        }
    }

    public void Verify_ContRegCont() throws Exception {

        webUtil.gettextlog(By.xpath(String.format(modaltext, "/h1")), String::equalsIgnoreCase, regheader);
        log.info("Header content matched");
        webUtil.gettextlog(By.xpath(String.format(modaltext, "/p[1]")), String::equalsIgnoreCase, regPara1);
        log.info("paragraph 1 content matched");
        webUtil.gettextlog(By.xpath(String.format(modaltext, "/p[2]")), String::equalsIgnoreCase, regPara2);
        log.info("paragraph 2 content matched");

    }


    public void clickConRegCloseBtn() throws InterruptedException {
        if (webUtil.isElementclickable(closebtn, 5)) {
            webUtil.click(closebtn);
            log.info("Continue Registration modal Closed");
        } else {
            throw new ExceptionUtils("Closed Button xpath may have changed");
        }
    }

    //Pin no longer valid modal start
    private void verifyModal() throws Exception {

        webUtil.gettextlog(modalHeader, String::equalsIgnoreCase, modalHeaderContent);
        webUtil.gettextlog(By.xpath(String.format(modalParagraph, "1")), String::equalsIgnoreCase, "Your PIN is only valid for 14 days from date of issue.");
        webUtil.gettextlog(By.xpath(String.format(modalParagraph, "2")), String::equalsIgnoreCase, "To register for State Savings Online you must complete \" +\n" +
                "                    \"the full registration process. Select ‘Continue’ below to proceed.");

    }

    public void clickContinueBtnModal() throws Exception {

        verifyModal();
        webUtil.scrollToView(continueBtn);
        webUtil.gettextlog(continueBtn, String::equalsIgnoreCase, "Continue");
        webUtil.click(continueBtn);
        webUtil.waitForPageLoaded();
    }


    public void clickBackToSSABtnModal() throws Exception {

        verifyModal();
        webUtil.gettextlog(backToSSA, String::equalsIgnoreCase, "Back to StateSavings.ie");
        webUtil.click(continueBtn);
        webUtil.waitForPageLoaded();
    }


    // Cancel Registration modal start(on mobile page)
    private void verifyCancelRegistration() throws Exception {
        if (webUtil.isElementDisplayed(cancelHeader, 10)) {
            if (!webUtil.getText(cancelHeader).equals(expCancelHeader)) {
                throw new ExceptionUtils("Header content not matched of cancel registration modal");
            }
            if (!webUtil.getText(cancelPara).equals(expCancelPara)) {
                throw new ExceptionUtils("Paragraph content not matched of cancel registration modal");
            }
        } else {
            throw new ExceptionUtils("Cancel Registration Header xpath may have changed");
        }
    }

    public void clickGoBackBtn() throws Exception {
        verifyCancelRegistration();
        if (webUtil.isElementclickable(goBackBtn, 5)) {
            if (webUtil.getText(goBackBtn).equals("Go back")) {
                webUtil.click(goBackBtn);
            } else {
                throw new ExceptionUtils("go back button clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Go back button xpath may have changed");
        }
    }

    public void clickCancelRegistrationBtn() throws Exception {
        verifyCancelRegistration();
        if (webUtil.isElementclickable(cancelRegist, 5)) {
            if (webUtil.getText(cancelRegist).equals("Cancel Registration")) {
                webUtil.click(cancelRegist);
            } else {
                throw new ExceptionUtils("Cancel Registration button clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Cancel Registration button xpath may have changed");
        }
    }

    //Reissue Request Exceeded modal started
    private void verifyReissuePIN() throws Exception {
        System.out.println(String.format(reIssueCont, "/h4"));
        if (webUtil.isElementDisplayed(By.xpath(String.format(reIssueCont, "/h4")), 10)) {
            if (!webUtil.getText(By.xpath(String.format(reIssueCont, "/h4"))).equals(expReissueHeader)) {
                throw new ExceptionUtils("Reissue Pin modal Header not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(reIssueCont, "/p[1]"))).equals(expReissuePara1)) {
                throw new ExceptionUtils("Reissue Pin modal paragraph 1 not matched");
            }
            if (!webUtil.getText(By.xpath(String.format(reIssueCont, "/p[2]"))).equals(expReissuePara2)) {
                throw new ExceptionUtils("Reissue pin modal paragraph 2 not matched");
            }
        } else {
            throw new ExceptionUtils("Reissue pin header xpath may have changed");
        }
    }

    public void clickReissueCancelBtn() throws Exception {
        verifyReissuePIN();
        webUtil.scrollToView(reissueCancel);
        if (webUtil.isElementclickable(reissueCancel, 5)) {
            if (webUtil.getText(reissueCancel).equals("Cancel")) {
                webUtil.click(reissueCancel);
            } else {
                throw new ExceptionUtils("Reissue modal cancel button clicked Interrupted");
            }
        } else {
            throw new ExceptionUtils("Reissue modal cancel button xpath may have changed");
        }
    }

    public void clickReissueContiRegBtn() throws Exception {
        verifyReissuePIN();
        webUtil.scrollToView(reissueContiReg);
        webUtil.gettextlog(reissueContiReg, String::equalsIgnoreCase, "Continue Registration");
        webUtil.click(reissueContiReg);
    }
}




