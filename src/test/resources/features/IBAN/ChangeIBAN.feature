Feature: Add_IBAN using prompt message


  @ManageIban
  Scenario Outline: To verify prompt message of iban is displayed "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When Verify the prompt message is on dashboard
    Examples:
      | TCNO    |
      | TC_01   |
      | TC_02   |
      | AddIBAN |


  @ManageIban
  Scenario Outline: Verify that user able to add/amend IBAN using prompt message "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    Examples:
      | TCNO    |
      | TC_11   |
      | AddIBAN |


  @SIT
    @SITRegression
    @ManageIban
  Scenario Outline: Verify the IBAN using Verify Bank details option through prompt message "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And Verify the error message displayed on IBAN page
    And User click on the cancel button
    And User navigates to profile and setting
    Examples:
      | TCNO      |
      | TC_04     |
      | AddIBANIV |


  @SIT
    @SITRegression
    @ManageIban
  Scenario Outline: Verify that OTP expired using prompt message "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And verify the otp page
    And User enters the expired otp and click on confirm button
    And Verify error message is displayed
    And User clicks on didn't receive link
    Examples:
      | TCNO    |
      | TC_05   |
      | TC_09   |
      | TC_13   |
      | AddIBAN |


  @AddIbanError
  Scenario Outline: Error journey for add/amend iban "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And Verify the error message displayed on IBAN page
    Examples:
      | TCNO               |
      | AddIBAN            |
      | TC_Change_Invalid  |
      | TC_Add_Non_Sepa    |
      | TC_Add_Invalid     |
      | TC_Change_Non_Sepa |


  @ManageIban
  Scenario Outline: Add/amend iban
    Given User login using valid credential "<TCNO>"
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And verify the otp page
    Then User enters the otp received
    And User click on confirm button
    And Verify the thank you prompt message
    When User navigates to profile and setting
    And Verify the profile and setting page content
    And Verify iban is displayed in your bank detail
    And User click on notification tab
    And Verify the iban is updated
    Examples:
      | TCNO    |
      | AddIBAN |
      | TC_03   |
      | TC_06   |
      | TC_07   |
      | TC_08   |
      | TC_10   |
      | TC_12   |
      | TC_14   |


  @ManageIban
  Scenario Outline: Verify that user able to cancel the Add Bank details from Add/Amend Bank details page "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    And User click on the cancel button
    And User navigates to profile and setting
    Examples:
      | TCNO         |
      | TC_15        |
      | TC_17        |
      | AddIBAN(P&S) |


  @ManageIban
  Scenario Outline: Verify that user able to cancel the Add Bank details from security code page "<Tc_Name>"
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    And Verify the content on the page
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And verify the otp page
    And User clicks on otp cancel button
    Examples:
      | TCNO    |
      | TC_16   |
      | TC_18   |
      | AddIBAN |








