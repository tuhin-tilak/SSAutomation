@SSW_Sanity_Suite_K13_PreProd
Feature: State Savings Sanity Suite

  Scenario Outline: State Savings Online landing page <scenario>
    Given Launch the application
    Examples:
      | scenario     |
      | TC1_Homepage |


  Scenario Outline: Ensure the Sign In page loads as expected <scenario>
    Given Launch the application
    Then Click sign in button on home page
    Then Validate the proper rendering and placement of the email and password input fields

    Examples:
      | scenario         |
      | TC2_Sign_In_Page |

  @ch
  Scenario Outline: Validate user login and dashboard view <scenario>
    Given Launch the application
    Then User login using these credentials: username "<email>" password "<pwd>" mobile number "<mobile>"
    Examples:
      | scenario          | email                               | pwd        | mobile      |
#      | TC3_Login_Journey | PreProd-ECRUser2@mailinator.com | Dummy!2022 | 020-8070001 |
      | TC3_Login_Journey | Perf-Test-User-10241@mailinator.com | Dummy!2022 | 020-8010235 |


  Scenario Outline: Forgot Password <scenario>
    Given Launch the url
    Then User click forgot password button enters email: "<email>" on slider click on next button
    Then User provides verification code clicks request reset link button for this mobile number: "<mobilenumber>"
    Then Considering the user opens the Mailinator portal and provides the email: "<email>"
    Then User clicks password change button present in email message body
    And  User enters new password: "<password>", clicks next to confirm input
    Examples:
      | scenario            | email                               | mobilenumber | password   |
      | TC4_Forgot_Password | Perf-Test-User-10299@mailinator.com | 020-8010289  | Dummy!0012 |


  Scenario Outline: Expedited Registration <scenario>
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<TestCase>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
#    Then enter your PIN
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And validate the create password page
    And Enter the password
    And click on Show password button
    And Click on complete button of password page
    And Validate the congratulations message
    And First time login using ECR User
    Examples:
      | scenario                   | TestCase | pin    |
      | TC5_Expedited_Registration | TC04     | 302034 |


  Scenario Outline: Add IBAN from dashboard <scenario>
    Given User login using valid credential "<TCNO>"
    When User click on Journey
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
    Examples:
      | scenario                    | TCNO    |
      | TC6_Add_IBAN_from_dashboard | AddIBAN |


  Scenario Outline: Change Iban <scenario>
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
    Examples:
      | scenario                                  | TCNO  |
      | TC5_Change_IBAN_from_Profile_and_Settings | TC_18 |

  Scenario Outline: Change Iban <scenario>
    Given User login using valid credential "<TCNO>"
    When User navigates to profile and setting
    And Verify the profile and setting page content
    And Verify iban is displayed in your bank detail

    Examples:
      | scenario                                       | TCNO  |
      | TC6_Changed_IBAN_updated_in_Profile_&_Settings | TC_18 |


  Scenario Outline: Reinvest into multiple product <scenario>
    Given User login using valid credential "<TCNO>"
    When user clicks View and Manage button to access summary
    And User selects any matured holding and opt for "<MethodType>"
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then validate Thank You Page is displayed
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | scenario                            | TCNO  | MethodType |
      | TC7_Reinvest_into_Multiple_Products | TC_31 | Reinvest   |

  @check
  Scenario Outline: Reinvest and CashIn FT Product <scenario>
    Given User login using valid credential "<TestCase>"
    When User select State Savings Product and clicks on manage button
    And User selects any matured holding and opt for "<MethodType>"
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    Then Validate OTP page and user enters valid otp
    Then validate Thank You Page is displayed
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | scenario           | TestCase | MethodType     |
      | TC8_Repay/Reinvest | TC_02    | Reinvest-Repay |


  Scenario Outline: Cash In non matured <scenario>
    Given User login using valid credential "<TestCase>"
    When user clicks View and Manage button to access summary
    And User opens non matured holding
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then validate Thank You Page is displayed
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | scenario                  | TestCase |
      | TC9_Repayment_Non-Matured | TC_04    |


  Scenario Outline: Cash In Matured <scenario>
    Given User login using valid credential "<TestCase>"
    When user clicks View and Manage button to access summary
    And User selects any matured holding and opt for "<MethodType>"
    Then User enters Amount for Cashin
    And User clicks on the confirm button
    And Validate the detail on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    Then validate Thank You Page is displayed
    When user clicks View and Manage button to access summary
    And User click on Pending Transaction modal
    Examples:
      | scenario               | TestCase | MethodType |
      | TC10_Repayment_Matured | TC_05    | Cash-In    |


  Scenario Outline: Cash In Prize Bond Choose Amount <scenario>
    Given User login using valid credential "<TestCase>"
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
    Then validate Thank You Page is displayed
    Examples:
      | scenario                     | TestCase | MethodType |
      | TC11_Repayment_PB_ChooseAmnt | TC_03    | Cash-In    |


  Scenario Outline: Cash In Prize Bond range <scenario>
    Given User login using valid credential "<TestCase>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User select from given list
    And User click on the confirm button
    And Verify error message displayed on prize bond range window
    And Validate the details on review transaction page prize amount
    And Click confirm button on review page to finalize the transaction
    Then Verify content on the otp page
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed
    Examples:
      | scenario                    | TestCase   | MethodType |
      | TC12_Repayment_PB_BondRange | PB_Range_2 | Cash-In    |


  Scenario Outline: Reinvest Prize Bond range <scenario>
    Given User login using valid credential "<TestCase>"
    When user clicks View and Manage button to access summary
    And Click on the reinvest and cashin link
    And User selects any matured holding and opt for "<MethodType>"
    And User click on choose bond range window
    And Verify the choose bond range window
    And User select from given list
    And User click on the confirm button
    And user selects a product from the dropdown list and proceeds to enter the desired amount
    And User clicks on confirm button to finalize product and amount for reinvestment
    And Validate the details on the review page
    And Click confirm button on review page to finalize the transaction
    And User enter the valid otp on the security code page
    And Confirm the transaction by clicking confirm button on verification code page
    And Thank you page is displayed

    Examples:
      | scenario                   | TestCase   | MethodType |
      | TC13_Reinvest_PB_BondRange | PB_Range_1 | Reinvest   |


  Scenario Outline: Reinvest From PB Choose Amount <scenario>
    Given User login using valid credential "<TestCase>"
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
      | scenario                 | TestCase | MethodType |
      | TC14_Reinvest_ChooseAmnt | TC_01    | Reinvest   |


  Scenario Outline: Guest sole purchase Fixed Term Product <scenario>
    Given Launch the url
    Given Click buy now for "<TestCaseName>"
    When User selects product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enter personal details on your details page for first applicant
    Then Validate Your Order page
    And Click on continue to review button
    Then Validate product and amount on review page
    And User selects fund source and account purpose
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | scenario                           | TestCaseName |
      | TC15_Guest_Buy_now_Sole_User_CDE_Y | TC3          |

  @by
  Scenario Outline: Sign in sole purchase Fixed Term Product <scenario>
    Given Launch the url
    Then Click buy now for "<TestCaseName>"
    When User selects product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    And Click on continue to review button
    Then Validate product and amount on review page
    And User selects fund source and account purpose
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | scenario                             | TestCaseName |
      | TC16_Sign_in_Buy_now_Sole_User_CDE_N | TC39         |
      | TC17_Sign_in_Buy_now_Sole_User_CDE_Y | TC25         |


  Scenario Outline: Sign in joint purchase <scenario>
    Given Launch the url
    Then Click buy now for "<TestCaseName>"
    When User selects product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enter personal details for second applicant
    Then Validate Your Order page
    And Click on continue to review button
    Then Validate product and amount on review page
    And User selects fund source and account purpose
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | scenario                              | TestCaseName |
      | TC18_Sign_in_Buy_now_Sole_User_CDE_NN | TC62         |


  Scenario Outline:Ensure workflow application launched sucessfully <scenario>
    Given Launch Workflow
    Examples:
      | scenario                   |
      | TC19_Workflow_Health_Check |


  Scenario Outline: Validate workflow Case search page displayed <scenario>
    Given Launch Workflow
    And Click administration forms select process: "<process>" and action: "<action>"
    Then Validate purchase page
    Examples:
      | scenario               | process | action      |
      | TC20_Workflow_Purchase | Search  | Case Search |


  Scenario Outline: Validate workflow case details page displayed <scenario>
    Given Launch Workflow
    And Click administration forms select process: "<process>" and action: "<action>"
    Then Validate case details page is displayed for this holding id: "<Holding>"

    Examples:
      | scenario                       | process          | action | Holding    |
      | TC21_Workflow_Repayment_Search | Repayments Forms | Search | 4016855977 |


  Scenario Outline: Ensure admin portal is launched sucessfully <scenario>
    Given Launch admin portal
    Examples:
      | scenario                       |
      | TC22_Admin_Portal_Health_Check |




