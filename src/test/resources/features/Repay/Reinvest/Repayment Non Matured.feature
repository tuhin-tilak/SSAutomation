@Cash_in_non_matured
Feature: Cash_In Non-Matured Journeys

  @negative
    @Non_Matured_Error_On_Enter_Amount_Page
  Scenario Outline: Non - Matured: Error on enter amount page for FT product
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
    Then User enters "<Amount>" for Cashin
    And User clicks on the confirm button
    And Verify the error message displayed
    Examples:
      | Scenario | Amount   |
      | TC_04    | 38129.53 |
      | TC_04    |          |

  @Non_Matured_Error_On_Enter_OTP_Page
  Scenario Outline: Non - Matured: Error on OTP page for FT product
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the error message displayed on otp page
    Examples:
      | Scenario       |
      | Blank_OTP      |
      | OTP_Less_Digit |
      | Expired_OTP    |
      | Invalid_OTP    |

  @Non_Matured_Repay_Reinvest_Link_For_New_Product
  Scenario Outline: Repay-Reinvest link for product
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Verify repay reinvest link is displayed
    Examples:
      | Scenario |
      | TC_03    |


  @Non_Matured_Cancel_Journey
  Scenario Outline: Cancel Journey
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then User click cancel button on review page
    Examples:
      | Scenario  |
      | Blank_OTP |


  @Build_Sanity_Add_IBAN
    @Non_Matured_Add_IBAN_IN_Journey
  Scenario Outline: Add iban while cash-in
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User click on add iban button
    And Verify the enter iban page slide
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Verify the thank you message displayed
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario             | MethodType |
      | ADD_IBAN_NON_MATURED | null       |
#      | TC_06 | null       |


  @Non_Matured_CashIn_Installment_Enter_Amount
  Scenario Outline: Non Matured: Childcare,SSA,Installment Enter amount journey wait for interest not enable
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User opens non matured holding
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_10    |

  @Non_Matured_CashIn_Installment_Enter_Amount
  Scenario Outline: Non Matured: Childcare,SSA,Installment Enter amount journey Wait for interest enable
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User opens non matured holding
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_10    |

  @Non_Matured_CashIn_Installment_Allocate_Modal
  Scenario Outline: Non - Matured: Childcare,SSA,Installment Allocate amount journey wait for interest not enable
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User opens non matured holding
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_13    |

  @Non_Matured_CashIn_Installment_Allocate_Modal
  Scenario Outline: Non - Matured: Childcare,SSA,Installment Allocate amount journey wait for interest enable
    Given User login using valid credential "<Scenario>"
    When User click on the manage button and select the parent Account
    And User opens non matured holding
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User click on the manage button and select the parent Account
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_10    |

  @Non_Matured_CashIn_FT_Allocate_Modal
  Scenario Outline: Non-Matured: Allocate Modal Wait for interest not enable
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |

  @Non_Matured_CashIn_FT_Allocate_Modal
  Scenario Outline: Non-Matured: Allocate Modal Wait for interest enable
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
    And User click on allocate full amount
    And User click on allocate modal confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC56     |

  @Non_Matured_CashIn_FT_Enter_Amount
  Scenario Outline: Non-Matured: Enter amount Wait for interest not enable
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
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
      | Scenario |
      | TC_12    |
      | TC_22    |

  @Non_Matured_CashIn_FT_Enter_Amount
  Scenario Outline: Non-Matured: Enter amount Wait for interest enable
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And User opens non matured holding
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    Then Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_20    |
      | TC_21    |



