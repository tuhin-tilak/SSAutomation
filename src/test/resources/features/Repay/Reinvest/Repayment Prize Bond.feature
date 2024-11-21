@Cash-In_Prize_Bond
Feature: Prize Bond Cash-in

  @PB_Error_On_Enter_Amount_Page
  Scenario Outline: PB Cash_In Amount more than available Less than available error
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Verify displayed error message content on Amount Page
    Examples:
      | Scenario | MethodType |
      | PB_TC_01 | Cash-In    |
      | PB_TC_03 | Cash-In    |


  @PB_Error_On_Enter_OTP_Page
  Scenario Outline: PB cash-in error on OTP Page
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the error message displayed on otp page
    Examples:
      | Scenario          | MethodType |
      | Blank_OTP_PB      | Cash-In    |
      | Invalid_OTP_PB    | Cash-In    |
      | Expired_OTP_PB    | Cash-In    |
      | OTP_Less_Digit_PB | Cash-In    |

  @PB_Cancel_Journey
  Scenario Outline: PB Cancel Journey
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the detail on review transaction page
    Then User click cancel button on review page
    Examples:
      | Scenario     | MethodType |
      | Blank_OTP_PB | Cash-In    |

  @PB_Error_On_Prize_Bond_Range_Window
  Scenario Outline: PB cash-in Error on bond range window
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User click on the confirm button
    And Verify error message displayed on prize bond range window
    Examples:
      | Scenario | MethodType |
      | PB_TC_05 | Cash-In    |

  @Build_Sanity_Add_IBAN
    @PB_Add_IBAN_IN_Journey
  Scenario Outline: Prize Bond: Add iban while cash-in
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User click on add iban button
    And Verify the enter iban page slide
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Verify the thank you prompt message displayed
    And User selects the Option
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the details on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    Examples:
      | Scenario | MethodType |
      | ADD_IBAN | Cash-In    |

  @PB_Cash_In_Enter_Amount
  Scenario Outline: PB cash-in Enter amount
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the details on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    Examples:
      | Scenario | MethodType |
      | PB_TC_03 | Cash-In    |
      | PB_TC_04 | Cash-In    |

  @PB_Cash_In_Allocate_Full_Amount
  Scenario Outline: PB cash-in Allocate full amount
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And Click on allocate full amount of Amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And Validate the details on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    Examples:
      | Scenario | MethodType |
      | PB_TC_02 | Cash-In    |

  @PB_Cash_In_Choose_Bond_Range
  Scenario Outline: PB cash-in prize bond range
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User selects all prize bonds
    And User select from given list
    And User click on the confirm button
    And Verify error message displayed on prize bond range window
    And Validate the details on review transaction page prize amount
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    Examples:
      | Scenario | MethodType |
      | PB_TC_05 | Cash-In    |













