package com.cucumbercraft.Models;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//@ExcelSheet("Buy Now")
public class PurchaseModel {

    @ExcelCellName("TestCaseName")
    private String testcase;

    @ExcelCellName("Entrypoint")
    private String entry;

    @ExcelCellName("Product")
    private List<String> product;

    @ExcelCellName("Journey")
    private String journey;

    @ExcelCellName("Aml_Indicator")
    private String amlFlag;

    @ExcelCellName("Source of Funds")
    private String sourceOfFunds;

    @ExcelCellName("Purpose of Account")
    private String purposeOfAccount;

    @ExcelCellName("Amount")
    private List<String> amount = new ArrayList<>();

    @ExcelCellName("Firstname")
    private String firstName;


    @ExcelCellName("PB_Holder_Joint")
    private String pbHolderFlag;

    @ExcelCellName("DOB")
    private String firstUserDOB;

    @ExcelCellName("DOB2")
    private String secondUserDOB;

    @ExcelCellName("SSCN")
    private String firstUserSSCN;

    @ExcelCellName("SSCN2")
    private String secondUserSSCN;

    @ExcelCellName("Surname")
    private String firstUserSurname;

    @ExcelCellName("Surname2")
    private String secondUserSurname;

    @ExcelCellName("PB_Holder_Fname")
    private String pbFirstName;

    @ExcelCellName("PB_Holder_Surname")
    private String pbSurName;

    @ExcelCellName("PB_Holder_Address")
    private String pbAddress;

    @ExcelCellName("PB_Holder_Fname2")
    private String pbFirstName2;

    @ExcelCellName("PB_Holder_Surname2")
    private String pbSurName2;

    @ExcelCellName("Address")
    private String address;

    @ExcelCellName("Email")
    private String firstUserEmail;

    @ExcelCellName("Email2")
    private String secondUserEmail;

    @ExcelCellName("Password")
    private String password;

    @ExcelCellName("Mobilenumber")
    private String mobileNum;

    @ExcelCellName("CDE")
    private List<String> CDE;

    @ExcelCellName("Paymnt_Details")
    private String paymentDetails;
}
