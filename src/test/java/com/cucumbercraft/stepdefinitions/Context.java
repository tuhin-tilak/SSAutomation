package com.cucumbercraft.stepdefinitions;

import com.cucumbercraft.Models.PurchaseModel;
import com.cucumbercraft.Models.TestData;
import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public class Context {

    WebDriver driver;
    TestData data;
    private PurchaseModel purchaseModel;

}
