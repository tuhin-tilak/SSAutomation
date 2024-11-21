package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.POMPages.Dash_Board;
import com.cucumbercraft.framework.DriverManager;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.cucumbercraft.stepdefinitions.ReinvestSteps.getdata;


public class DashboardSteps {

    Context context;
    WebDriver driver = DriverManager.getWebDriver();
    Dash_Board dashBoard = new Dash_Board(driver);


    public DashboardSteps(Context context) {
        this.context = context;

    }

    @And("^User select State Savings Product and clicks on manage button$")
    public void userClicksOnManageButton() {
        try {
//            dashBoard.clickProduct(context.data.getProduct());
            dashBoard.clickProduct(getdata.get("Product"));
        } catch (Exception e) {
            Assert.fail(e.getStackTrace()[0].getMethodName());
        }
    }
}
