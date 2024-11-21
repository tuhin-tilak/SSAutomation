package com.cucumbercraft.POMPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.By;

public class ReqToAddHoldings {
    //your saving page
    public By userName = By.xpath("//h4[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_greetingTitle']");
    public By totalAmount = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_spanTotalAmount']");
    public By pdfSummary = By.xpath("//button[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_M40DashboardCards_btnDownloadSummary']");
    public By allHoldings = By.xpath("//*[@id='form']/div[5]/div[1]/section/div[4]/div/div[2]/button");


    //add product page
    public By closePage = By.xpath("//section[@id='sectionAddNewProduct']/div[@class='modal_close js-closeModal']");

    //list of all products
    public By selectProduct1 = By.xpath("//*[@id='ddlProductList']");
    public By selectProduct2 = By.xpath("//*[@id='sectionAddNewProduct']/descendant::select[2]");
    public By selectProduct3 = By.xpath("//*[@id='sectionAddNewProduct']/div[3]/div[3]//child::select");


    public By closeProductPage = By.xpath("//button[@class='button button--secondary button--alt js-confirm-close-modal']");
    //after click close product page button

    //Cancel request
    public By closeProductCancelRequest = By.xpath("//div[@class='dashboard-modal-btn--container']/button[@class='button button--secondary button--alt js-close-confirmation-modal']");
    public By confirmProductCancelRequest = By.xpath("//div[@class='dashboard-modal-btn--container']/button[@class='gtm-cta button button--primary js-closeModal']");
    public By confirmDownloadForm = By.xpath("//*[@id='btnDownloadForm']");

    //if without selecting and product we click confirm and download form button
    public By noProductSelected = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_AddNewProductModals_RF_ddlPL']");

    //add another product link
    public By addAnotherProductLink = By.xpath("//button[@class='icon-link icon-link--add product-details-card__add']");

    //common for all the product
    public By commenttextbox1 = By.xpath("//input[@id='txtOptional']");
    public By commenttextbox2 = By.xpath("//*[@id='sectionAddNewProduct']/div[2]/div[2]//child::input[@id='txtOptional2']");
    public By commenttextbox3 = By.xpath("//*[@id='sectionAddNewProduct']/div[3]/div[3]//child::input[@id='txtOptional']");

    //Prize Bond Product
    public By bondNumber = By.xpath("//input[@id='txtPrizeBondNumber']");
    public By bondNumberError = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_AddNewProductModals_cv_txtPrizeBond']");


    //From here the account number is same for all the products so we're taking a common X_PATH for all
    public By accountNumber1 = By.xpath("//input[@id='txtSavingsAccountNumber']");
    public By accountNumber2 = By.xpath("//*[@id='sectionAddNewProduct']/div[2]/div[2]//child::input[@id='txtSavingsAccountNumbeNaN']");
    public By accountNumber3 = By.xpath("//*[@id='sectionAddNewProduct']/div[3]/div[3]//child::input[@id='txtSavingsAccountNumbeNaN']");


    public By accountNumberError = By.xpath("//span[@id='p_lt_WebPartZone5_zoneContent_pageplaceholder_p_lt_ctl00_AddNewProductModals_cv_txtSavingsAccountNumber']");

    //Remove Button
    public By removeProduct1 = By.xpath("//*[@id='sectionAddNewProduct']/div[2]/div[2]/div/button");
    public By removeProduct2 = By.xpath("//*[@id='sectionAddNewProduct']/div[3]/div[3]/div/button");
    public By removeProduct3 = By.xpath("//*[@id='sectionAddNewProduct']/div[3]/div[4]/div/button");


    //Thanks
    public By thanksContent = By.xpath("//*[@id='sectionAddNewProductThankYou']//h4");

    //Thanks Form Close Button
    public By thanksFormCloseButton = By.xpath("//section[@data-name='add-missing-product-thank-you-modal']//button[@aria-label='Close modal']");

    WebDriver driver;

    public ReqToAddHoldings(WebDriver driver) {
        this.driver = driver;
    }
}
