Feature: Reinvest From PB


  @Check_Repay_Reinvest_Link_Displayed
  Scenario Outline: Reinvest_Enter_Amount PB into single/multiple product
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Check if the Repay-Reinvest link displayed
    Examples:
      | TCNO |
      | Tc28 |

    @PB_IBAN_NOT_ADDED_MODAL_REMAINING_AMOUNT
  Scenario Outline: Remaining amount modal when iban not added
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User clicks on allocate to cash modal
    And Verify the enter iban page for PB
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the thank you message displayed
    And Click on back button thank you page
    Examples:
      | TCNO  | MethodType |
      | TC_17 | Reinvest   |

    @PB_Iban_Not_Added_Modal_Confirm_Button
  Scenario Outline: PB Reinvest_Enter_Amount Iban not added confirm button modal journey
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Verify the enter iban page for PB
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the thank you message displayed
    And Click on back button thank you page
    Examples:
      | TCNO    | MethodType |
      | TC_Test | Reinvest   |

    @PB_IBAN_Not_Added_Allocate_Button_Modal
  Scenario Outline: PB Reinvest_Enter_Amount iban not added Allocate button modal journey
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User click on allocate full amount button
    And User click on PB allocate modal Add IBAN button
#    And User click on PB allocate modal goBack button
    And Verify the enter iban page for PB
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the thank you message displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | TCNO | MethodType | Index |

    @PB_Range_Iban_Not_Added_Allocate_Button_Modal
  Scenario Outline: PB Reinvest_Bond_Range iban not added allocate button modal
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User select from given list
#    And User selects all prize bonds
    And User click on the confirm button
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
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the thank you message displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | TCNO | MethodType | Index |

    @PB_Range_Iban_Not_Added_Confirm_Button_Modal
  Scenario Outline: PB Reinvest_Bond_Range iban not added confirm button modal journey
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User select from given list
#    And User selects all prize bonds
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Verify the enter iban page for PB
    And User enters the iban
    And Click on checkbox
    And Clicks on confirm button
    And Verify the security page
    And Enter the Otp received
    And Click on confirm button on security page
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And Verify the thank you message displayed
    And Click on back button thank you page
    Examples:
      | TCNO | MethodType | Index |

  @Reinvest_From_PB_Enter_Amount_Cancel_Journey
  Scenario Outline: Reinvest PB Cancel Journey
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the detail on review transaction page
    Then User click cancel button on review page
    Examples:
      | TCNO  | MethodType |
      | TC_12 | Reinvest   |

  @Reinvest_From_PB_Enter_Amount
  Scenario Outline: Reinvest_Enter_Amount PB into single/multiple product
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    And Check if the Repay-Reinvest link displayed
    Examples:
      | TCNO  | MethodType |
      | TC_12 | Reinvest   |
      | TC_14 | Reinvest   |

  @Reinvest_From_PB_Enter_Amount_Remaining_Amount
  Scenario Outline: Reinvest_Enter_Amount PB into single/multiple product_Remaining_Amount
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User clicks on allocate to cash modal
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    Examples:
      | TCNO  | MethodType |
      | TC_09 | Reinvest   |
      | TC_16 | Reinvest   |

  @Reinvest_From_PB_Allocate_Modal_Reinvest_Page
  Scenario Outline: Reinvest_Allocate_Full_Amount PB into Multiple/Single product
    Given User login using valid credential "<TCNO>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | TCNO  | MethodType |
      | TC_11 | Reinvest   |

  @Reinvest_PB_Enter_PB_Amount
  Scenario Outline: Allocate Full Amount on Enter Amount Page
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Click on allocate full amount of Amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType | Index |

  @Reinvest_From_PB_Range
  Scenario Outline: PB Reinvest-in prize bond range
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User select from given list
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    Examples:
      | Scenario | MethodType |

  @Reinvest_PB_Bond_Range_Remaining_Amount
  Scenario Outline: PB Reinvest prize bond range Remaining amount modal
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User select from given list
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User clicks on allocate to cash modal
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    Examples:
      | Scenario | MethodType |
      | TC_13    | Reinvest   |
      | TC_15    | Reinvest   |

  @Reinvest_PB_Range_All_Select
  Scenario Outline: PB Reinvest Bond range select all PB Range
    Given User login using valid credential "<Scenario>"
    When User selects the product and clicks on manage button
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User selects all prize bonds
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When User selects the product and clicks on manage button
    Examples:
      | Scenario | MethodType |
    | TC32         | Reinvest           |

