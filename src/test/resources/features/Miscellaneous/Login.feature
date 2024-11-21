Feature: Login

  @K13Regression
    @ValidLogin
  Scenario Outline: Sign In with valid credentials <scenario>
    Given Launch the url
    Then User login using these credentials: username "<email>" password "<pwd>" mobile number "<mobile>"
    Examples:
      | email                               | pwd        | mobile      | scenario |
      | Perf-Test-User-10248@mailinator.com | Dummy!0000| 020-8010238 |TC 26|
#      | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 | TC 26    |

  @K13Regression
    @InvalidLogin
  Scenario Outline: Sign In with invalid credentials <scenario>
    Given Launch the url
    Then User login using invalid credential
      | Username                            | Password   | Error Message                                              |
      | anpost@gmail.com                    | anpost@123 | Incorrect email address and/or password. Please try again. |
      | jfallis7a@theatlantic.com           | anpost@123 | Incorrect email address and/or password. Please try again. |
      | PreProd-TestUser3@mailinator.com | Dummy!2022 | A registration is in progress for these details.           |
#      | Perf-Test-User-10230@mailinator.com | Dummy!2022 | A registration is in progress for these details.           |
    Examples:
      | scenario |
      | TC 27    |

  @LoginErrorValidation
  Scenario Outline: Sign In with valid credentials <scenario>
    Given Launch the url
    Then Click sign in button on home page
    Then User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then Validate error message on sign in page for "<feilds>"
    When Verification code entered is "<OTP Feild>"

    Examples:
      | username                            | password      | feilds             | OTP Feild            |
      |                                     |               | Blank              |                      |
      | Perf-Test-User-10224@mailinator.co  | Dumm00        | Invalid            |                      |
      | Perf-Test-User-10224@mailinator.co  | Dummy!0000    | Invalid Email      |                      |
      | Perf-Test-User-10224@mailinator.com | Dummy!0001    | Incorrect Password |                      |
#      | Perf-Test-User-10230@mailinator.com | Dummy!2022 | Pending            |                      |
      | testworkflow29@mailinator.com       | Dummy!2022    | Pending            |                      |
      | PreProd-TestUser8@mailinator.com    | AutoTest!2022 |                    | Blank                |
      | PreProd-TestUser8@mailinator.com    | AutoTest!2022 |                    | Expired              |
      | PreProd-TestUser8@mailinator.com    | AutoTest!2022 |                    | Less than six digits |
#      | Perf-Test-User-10224@mailinator.com | Dummy!0000    |                    | Blank                |
#      | Perf-Test-User-10224@mailinator.com | Dummy!0000    |                    | Expired              |
#      | Perf-Test-User-10224@mailinator.com | Dummy!0000    |                    | Less than six digits |

      