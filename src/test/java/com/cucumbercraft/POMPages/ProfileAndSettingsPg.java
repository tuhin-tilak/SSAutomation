package com.cucumbercraft.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfileAndSettingsPg {

    //welcome back section
    public By greetings = By.xpath("//h4[@class='m40-dashboard-intro--greeting']");

//   Your Personal Details section

    //   Your Full name:
    public By headerYourPersonalDetails = By.xpath("//h3[text()='Your Personal Details']");
    public By yourFullName = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_profileFullName']");
    public By reqUpdatelink = By.xpath("//*[@data-modal='update-name-modal']");
    public By updatenamelabl = By.xpath("//h4[text()='Update your full name']");
    public By updateClosepopup = By.xpath("//section[@data-name='update-name-modal']/button");

    //  Your email Address section
    public By yourMailtitlelabel = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalEmail']");
    public By emailId = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_profileEmailAddress'] ");
    public By changeEmailbutton = By.xpath("//button[@data-modal='change-email-modal']");

    //  Change Email Address pop-up
    public By existingEmail = By.xpath("//span[@id='txtNewEmailValidator']");
    public By invalidEmailPopup = By.xpath("//span[@id='txtNewEmailValidator']");
    public By emptyEmail = By.id("errorEmptyEmail");
    public By emailPopupTitle = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmail']/h4");
    public By newEmailtxt = By.xpath("//input[@id='txtNewEmail']");
    public By cancelbutton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmail']/div[3]/button");
    public By confirmbutton = By.xpath("//button[@id='btnConfirmChangeEmail']");
    public By sameEmailPopup = By.xpath("//span[@id='txtNewEmailValidator']");
    //  Change Email Security Code pop-up
    public By EmailSectitlePopup = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmailSecurityCode']/h4");
    public By EmailSecCodelabeltitle = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmailSecurityCode']/div[2]/div/label");
    public By EmailEnterSecCodeText = By.xpath("//input[@id='txtChangeEmailSecurityCode']");
    public By EmailCancelButton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangeEmailSecurityCode']/div[3]/button");
    public By EmailVerifyButton = By.xpath("//button[@id='btnConfirmChangeEmailSecurityCode']");
    public By EmailDidntgetcodebutton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_lnkOpenChangeEmailResendCode'] ");

    //  Your Password section
    public By yourpasswordtitlelabel = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalPassword']");
    public By changePasswordbutton = By.xpath("//button[@onclick='ResetPasswordChangeModal()']");
    //  Change Password Security Code pop-up
    public By pwdSectitlePopup = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangePasswordSecurityCode']/h4");
    public By pwdEnterSecCodeText = By.xpath("//input[@id='txtChangePasswordSecurityCode']");
    public By pwdCancelButton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangePasswordSecurityCode']/div[3]/button");
    public By pwdVerifyButton = By.xpath("//button[@id='btnConfirmChangePasswordSecurityCode']");
    //  Password Change pop-up
    public By pwdOldPwdTxt = By.xpath("//input[@id='txtOldPassword']");
    public By pwdChangePwdTxt = By.xpath("//input[@id='txtNewPassword']");
    public By pwdChangeCancelbutton = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_ProfileSettingsModals_sectionChangePassword']/div[3]/button");
    public By pwdChangeConfirmbutton = By.xpath("//button[@id='btnConfirmChangePassword']");
    public By samePwd = By.xpath("//span[@id='txtNewPasswordValidator']");
    public By wrongPwd = By.xpath("//span[@id='txtNewPasswordValidator']");
    public By nullPwd = By.xpath("//span[@id='txtOldPasswordValidator']");
    public By requiredPwd = By.xpath("//*[@id='REVPassword']");
    public By nonRegisteredEmail = By.xpath("//span[@id='EmailForgetPasswordApiError']");
    public By nullEmail = By.xpath("//span[@id='RFVEmailForgetPasswordRequired']");
    public By invalidEmail = By.xpath("//span[@id='REVEmailForgetPasswordRegex']");
    //    Success Pop-up
    public By successPwd = By.xpath("(//div[@class='notification-box__text']//strong[text()='Thank you, your password has been updated'])[1]");

    //Update Mobile number section
    public By mobnumlabel = By.xpath("//div[@id='personalMobileNumber']");
    public By requstbtn = By.xpath("//*[@data-modal=\"update-mobile-modal\"]");
    public By updatemblnumtitle = By.xpath("//h4[text()='Update your mobile number']");
    public By closbutn = By.xpath("//section[@id='sectionChangeMobileNumber']//button[text()='Close']");

    //Your address section
    public By youraddtitle = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalAddress']");
    public By requestbutn = By.xpath("//*[@data-modal='update-address-modal']");
    public By updateadstitle = By.xpath("//h4[text()='Update your address']");
    public By closebtn = By.xpath("//section[@id='sectionChangeAddress']//button[text()='Close']");

    //Your account details section
    public By accntdetaillble = By.xpath("//h3[text()='Your Account Details']");
    public By SSCNtext = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalSSCN']");
    public By sscn = By.xpath("//*[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M43ProfileSettings_personalSSCN']/following-sibling::div/span");
    public By showsscnbtn = By.xpath("//button[text()='Show your SSCN code']");
    public By yourSSNlabl = By.xpath("//h4[text()='Your SSCN Code']");
    public By dwnldbtn = By.xpath("//button[@id='btnDownloadSSCN']");
    public By cancelbtn = By.xpath("//div[@class='small-12 medium-8 medium-pull-4 columns']//button[text()='Cancel']");

    WebDriver driver;

    public ProfileAndSettingsPg(WebDriver driver) {
        this.driver = driver;
    }

}
