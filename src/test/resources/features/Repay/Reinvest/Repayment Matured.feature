@CashIn_FT_Matured
Feature: User CashIn Matured in State Savings portal

  @Matured_Error_On_Enter_Amount
  Scenario Outline: Error on enter amount page for FT product
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Verify the error message displayed
    Examples:
      | Scenario | MethodType | Comment                    |
      | TC_01    | Cash-In    | Amount more than available |
      | TC_01_1  | Cash-In    | Blank                      |


  @Matured_Review_Page_Cancel_Journey
  Scenario Outline: Error on OTP page for FT product
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    Then User click cancel button on review page
    Examples:
      | Scenario | MethodType |
      | TC_05    | Cash-In    |


  @Matured_Error_On_Enter_OTP_Page
  Scenario Outline: Error on OTP page for FT product
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the error message displayed on otp page
    Examples:
      | Scenario       | MethodType |
      | Blank_OTP      | Cash-In    |
      | OTP_Less_Digit | Cash-In    |
      | Expired_OTP    | Cash-In    |
      | Invalid_OTP    | Cash-In    |

  @Matured_Cancel_Journey
  Scenario Outline: Matured Cancel Journey
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    Then User click cancel button on review page
    Examples:
      | Scenario  | MethodType |
      | Blank_OTP | Cash-In    |

  @Matured_Must_Allocate_Full_Amount_Modal
  Scenario Outline: Must allocate full amount modal amount entered is less
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And User clicks on the Allocate to cash-in button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario            | MethodType |
      | Less_amount_cash_in | Cash-In    |

  @Matured_Check_If_Iban_Setup
  Scenario Outline: Add iban link displayed or not check
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User open the matured holding
    And User click on add iban button
    Examples:
      | Scenario |
      | TC_02    |

  @Build_Sanity_Add_IBAN
    @Matured_Add_IBAN_IN_Journey
  Scenario Outline: Add iban while cash-in
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User open the matured holding
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
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_05    | Cash-In    |

  @Matured_CashIn_FT_Product_Allocate_Modal
  Scenario Outline: Matured Cash In for FT product using allocate full amount button
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_05    | Cash-In    |
      | TC_06    | Cash-In    |
      | TC_09    | Cash-In    |
      | TC_26    | Cash-In    |

  @Matured_CashIn_FT_Product_Enter_Amount
  Scenario Outline: Matured Cash In for FT product by enter amount
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_07    | Cash-In    |
      | TC_08    | Cash-In    |
      | TC_17    | Cash-In    |
      | TC_18    | Cash-In    |
      | TC_25    | Cash-In    |

  @Matured_CashIn_Installment_SSA_ChildCare_Product_Enter_Amount
  Scenario Outline: Matured Cash In For Installment/Child-Care/SSA Using Enter amount
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User selects any matured holding and opt for "<MethodType>"
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_14    | Cash-In    |
      | TC_15    | Cash-In    |

  @Matured_CashIn_Installment_SSA_ChildCare_Product_Allocate_Modal
  Scenario Outline: Matured Cash In For Installment/Child-Care/SSA Using Allocate full amount
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User selects any matured holding and opt for "<MethodType>"
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType |
      | TC_14    | Cash-In    |






