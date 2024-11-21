package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Data;

import java.util.List;

@Data
@ExcelSheet("Health_Report_UAT")
public class TestData {

    @ExcelCellName("Scenerio")
    private String testcaseName;

    @ExcelCellName("Username")
    private String username;


    @ExcelCellName("Password")
    private String password;

    @ExcelCellName("Number")
    private String mobileNumber;

    @ExcelCellName("Holding ID")
    private String holdingID;

    @ExcelCellName("IBAN_Number")
    private String IBAN;

    @ExcelCellName("Product")
    private String product;

    @ExcelCellName("Products")
    private List<String> reinvestProducts;

    @ExcelCellName("Amount")
    private List<String> reinvestAmounts;

    @ExcelCellName("Allocate Full Amount Index")
    private String allocateAllAmountIndex;

    @ExcelCellName("Prize Bond Range")
    private String bondRange;

    @ExcelCellName("Prize Amount")
    private String chooseAmount;

    @ExcelCellName("MethodType")
    private String optionType;

}
