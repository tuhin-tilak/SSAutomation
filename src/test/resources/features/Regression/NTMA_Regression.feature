@K13Regression
Feature: NTMA Regression Suite

  @ForgotPassword
  Scenario Outline: Forgot Password <scenario>
    Given Launch the url
    Then Click sign in button on home page
    Then User click forgot password button enters email: "<email>" on slider click on next button
    Then User provides verification code clicks request reset link button for this mobile number: "<mobilenumber>"
    Then Considering the user opens the Mailinator portal and provides the email: "<email>"
    Then User clicks password change button present in email message body
    And  User enters new password: "<password>", clicks next to confirm input
    Examples:
      | scenario | email                               | mobilenumber | password   |
      | TC 28    | Perf-Test-User-10247@mailinator.com | 020-8010237  | Dummy!0026 |
#      | TC 28    | UAT-Target-Regression-9011@mailinator.com | 020-8009011  | Dummy!0014 |


  Scenario Outline: EmailID change request <Testcase Name>
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Email address Change section on the Profile and Settings tab for "<number>"
    And User is able to change the Email to "<Email2>"
    Then Considering the user opens the Mailinator portal and provides the email: "<Email2>"
    And User click on  Confirm change of email address  button which is displayed in mail message body
    And Verify the Email has been changed
    Examples:
      | Testcase Name | username                            | password   | number      | Email2                                  | name                  |
      | TC 31         | Perf-Test-User-10236@mailinator.com | Dummy!0000 | 020-8010226 | Perf-AutoTest-User-10236@mailinator.com | MR DOLORITA DRINKHILL |
#      | TC 31         | UAT-WF-AutoUser21@mailinator.com | Dummy!2022 | 020-8009212 | UAT-WF-User212@mailinator.com | DR SILVIE CONNAR |


  Scenario Outline: Password Change <Testcase Name>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    And User navigates to Password Change section on the Profile and Settings tab for "<number>"
    And User is able to change the "<password>" to "<password2>"
    And click on the Notification menu and navigates to the Notification Page
    And User is able to view the Notification DropDown
    Then User selects the "<action>" and able to view the respective notifications
    Then Validate notification "<message>"
    Examples:
      | Testcase Name | username                            | password   | number      | action | password2  | message                        |
      | TC 32         | Perf-Test-User-10250@mailinator.com | Dummy!0002 | 020-8010240 | Alerts | Dummy!0003 | Your Password has been updated |
#      | TC 32         | UAT-Target-Regression-9030@mailinator.com | Dummy!0009 | 020-8009030 | Alerts | Dummy!0010 | Your Password has been updated |


  Scenario Outline: Full name  change request <Testcase Name>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    And User navigates to Full name Change section on the Profile and Settings tab and click on request an update  button

    Examples:
      | Testcase Name | username                            | password   | number      |
      | TC 33         | Perf-Test-User-10248@mailinator.com | Dummy!0000 | 020-8010238 |
#      | TC 33         | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 |


  Scenario Outline: Mobile number change request <Testcase Name>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    And User navigates to Your mobile number Change section on the Profile and Settings tab and click on request an update button
    Examples:
      | Testcase Name | username                            | password   | number      |
      | TC 34         | Perf-Test-User-10248@mailinator.com | Dummy!0000 | 020-8010238 |
#      | TC 34         | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 |


  Scenario Outline: Your address change request <Testcase Name>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    And User navigates to Your Change your address section on the Profile and Settings tab and click on request an update button

    Examples:
      | Testcase Name | username                            | password   | number      |
      | TC 35         | Perf-Test-User-10248@mailinator.com | Dummy!0000 | 020-8010238 |
#      | TC 35         | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 |


  Scenario Outline: Check your SSCN details <Testcase Name>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    Then User verifies "<SSCN>" on profile and setting tab
    Examples:
      | Testcase Name | username                            | password   | number      | SSCN      |
      | TC 36         | Perf-Test-User-10248@mailinator.com | Dummy!0000 | 020-8010238 | 242598706 |
#      | TC 36         | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 | 242598307 |


#  Scenario Outline: E2E journey add iban <Testcase Name>
#    Given User login using valid credential "<TCNO>"
#    When User click on Journey
#    And Verify the content on the page
#    And Verify add iban modal displayed
#    And User enter the iban
#    And Tick the checkbox on the iban page
#    Then User click on the verify bank button
#    And verify the otp page
#    Then User enters the otp received
#    And User click on confirm button
#    And Verify the thank you prompt message
#    When User navigates to profile and setting
#    And Verify the profile and setting page content
#    And Verify iban is displayed in your bank detail
#    Examples:
#      | TCNO    | Testcase Name |
#      | AddIBAN | TC 39         |
#      | AddIBAN(P&S) | TC 37         |


  Scenario Outline: E2E journey for IBAN amendment <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When User click on Journey
    Then user click on the change button
    And Verify add iban modal displayed
    And User enter the iban
    And Tick the checkbox on the iban page
    Then User click on the verify bank button
    And Verify the otp page content
    Then User enters the otp received
    And User click on confirm button
    And Verify the thank you prompt message
    When User navigates to profile and setting
    And Verify the profile and setting page content
    And Verify iban is displayed in your bank detail
    And User click on notification tab
    And Verify the iban is updated
    Examples:
      | TCNO       | Testcase Name |
      | ChangeIBAN | TC 38         |


#  Scenario Outline: Cash In(non-matured) add IBAN <Testcase Name>
#    Given User login using valid credential "<Scenario>"
#    When user clicks View and Manage button to access summary
#    And User opens non matured holding
#    And User click on add iban button
#    And User enters the iban
#    And Click on checkbox IBAN not added
#    And Clicks on confirm button
#    And Enter the Otp received
#    And Click on confirm button on security page
#    Then User enters Amount for Cashin
#    And User clicks on the confirm button
#    And Validate the detail on review transaction page
#    And Click confirm button on review page to finalize the transaction
#    And User enter the valid otp on the security code page
#    And Confirm the transaction by clicking confirm button on verification code page
#    And Click on back button thank you page
#    When user clicks View and Manage button to access summary
#    And User click on Pending Transaction modal
#    Examples:
#      | Scenario | Testcase Name |
#      | TC_06    | TC 40         |
#
#  @rerun
#    @Matured_add_IBAN_cashin_journey
#  Scenario Outline: Cash In add IBAN <Testcase Name>
#    Given User login using valid credential "<Scenario>"
#    When user clicks View and Manage button to access summary
#    And User opens matured holding and selects method type
#    And User enters the iban
#    And Click on checkbox IBAN not added
#    And Clicks on confirm button
#    And Enter the Otp received
#    And Click on confirm button on security page
#    And User selects the Option
#    And User click on allocate full amount
#    And User click on allocate modal confirm button
#    And Validate the detail on review transaction page
#    And Click confirm button on review page to finalize the transaction
#    Then Verify content on the otp page
#    And User enter the valid otp on the security code page
#    And Confirm the transaction by clicking confirm button on verification code page
#    And Click on back button thank you page
#    When user clicks View and Manage button to access summary
#    And User click on Pending Transaction modal
#    Examples:
#      | Scenario             | Testcase Name |
#      | ADDIBANCASHINMATURED | TC 41         |
#
#
#  @Repay&Reinvest_add_IBAN
#  Scenario Outline: Reinvest and CashIn with add IBAN Journey <Testcase Name>
#    Given User login using valid credentials for "<Scenario>"
#    When Validate IBAN status is displayed
#    When User select State Savings Product and clicks on manage button
#    And User opens matured holding and selects method type
#    Then User Enters IBAN number and click on confirm bank details
#    Then User then enters otp and press confirm
#    And User selects the Option
#    And User choose product from dropdown and enter the amount
#    Then User enter rest amount for CashIn
#    And User clicks on confirm button
#    And Substantiate particulars on review transaction page
#    And Click confirm button on review page to finalize the transaction
#    Then Validate OTP page and user enters valid otp
#    Then User redirected to thank you page
#    Examples:
#      | Scenario             | Testcase Name |
#      | ADDIBANREPAYREINVEST | TC 42         |

    #Cashin Journeys
  @RepayNonMatured
  Scenario Outline: Cash In non matured <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When user clicks View and Manage button to access summary
    And User opens non matured holding
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | Scenario | Testcase Name |
      | TC_04    | TC 43         |
#      | TC 04_SSA | Repay_Non_Matured_SSA |

  @Cashin_Delay_Checkbox
  Scenario Outline: Cash In non matured with delay checkbox & allocate full amount <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When user clicks View and Manage button to access summary
    And User opens non matured holding
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click on the delay checkbox
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | Scenario           | Testcase Name |
      | TC_05AllocateModal | TC 44         |

  @MaturedCashin
  Scenario Outline: Cash In Matured <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When user clicks View and Manage button to access summary
    And User selects any matured holding and opt for "<MethodType>"
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType | Testcase Name |
      | TC_05    | Cash-In    | TC 45         |

  @negative
    @Cashinchooseamnt
  Scenario Outline: Cash In PB choose amount <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When user clicks View and Manage button to access summary
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
    And Thank you page is displayed
    Examples:
      | Scenario | MethodType | Testcase Name |
      | TC_03    | Cash-In    | TC 46         |


  @CashinPBrange
  Scenario Outline: PB cash-in prize bond range <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<Range>"
    And User click on the confirm button
    And Verify error message displayed on prize bond range window
    And Validate the details on review transaction page prize amount
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed

    Examples:
      | Scenario   | MethodType | Testcase Name | Range                 |
      | PB_Range_2 | Cash-In    | TC 47         | ACH408937 - ACH408944 |



    #Reinvest Journey

  @negative
    @ReinvestSingle_Product
  Scenario Outline: Reinvest FT into single product <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | TCNO          | MethodType | Testcase Name |
      | TC_31         | Reinvest   | TC 48         |
      | TC_31_No_IBAN | Reinvest   | TC 49         |

  @ReinvestMultiple_Product
  Scenario Outline: Reinvest FT into multiple product <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Click on back button thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | TCNO          | MethodType | Testcase Name |
      | TC_31Multiple | Reinvest   | TC 50         |


  @Allocate2cash_addIBAN
  Scenario Outline: Reinvest_Choose_Amount PB into single/multiple product_allocate_to_cash and add IBAN <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And User enters the iban
    And Click on checkbox IBAN not added
    And Clicks on confirm button
    And Enter the Otp received
    And Click on confirm button on security page
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | TCNO  | MethodType | Testcase Name |
      | TC_09 | Reinvest   | TC 51         |

  @Rerun
    @Reinvest_Allocate_full_amount
  Scenario Outline: Reinvest into any product Allocate full amount journey <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And User click on edit your order review page
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    And Click on back button thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | TCNO          | MethodType | Testcase Name |
      | TC_31Allocate | Reinvest   | TC 52         |


  @ReinvestPBbondrange
  Scenario Outline: PB Reinvest-in prize bond range <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    Then Select from bond range: "<Range>"
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed

    Examples:
      | Scenario   | MethodType | Testcase Name | Range                 |
      | PB_Range_1 | Reinvest   | TC 53         | ACH471217 - ACH471224 |


  @Reinvest_PB_choose_amount
  Scenario Outline: Reinvest From PB choose amount journey <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | TCNO  | MethodType | Testcase Name |
      | TC_01 | Reinvest   | TC 54         |

  @Reinvest_PB_choose_amount_edit_&_Allocatetefull
  Scenario Outline: Reinvest From PB choose amount edit your order allocate full amount <Testcase Name>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And Verify the choose amount page
    And User enter the amount on choose amount page
    And Click confirm button to finalize the chosen amount on choose amount page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Substantiate particulars on review transaction page
    And User click on edit your order review page
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User click on allocate full amount button
    And User click on allocate modal confirm button
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | TCNO                          | MethodType | Testcase Name |
      | ChooseAmountEditOrderReinvest | Reinvest   | TC 55         |


  @Repay_Reinvest_Single_product
  Scenario Outline: Reinvest and CashIn Fixed Term Product Single <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When User select State Savings Product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | Scenario | MethodType     | Testcase Name |
      | TC_02    | Reinvest-Repay | TC 56         |

  @Repayreinvest_Multiple_product
  Scenario Outline: Reinvest and CashIn Fixed Term Product Multiple <Testcase Name>
    Given User login using valid credential "<Scenario>"
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | Scenario      | Testcase Name |
      | TC_02Multiple | TC 57         |

  @Repayreinvest_Allocate2cash
  Scenario Outline: Reinvest and CashIn Allocate to cash <Testcase Name>
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    And User clicks on confirm button
    When Amount entered is less than maturity value user click on allocate to cash
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario            | Testcase Name |
      | TC_02Allocatetocash | TC 58         |


  @Repayreinvest_Allocate_fullamnt
  Scenario Outline: Reinvest and CashIn Allocate full amount <Testcase Name>
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on allocate full amount button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario                | Testcase Name |
      | TC_02AllocateFullamount | TC 59         |

#  Scenario Outline: Sign in sole purchaase  Fixed Term Product <scenario>
#    Given Launch the url
#    Then Click buy now for "<Testcase Name>"
#    When User select product for purchase
#    Then User can continue as guest or sign-in during the journey
#    Then Validate Your Order page
#    Then Validate product and amount on review page
#    Then User selects customer level critical data elements
#    Then Enter payment details click pay
#    Then Verify Thank You page
#    Examples:
#      | scenario                        | Testcase Name |
#      | Sign_in_Buy_now_Sole_User_CDE_N | TC39          |
#      | Sign_in_Buy_now_Sole_User_CDE_Y | TC25          |
#
#
##
#  @Joint_Notice_Check
#  Scenario Outline: Joint Content Validation for matured and non matured holdings
#    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
#  #  When Validate IBAN status is displayed
#    When User select "<StateSavingsProduct>" and clicks on manage button
#    Then User clicks on joint button
#    And User selects joint holding with mentioned "<name>"
#    Then User verify notice button with mentioned "<Holding Id>"
#    Then User clicks on button and verifies the modal content
#    When User click on more details and verifies button present is same as on holdings page
#    Examples:
#      | Username                               | Password   | Mobilenumber | name                             | StateSavingsProduct | Holding Id |
#      | UATAUTOCHANGED-WF-User2@mailinator.com | Dummy!2022 | 020-8030002  | MS EILEEN O'SULLIVAN (SECONDARY) | Saving Bond         | 3500006248 |
#      | UATAUTOCHANGED-WF-User2@mailinator.com | Dummy!2022 | 020-8030002  | HON TADES PASHAN (PRIMARY)       | Saving Bond         | 3500011161 |
##
#  @Notice_Check_Installment&Childcare
#  Scenario Outline: Content Validation for installment and Childcare
#    Given User login using valid credentials for "<Scenario>"
#    When User clicks on manage button and select monthly instalment savings
#    And User opens matured holding page selects method type
#    Examples:
#      | Scenario                       |
#      | Notice_UAT_Install_Matured     |
#      | Notice_UAT_Child_Donor         |
#      | Notice_UAT_Child_Matured       |
#      | Notice_UAT_Child_Non_Matured   |
#      | Notice_UAT_Install_Non_Matured |
#      | Notice_UAT_Install_Flushed     |
#
##Data not available for commented examples
#  @Notice_Check_FTProducts
#  Scenario Outline: Content Validation For FT <TestCaseName>
#    Given User login using valid credentials for "<Scenario>"
#    When User select State Savings Product and clicks on manage button
#    And User opens matured holding page selects method type
#    Examples:
#      | Scenario                                | TestCaseName |
#      | Notice_UAT_NSB_4_Matured                | TC 69        |
#      | Notice_UAT_NSB_4_Non_Matured            | TC 69        |
###      | Notice_UAT_NSB_4_Flushed                |
#      | Notice_UAT_NSB_10_Matured               | TC 69        |
#      | Notice_UAT_NSB_10_Non_Matured           | TC 69        |
###      | Notice_UAT_NSB_10_Flushed               |
##      | Notice_UAT_NSB_10_Non_Matured_Other     |
#      | Notice_Saving_Bond_Matured              | TC 69        |
#      | Notice_Saving_Bond_Non_Matured          | TC 69        |
#      | Notice_Savings_Certificate_Matured      | TC 69        |
#      | Notice_Savings_Ceritificate_Non_Matured | TC 69        |
#      | Notice_SSA                              | TC 69        |
##      | Notice_Prior_2014             |