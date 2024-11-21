@SIT
Feature: Repay/Reinvest into State Savings Product

  @SITE2E
    @RepayreinvestSingle
  Scenario Outline: Reinvest and CashIn Fixed Term Product
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_02    |
      | TC_58    |
      | TC_59    |
      | TC_60    |
      | TC_61    |
      | TC_62    |
      | TC_63    |
      | TC_72    |
      | TC_73    |
      | TC_77    |
      | TC_78    |

  @SITE2E
    @RepayreinvestMultiple
  Scenario Outline: Reinvest and CashIn Fixed Term Product
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click on the review page confirm button
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario      |
      | TC_02Multiple |
#      | TC_64    |

  @SITE2E
    @Installment&Childcare
  Scenario Outline: Reinvest and CashIn InstallmentSave and Childcare product
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User clicks on manage button and select monthly instalment savings
    And User opens matured holding ID for Instalment Save and select method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click on the review page confirm button
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    Examples:
      | Scenario |
      | TC_65    |
      | TC_66    |
      | TC_67    |
      | TC_68    |
      | TC_75    |
      | TC_76    |


  @RepayReinvestAddIBAN
  Scenario Outline: Reinvest and CashIn with add IBAN Journey
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    Then User Enters IBAN number and click on confirm bank details
    Then User then enters otp and press confirm
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click confirm button on review page to finalize the transaction
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    Examples:
      | Scenario |
      | TC_69    |


  @Allocatetocash
  Scenario Outline: Reinvest and CashIn positive
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    And User clicks on confirm button
    When Amount entered is less than maturity value user click on allocate to cash
    And Substantiate particulars on review transaction page
    And Click on the review page confirm button
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal
    Examples:
      | Scenario |
      | TC_02    |

  @CancelTransaction
  Scenario Outline: Reinvest and CashIn positive
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    Then User click cancel button on review page

    Examples:
      | Scenario |
#      | TC_81    |
      | TC_02    |


  @Allocatefullamount
  Scenario Outline: Reinvest and CashIn positive
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
#    Then User enter rest amount for CashIn
    And User clicks on allocate full amount button
    And Substantiate particulars on review transaction page
    And Click on the review page confirm button
    Then Validate OTP page and user enters valid otp
    Then User redirected to thank you page
    When User select State Savings Product and clicks on manage button
    And User click on Pending Transaction modal

    Examples:
      | Scenario      |
#      | TC_70    |
      | TC_02Multiple |


  @HoldingsContentCheck
  Scenario Outline: Reinvest and Cashin Content Validation
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding page selects method type
    Examples:
      | Scenario |
      | TC_82    |

  @HoldingsContentCheckInstalment&Childcare
  Scenario Outline: Reinvest and Cashin Content Validation
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User clicks on manage button and select monthly instalment savings
    And User opens matured holding page selects method type
    Examples:
      | Scenario |
      | TC_83    |


  @JointContentcheck
  Scenario Outline: Joint Content Validation for matured and non matured holdings
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When Validate IBAN status is displayed
    When User select "<StateSavingsProduct>" and clicks on manage button
    Then User clicks on joint button
    And User selects joint holding with mentioned "<name>"
    Then User verify notice button with mentioned "<Holding Id>"
    Then User clicks on button and verifies the modal content
    When User click on more details and verifies button present is same as on holdings page
    Examples:
      | Username                           | Password       | Mobilenumber | name              | StateSavingsProduct | Holding Id |
      | Perf-Test-User-9951@mailinator.com | MyAccount_2021 | 020-8009951  | REV KEEN PIETASCH | Savings Certificate | 4019275211 |


  @ErrorValidationOTP
  Scenario Outline: Reinvest and CashIn Fixed Term Product
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding and selects method type
    And User choose product from dropdown and enter the amount
    Then User enter rest amount for CashIn
    And User clicks on confirm button
    And Substantiate particulars on review transaction page
    And Click on the review page confirm button
    Then Validate error message on OTP page with multiple combination
    Examples:
      | Scenario |
      | TC_85    |