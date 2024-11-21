Feature: Web Registration E2E flow


#  @K13Regression
    @webRegistration
  Scenario Outline: Web Registration E2E Happy flow <Testcase Name>
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    Then Enter all address details for Web Register user
    And Click on next button of We Matched your details page
    And Verify details of Check Details and continue page
    And Click on consent of check Details and continue page
    And click on next button of check Details and continue page
    And click on send code of confirm your mobile phone page
    And Enter the security code for web Registration
    And Click on Confirm button of security code for web Registration
    And verify the check your inbox page
    And open Email and click on email verification link
    And verify Email address Verified page
    And Enter password for web Registration
    And Click on Confirm button of Email address Verified page
    And Verify what happens next page displayed
    Examples:
      | Scenario |
      | Web_73   |
      | Web_1    |
      | Web_2    |
      | Web_3    |
      | Web_4    |
      | Web_5    |
      | Web_6    |
      | Web_7    |
      | Web_8    |
      | Web_9    |
      | Web_10   |
      | Web_11   |
      | Web_12   |
      | Web_13   |
      | Web_14   |
      | Web_15   |
      | Web_16   |
      | Web_17   |
      | Web_18   |
      | Web_19   |
      | Web_20   |
      | Web_21   |
      | Web_22   |
      | Web_23   |
      | Web_24   |
      | Web_25   |
      | Web_26   |
      | Web_27   |
      | Web_28   |
      | Web_29   |
      | Web_30   |
      | Web_31   |
      | Web_32   |


  Scenario Outline: Open Email and Register User
    Given open Email and click on email verification link for "<Scenario>"
    And verify Email address Verified page
    And Enter password for web Registration
    And Click on Confirm button of Email address Verified page
    Then Verify what happens next page displayed
    Examples:
      | Scenario |
      | Web_1    |

  @PIN_Expired_Perform_WebRegistration
  Scenario Outline: PIN Expired perform web Registration E2E
    Given user launch the State Savings portal and click on Register
    When click on Begin Registration and verify let's get started page
    Then user enter the details as per "<Scenario>"
    And Validate the PPSN_SSCN tooltip
    And  user click on next button of lets get stared page
    And Click on Continue button of Pin No Longer Valid modal
    Then Enter all address details for Web Register user
    And Click on next button of Postal address page
    And Verify details of Check Details and continue page
    And Click on consent of check Details and continue page
    And click on next button of check Details and continue page
    And click on send code of confirm your mobile phone page
    And Enter the security code for web Registration
    And Click on Confirm button of security code for web Registration
    And verify the check your inbox page
    And open Email and click on email verification link
    And verify Email address Verified page
    And Enter password for web Registration
    And Click on Confirm button of Email address Verified page
    And Verify what happens next page displayed
    Examples:
      | Scenario     |
      | PIN_Expired2 |
