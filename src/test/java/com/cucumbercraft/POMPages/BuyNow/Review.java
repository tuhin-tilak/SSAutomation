package com.cucumbercraft.POMPages.BuyNow;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.framework.APIReusuableLibrary;
import com.cucumbercraft.framework.ExceptionUtils;
import com.cucumbercraft.framework.WebDriverUtil;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Review {

    WebDriver driver;
    WebDriverUtil webUtil;
    YourOrder yourOrder;
    BuyNowContext context = BuyNowContext.getInstance();
    private final By table2 = By.xpath("(//table[@class='ec__review-details-basic-table']//td/p)[2]");
    private final By reviewAmntText = By.xpath(".//td[3]//p");
    private final By ddlSourceOfFunds = By.id("source-of-funds");
    private final By ddlPurposeOfAccount = By.id("purpose-of-account");
    APIReusuableLibrary apiReusuableLibrary = new APIReusuableLibrary();
    public boolean containsPB, onlyPB, containsPBAndPBGift;

    private final By btnSubmitReview = By.id("order-review-submit");
    private final By productElements = By.xpath("//tr[@class='js-ec-your-order-row']");


    public Review(WebDriver driver) {
        this.driver = driver;
        webUtil = new WebDriverUtil(driver);
        yourOrder = new YourOrder(driver);

    }

    public Review verifyUserDetails(List<String> products, String primaryUsername, String secondaryUsername, PurchaseModel purchaseModel) throws Exception {
        boolean notContainsFT = products.stream().noneMatch(s -> s.equalsIgnoreCase("National Solidarity Bonds") || s.equalsIgnoreCase("Savings Bonds") || s.equalsIgnoreCase("Savings Certificates"));
        boolean containsPB = products.stream().anyMatch(s -> s.contains("Prize Bonds") || s.contains("Prize Bonds as a Gift"));
        boolean containsPBgift = products.stream().anyMatch(s -> s.contains("Prize Bonds as a Gift"));
        context.setUserMapSole(apiReusuableLibrary.getFormattedUserDetailsByDate(primaryUsername));
        if (purchaseModel.getJourney().contains("Joint") && !(containsPBgift && products.size() == 1)) {
            context.setUserMapJoint(apiReusuableLibrary.getFormattedUserDetailsByDate(secondaryUsername));

        }
        List<String> personal = Arrays.asList(webUtil.getText(By.xpath("//table[@class='ec__review-details-basic-table']//td/p")).split("\\n"));

        String actualName = cleanseName(personal.get(0));
        context.setExpectedPrimaryName(buildExpectedName(context.getUserMapSole()));

        validateUserJourney(containsPB, notContainsFT, containsPBgift,
                personal, actualName,
                context.getExpectedPrimaryName(), context.getUserMapSole(), context.getUserMapJoint(),
                purchaseModel, webUtil, table2);

        return this;
    }

    private void validateUserJourney(boolean containsPB, boolean notContainsFT, boolean containsPBgift,
                                     List<String> personal, String actualName,
                                     String expectedName,
                                     Map<String, String> userMapSole,
                                     Map<String, String> userMapJoint,
                                     PurchaseModel purchaseModel,
                                     WebDriverUtil webUtil, By table2) {

        if ((containsPB && purchaseModel.getProduct().size() > 1) || !notContainsFT) {
            validateRegularJourney(purchaseModel.getJourney(), personal, actualName, expectedName, userMapSole, userMapJoint, table2);
        } else if (containsPBgift && purchaseModel.getProduct().size() == 1) {
            validateGiftJourney(purchaseModel.getJourney(), personal, actualName, purchaseModel, webUtil, table2, userMapSole);
        }
    }

    private void validateRegularJourney(String journey, List<String> personal, String actualName,
                                        String expectedName, Map<String, String> userMapSole,
                                        Map<String, String> userMapJoint, By table2) {
        String name = journey.contains("Joint") ? getExpectedSecondaryName(userMapJoint) : null;
        context.setExpectedSecondaryName(name);

        compareStrings(webUtil, actualName, expectedName);
        validateCommonPersonalFields(journey, personal, userMapSole);

        if (journey.contains("Joint")) {
            validateJointJourney(webUtil, table2, context.getExpectedSecondaryName(), userMapJoint);
        }
    }

    private void validateJointJourney(WebDriverUtil webUtil, By table2, String expectedSecndryName,
                                      Map<String, String> userMapJoint) {
        List<String> personal2 = getPersonalInfoFromTable(webUtil, table2);
        compareStrings(webUtil, cleanseName(personal2.get(0)), expectedSecndryName);

        validateJointPersonalFields(webUtil, personal2, userMapJoint);
    }

    private void validateGiftJourney(String journey, List<String> personal, String actualName,
                                     PurchaseModel purchaseModel, WebDriverUtil webUtil,
                                     By table2, Map<String, String> userMapSole) {
        if (journey.contains("Guest")) {
            validateGuestGift(actualName, personal, purchaseModel, webUtil, table2);
        } else {
            validateRegularGift(actualName, personal, webUtil, table2, userMapSole);
        }
    }

    private void validateGuestGift(String actualName, List<String> personal, PurchaseModel purchaseModel,
                                   WebDriverUtil webUtil, By table2) {
        String expectedGiftName = purchaseModel.getFirstName() + " " + purchaseModel.getFirstUserSurname();
        compareStrings(webUtil, actualName, expectedGiftName);
        compareStrings(webUtil, personal.get(1), purchaseModel.getFirstUserEmail());

        List<String> expectedAddress = Arrays.asList(purchaseModel.getAddress().split(","));
        validateAddress(webUtil, table2, expectedAddress);
    }

    private void validateRegularGift(String actualName, List<String> personal, WebDriverUtil webUtil,
                                     By table2, Map<String, String> userMapSole) {
        compareStrings(webUtil, actualName, buildExpectedName(userMapSole).toUpperCase());
        validateCommonPersonalFields("Regular", personal, userMapSole);
        validateAddressForRegularUser(webUtil, table2, userMapSole);
    }

    private void validateCommonPersonalFields(String journey, List<String> personal, Map<String, String> userMapSole) {
        compareStrings(webUtil, personal.get(1), userMapSole.get("dateOfBirth"));
        compareStrings(webUtil, personal.get(2), getIdentifier(journey, userMapSole));
        compareStrings(webUtil, personal.get(3), userMapSole.get("emailAddress"));
    }

    private void validateJointPersonalFields(WebDriverUtil webUtil, List<String> personal2, Map<String, String> userMapJoint) {
        compareStrings(webUtil, personal2.get(1), userMapJoint.get("dateOfBirth"));
        compareStrings(webUtil, personal2.get(2), userMapJoint.get("sscn"));
        compareStrings(webUtil, personal2.get(3), userMapJoint.get("emailAddress"));
    }

    private String getIdentifier(String journey, Map<String, String> userMapSole) {
        return journey.contains("Guest") ? userMapSole.get("sscn") : userMapSole.get("ppsNumber");
    }

    private void validateAddress(WebDriverUtil webUtil, By tableData, List<String> expectedAddress) {
        List<String> addressDetails = getPersonalInfoFromTable(webUtil, tableData);
        for (int i = 0; i < expectedAddress.size(); i++) {
            compareStrings(webUtil, addressDetails.get(i), expectedAddress.get(i));
        }
    }

    private void validateAddressForRegularUser(WebDriverUtil webUtil, By table2, Map<String, String> userMapSole) {
        List<String> personal2 = getPersonalInfoFromTable(webUtil, table2);
        compareStrings(webUtil, personal2.get(0), userMapSole.get("addressLine1").stripTrailing());
        compareStrings(webUtil, personal2.get(1), userMapSole.get("county"));
    }

    private void compareStrings(WebDriverUtil webUtil, String actual, String expected) {
        webUtil.CompareString(actual, String::equals, expected);
    }

    private List<String> getPersonalInfoFromTable(WebDriverUtil webUtil, By tableData) {
        return Arrays.asList(webUtil.getText(tableData).split("\\n"));
    }

    private String getExpectedSecondaryName(Map<String, String> userMapJoint) {
        return WordUtils.capitalizeFully(userMapJoint.get("firstName")) + " " +
                WordUtils.capitalizeFully(userMapJoint.get("lastName"));
    }

    private String buildExpectedName(Map<String, String> userMapSole) {
        return WordUtils.capitalizeFully(userMapSole.get("firstName")) + " " +
                WordUtils.capitalizeFully(userMapSole.get("lastName"));
    }

    private String cleanseName(String name) {
        final String regex = "\\b(Mr|Mrs|Ms|Miss|Dr|Doctor|Prof)\\s+";
        final Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(name).replaceAll("").trim();
    }

    public Review validateProductAndAmount(List<String> product, List<String> amount) {
        List<WebElement> prdctRow = webUtil.getElements(productElements);

        for (int i = 0; i < prdctRow.size(); i++) {

            String expectedAmount = product.get(i).contains("Prize Bonds") ? "€" + String.format("%,.2f", Double.parseDouble(amount.get(i)) * 6.25) : "€" + String.format("%,.2f", Double.parseDouble((amount.get(i))));
            String[] details = yourOrder.productMap.get(product.get(i));
            System.out.println(Arrays.toString(details));
            if (details != null) {
                if (details[0] != null) webUtil.verifyText(prdctRow, yourOrder.issueText, "", details[0], i, true);
                webUtil.verifyText(prdctRow, yourOrder.productText, "", product.get(i), i, true);
                webUtil.verifyText(prdctRow, reviewAmntText, "", expectedAmount, i, true);
            }
        }
        return this;

    }


    public Review initializeProductFlags(List<String> products) {
        containsPB = products.stream().anyMatch(s -> s.contains("Prize Bonds"));
        onlyPB = products.stream().anyMatch(s -> s.contains("Prize Bonds") && products.size() == 1);
        context.setOnlyPB(onlyPB);
        containsPBAndPBGift = products.containsAll(List.of("Prize Bonds", "Prize Bonds as a Gift")) && products.size() == 2;
        context.setContainsPBAndPBGift(containsPBAndPBGift);
        return this;
    }


    public Review selectAccountCDE(String soa, String poa) {


        if (onlyPB || containsPBAndPBGift) {
            selectSourceOfFunds(soa);

            if (webUtil.isElementVisible(By.id("ddlNationality"), 5)) {
                throw new ExceptionUtils("Critical Data Element Page displayed for PB purchase");
            }
        } else {
            selectSourceOfFunds(soa);
            selectpurposeofAccount(poa);
        }
        return this;
    }

    public void selectSourceOfFunds(String option) {
        webUtil.selectDropDown(ddlSourceOfFunds, select -> select.selectByVisibleText(option));
    }

    public void selectpurposeofAccount(String option) {
        webUtil.selectDropDown(ddlPurposeOfAccount, select -> select.selectByVisibleText(option));
    }

    private List<String> getCDEList(String journeyType, String indicator) {
        context.setJoint(journeyType.contains("Joint"));
        context.setGuest(journeyType.contains("Guest"));
        context.setSignIn(journeyType.contains("SignIn"));

        return context.isJoint()
                ? Arrays.asList(indicator.split(","))
                : Collections.singletonList(indicator);
    }


    public boolean checkCDE_Y(List<String> cdeList) {

        return (cdeList.size() == 1 && cdeList.get(0).equalsIgnoreCase("Y")) ||
                (cdeList.size() == 2 && cdeList.equals(List.of("Y", "Y")));
    }


    public void proceedToPayment(String indicator, String journey) {
        boolean CDE_Y = checkCDE_Y(getCDEList(journey, indicator));
        context.setCDEPrimaryFlag(getCDEPrimaryFlag(indicator, journey));
        context.setCDESecondaryFlag(getCDESecondaryFlag(indicator, journey));

        if(onlyPB || containsPBAndPBGift) {
            webUtil.gettextlog(btnSubmitReview, String::equals, "Continue to payment");
            webUtil.clickLog(btnSubmitReview, "Continue to payment");
        }else if (CDE_Y) {
            webUtil.gettextlog(btnSubmitReview, String::equals, "Continue to payment");
            webUtil.clickLog(btnSubmitReview, "Continue to payment");
        }  else {
            webUtil.gettextlog(btnSubmitReview, String::equals, "Continue");
            webUtil.clickLog(btnSubmitReview, "Continue Button");
        }
    }

    private boolean getCDEPrimaryFlag(String indicator, String journey) {
        List<String> cdeList = getCDEList(journey, indicator);
        return (cdeList.size() == 1 && cdeList.get(0).equalsIgnoreCase("Y")) ||
                (cdeList.size() == 2 && cdeList.get(0).equalsIgnoreCase("Y"));


    }

    private boolean getCDESecondaryFlag(String indicator, String journey) {
        List<String> cdeList = getCDEList(journey, indicator);
        return (cdeList.size() == 2 && cdeList.get(1).equalsIgnoreCase("Y"));
    }

}
