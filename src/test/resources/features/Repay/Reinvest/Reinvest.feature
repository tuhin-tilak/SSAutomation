Feature: User Reinvest in State Savings portal

  @Enter_Amount_Error
  Scenario Outline: Checking Enter Amount Errors
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Examples:
      | Scenario | MethodType | Error_Type                                     |
      | TC_27    | Reinvest   | Amount_More_Than_Available                     |
      | TC_28    | Reinvest   | Amount_Less_Than_Minimum                       |
      | TC_29    | Reinvest   | Amount_more_Than_maximum                       |
      | TC_39    | Reinvest   | Product INST issue 6 not displayed in dropdown |

  @Error_At_Otp_Page
  Scenario Outline: Error at otp page
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Verify the error message displayed on otp page
    Examples:
      | Scenario       | MethodType |
      | Blank_OTP      | Reinvest   |
      | OTP_Less_Digit | Reinvest   |
      | Expired_OTP    | Reinvest   |
      | Invalid_OTP    | Reinvest   |

  @Reinvest_Less_Amount_Entered_Modal
  Scenario Outline: Less amount entered modal verify
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Examples:
      | TCNO  | MethodType |
      | TC_28 | Reinvest   |

  @Reinvest_More_Amount_Entered_Modal
  Scenario Outline: More amount entered modal verify
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    Examples:
      | TCNO  | MethodType |
      | TC_27 | Reinvest   |


  @Reinvest_Cancel_Journey
  Scenario Outline: Reinvest Cancel Journey
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    Then User click cancel button on review page
    Examples:
      | Scenario  | MethodType |
      | Blank_OTP | Reinvest   |

  @Reinvest_Installment_To_Product
  Scenario Outline: Reinvest From Installment to any Product Enter Amount Journey
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
#      | TC_38    | Reinvest   |
      | TC_37    | Reinvest   |
#      | TC_55    | Reinvest   |
#      | TC_56    | Reinvest   |

  @Reinvest_Installment_To_Product_Allocate_Button
  Scenario Outline: Reinvest From Installment to any Product Allocate Amount Journey
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_40    | Reinvest   |

  @Reinvest_FT_To_FT_Enter_Journey
  Scenario Outline: Reinvest into single/multiple product enter amount journey
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | TCNO  | MethodType |
      | TC_32 | Reinvest   |
      | TC_33 | Reinvest   |
      | TC_34 | Reinvest   |
      | TC_35 | Reinvest   |
      | TC_36 | Reinvest   |
      | TC_41 | Reinvest   |
      | TC_42 | Reinvest   |
      | TC_43 | Reinvest   |
      | TC_45 | Reinvest   |
      | TC_46 | Reinvest   |
      | TC_53 | Reinvest   |

  @Reinvest_FT_To_Any_Product_Allocate_Journey
  Scenario Outline: Reinvest into any product Allocate full amount button journey
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | TCNO  | MethodType |
      | TC_31 | Reinvest   |
      | TC_40 | Reinvest   |
      | TC_52 | Reinvest   |
      | TC_54 | Reinvest   |

  @Build_Sanity_Add_IBAN
    @Reinvest_FT_To_PB_Allocate_Full_Amount
  Scenario Outline: Reinvest into PB Allocate full amount button journey_IBAN not added
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on PB allocate modal Add IBAN button
    And Verify the enter iban page for PB
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Validate the detail on review transaction page
    And User clicks on confirm button to finalize product and amount for reinvestment
    Then Verify content on the otp page
    And Verify the thank you message displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | TCNO  | MethodType |
      | TC_31 | Reinvest   |



































