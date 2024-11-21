package com.cucumbercraft.framework;

import org.aeonbits.owner.Config;
import org.checkerframework.checker.units.qual.K;

@Config.Sources({"classpath:Global Settings.properties"})
public interface PropertyConfig extends Config {
    @Key("Environment")
    @DefaultValue("uat")
    String env();

    @Key("statesavings.${Environment}.url")
    String getUrl();

    @Key("statesavings.${Environment}.baseuri")
    String getbaseURI();

    @Key("HeaderPath")
    String getHeaderPath();

    @Key("BuyNowExcelPath")
    String getBuyNowFilePath();

    @Key("ExcelPath")
    String getExcelpath();

    @Key("statesavings.${Environment}.purchase.sheetname")
    String  getBuyNowSheetName();

    @Key("statesavings.${Environment}.regression.sheetname")
    String getSheetName();
}
