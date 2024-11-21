@Expedited_Registration
Feature: Expedited Registration E2E flow

  @ErrorMessage_LetsGetStart
  Scenario Outline: Validate Error message on Lets Get Started
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And  user click on next button of lets get stared page
    And verify the error message displayed on Lets get started page for "<Indicator>"
    Examples:
      | Scenario       | Indicator |
      | SSCN_Incorrect | Incorrect |

  @ErrorMessage_LetsGetStart_AllBlank
  Scenario Outline: Validate Error message on Lets Get Started for All Blank
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    And  user click on next button of lets get stared page
    And verify the error message displayed on Lets get started page for "<Indicator>"
    Examples:
      | Indicator |
      | Blank     |

  @ErrorMessage_EnterEmail
  Scenario Outline: Validate Error message on Enter Email
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    And verify the error message displayed on Email page for "<Indicator>"
    Examples:
      | Scenario       | Indicator     |
      | Email_Invalid  | Invalid       |
      | Email_Mismatch | EmailMismatch |


  @ErrorMessage_EnterEmailBlank
  Scenario Outline: Validate Error message on Enter Email
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  Click on next button of email page
    And verify the error message displayed on Email page for "<Indicator>"
    Examples:
      | Scenario      | Indicator |
      | Email_Invalid | Blank     |

  @ErrorMessage_WrongPIN
  Scenario Outline: validate Error Message on Enter PIN
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And Verify the error message displayed on Pin Page for "<Indicator>"
    Examples:
      | Scenario | Indicator | pin    |
      | TC04     | Incorrect | 678543 |
      | TC04     | LessThan6 | 4532   |


  @PIN_Reissue
  Scenario Outline: PIN Reissue
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then Click on Didn't get PIN link
    And Click on Resend PIN button
    And click on GotoStateSaving button of Reissue PIN Thankyou page
    Examples:
      | Scenario |
      | TC04     |

  @PIN_ReissueRequestExceeded
  Scenario Outline: PIN Reissue Request Exceeded Modal
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then Click on Didn't get PIN link
    And Click on Resend PIN button
    And click on Close Button of Reissue PIN thankyou Page
    Then Click on Didn't get PIN link
    And Click on Resend PIN button
    And Click on Continue Registration button of Reissue Exceeded modal

    Examples:
      | Scenario                     |
      | PIN_Reissue_Request_Exceeded |

  @ErrorMessage_MobileNumber
  Scenario Outline: Validate Error Message on Enter Mobile Number
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And verify the error message displayed on Mobile number page for "<Indicator>"

    Examples:
      | Scenario                      | Indicator       | pin    |
      | Mobile_Number_Blank           | Blank           |        |
      | Mobile_Number_Incorrect       | Incorrect       | 481674 |
      | Mobile_Number_MisMatch        | Mobile_MisMatch | 481674 |
      | Mobile_Number_Prefix_MisMatch | Prefix_MisMatch | 481674 |

  @ErrorMessage_Security
  Scenario Outline: Validate Error Message on Security Code
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And  verify the error message displayed on Security code page for "<Indicator>"
    Examples:
      | Scenario      | Indicator | pin    |
      | OTP_Blank     | Blank     | 649409 |
      | OTP_Incorrect | Incorrect | 649409 |
      | OTP_LessThan6 | LessThan6 | 649409 |
      | OTP_Expired   | Expired   | 649409 |

  @ErrorMessage_Password
  Scenario Outline:Validate Error Message on Password
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And open Email and click on email verification link
    And Enter all the details for mobile number page
    Then Click on continue button of mobile page
    And  Enter the security code
    Then Click on verify button of security code
    And validate the create password page
    And Click on complete button of password page
    And verify the error message displayed on Password page for "<Indicator>"
    And Enter the password
    And click on Show password button
    And Click on complete button of password page
    And verify the error message displayed on Password page for "<Indicator>"

    Examples:
      | Scenario                         | Indicator       | pin    |
      | Password_Requirement_Not_Matched | Blank           | 901162 |
      | Password_Requirement_Not_Matched | Req_Not_Matched | 901162 |

  @RegisterUsingSecondEmailLink
  Scenario Outline: Expedited Registration using Second Email Verification Link
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<Pin>"
    Then Click on continue button of PIN page
    And verify check your inbox page
    And Click on Didn't receive email link
    And Click on Resend email button
    And open Email and click on email verification link
    And open Email and delete an email
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
      | Scenario | Pin    |
      | TC04     | 442937 |

  @Expedited_E2E123
  Scenario Outline: Expedited Registration E2E Happy flow
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And  Validate the Continue Registration modal
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Enter and Re enter email
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN
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
      | Scenario                         |
      | TC08                             |
      | TC_21                            |
      | TC_29                            |
      | TC_30                            |
      | TC_31_Exp_Reg_Saving_Bond        |
      | TC_32_Exp_Reg_Saving_Certificate |
      | TC_33                            |
      | TC_34_Exp_Reg_NSB10Yr            |

#  @K13Regression
    @exp
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
    Then enter your PIN
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
      | scenario | TestCase | pin    |
      | TC 78    | TC04     | 226796 |

  @AlreadyRegistered
  Scenario Outline: Already Registered
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And Verify Already Registered details page displayed

    Examples:
      | Scenario |
      | TC04     |