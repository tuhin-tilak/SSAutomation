Feature: Profile & Settings


  @demo51
  Scenario Outline: Password Change "<TCName>"
    Given User is on the homepage of State Savings portal
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Password Change section on the Profile and Settings tab for "<number>"
    And User is able to change the "<password>" to "<password2>"



    Examples:
      | username                         | password   | TCName          | number      | name                | password2  |
      | UATAUTO-WF-User39@mailinator.com | Dummy!0000 | Change Password | 020-8030039 | MS WOODMAN HARTZOGS | Dummy!2023 |

  @demo5
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
      | username                          | password   | TCName          | number      | name                | password2  | Email2                           |
      | UATAUTO-WF-User39c@mailinator.com | Dummy!2023 | Change Password | 020-8030039 | MS WOODMAN HARTZOGS | Dummy!2023 | UATAUTO-WF-User39@mailinator.com |


  @demo5
  Scenario Outline: Full name  change request "<TCName>"
    Given User is on the homepage of State Savings portal
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Full name Change section on the Profile and Settings tab and click on request an update  button


    Examples:
      | username                         | password   | TCName          | number      | name                | password2  | Email2                            |
      | UATAUTO-WF-User39@mailinator.com | Dummy!2023 | Change Password | 020-8030039 | MS WOODMAN HARTZOGS | Dummy!2023 | UATAUTO-WF-User39c@mailinator.com |

  @demo5
  Scenario Outline: Mobile number change request "<TCName>"
    Given User is on the homepage of State Savings portal
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Your mobile number Change section on the Profile and Settings tab and click on request an update button


    Examples:
      | username                         | password   | TCName          | number      | name                | password2  | Email2                            |
      | UATAUTO-WF-User39@mailinator.com | Dummy!2023 | Change Password | 020-8030039 | MS WOODMAN HARTZOGS | Dummy!2023 | UATAUTO-WF-User39c@mailinator.com |

  @demo5
  Scenario Outline: Your address change request "<TCName>"
    Given User is on the homepage of State Savings portal
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Your Change your address section on the Profile and Settings tab and click on request an update button


    Examples:
      | username                         | password   | TCName          | number      | name                | password2  | Email2                            |
      | UATAUTO-WF-User39@mailinator.com | Dummy!2023 | Change Password | 020-8030039 | MS WOODMAN HARTZOGS | Dummy!2023 | UATAUTO-WF-User39c@mailinator.com |


  @demo6
  Scenario Outline: Check your SSCN details "<TCName>"
    Given User is on the homepage of State Savings portal
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Your account detail section on the Profile and Settings tab and click on show SSCN button


    Examples:
      | username                         | password   | TCName          | number      | name                | password2  | Email2                            |
      | UATAUTO-WF-User39@mailinator.com | Dummy!2023 | Change Password | 020-8030039 | MS WOODMAN HARTZOGS | Dummy!2023 | UATAUTO-WF-User39c@mailinator.com |


  @demo8
  Scenario Outline: Check your SSCN details "<TCName>"
    Given User is on the Dashboard page with Username - "<username>", Password - "<password>" and Mobile Number - "<number>"

    Examples:
      | username               | password   | TCName            | number      | name            | password2  | Email2                 |
      | hubpressland@yahoo.com | Dummy!0001 | Change Password 2 | 020-1234584 | CASSEY TRUGGIAN | Dummy!0000 | hubpressland@yahoo.com |


  @K13Regression
    @ProfileandSettingsAddHoldings
  Scenario Outline: Add Holdings from dashboard <scenario>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    And User clicks on add holding url and add different holding "<userProduct>"
    And User clicks on add another product "<userAnotherProduct>"
    And User removes another product "<userAnotherProduct>"
    Then User clicks on download button and SuccessFul message is displayed

    Examples:
      | username                            | password   | number      | userProduct | userAnotherProduct   | scenario |
 | Perf-Test-User-10248@mailinator.com | Dummy!0000| 020-8010238 | Prize Bonds | Savings Bond 3 years |TC 71     |
#      | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 | Prize Bonds | Savings Bond 3 years | TC 71    |

  @K13Regression
    @summaryAddHoldings
  Scenario Outline: Add Holdings from summary <scenario>
    Given User launches the State Savings portal URL and login to portal sucessfully with  "<username>" ,"<password>" and "<number>"
    When User selects the product and clicks on manage button
    And User clicks on add holding url and add different "<userProduct>"
    And User clicks on add another product "<userAnotherProduct>"
    Then User clicks on download button and SuccessFul message is displayed

    Examples:
      | username                            | password   | number      | userProduct | userAnotherProduct   | scenario |
      | Perf-Test-User-10248@mailinator.com | Dummy!0000| 020-8010238 | Prize Bonds | Savings Bond 3 years |TC 71     |
#      | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218 | Prize Bonds | Savings Bond 3 years | TC 71    |

  @negative

  Scenario Outline: Checking Error message while giving same email for email change
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Email address Change section on the Profile and Settings tab One
    And User Validates the Email Address "<username>"

    Examples:
      | username                     | password   | TCName             | number      | name                |
#      | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022 | Email Change Error | 020-8030045 | REV ANJELA NALDRETT |

#  Request to change your email address for Ireland State Savings Online

  @negative

  Scenario Outline: Change email by entering invalid email
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Email address Change section on the Profile and Settings tab One
    And User Validates the Invalid Email Address "<Email4>"
    Examples:

      | username                     | password   | TCName        | number      | name                | Email4 |
#      | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022 | Invalid Email | 020-8030045 | REV ANJELA NALDRETT | 123456 |

  @negative
  Scenario Outline: Change email by using already registered email
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Email address Change section on the Profile and Settings tab One
    And User Validates the Registered Email Address "<Email4>"
    Examples:

      | username                     | password   | TCName         | number      | name                | Email4                       |
#     | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022 | Existing Email | 020-8030045 | REV ANJELA NALDRETT | UAT-WF-User23@mailinator.com |

  @negative

  Scenario Outline: Change email by passing email feild as empty
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Email address Change section on the Profile and Settings tab One
    And User Validates the null Email Address "<Email4>"
    Examples:

      | username                     | password   | TCName      | number      | name                | Email4 |
#     | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022 | Blank Email | 020-8030045 | REV ANJELA NALDRETT |        |

  @negative

  Scenario Outline: user checks the error message while giving Same password in both fields
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Password Change section on the Profile and Settings tab for "<number>"
    And User is validation on same password "<password>""<password1>"
    Examples:

      | username                         | password      | TCName        | number      | name            | password1     |
#      | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
#      | UAT-WF-User45@mailinator.com | Dummy!2022 | Same Password | 020-8030045 | REV ANJELA NALDRETT |Dummy!2022|
      | PreProd-TestUser8@mailinator.com | AutoTest!2022 | Same Password | 020-8099908 | CORETTE SIMANEK | AutoTest!2022 |


  Scenario Outline: Validates if user does not achieve the password requirement error message is displayed
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Password Change section on the Profile and Settings tab for "<number>"
    And User is validation on password Requirement one "<password>""<password1>"
    Examples:

      | username                     | password   | TCName        | number      | name                | password1 |
#      | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022 | Invalid Email | 020-8030045 | REV ANJELA NALDRETT | Msd0707   |
#      | PreProd-TestUser8@mailinator.com | AutoTest!2022 | Same Password | 020-8099908 | CORETTE SIMANEK | Msd0707 |
  @negative

  Scenario Outline: Validates if user does not achieve the password requirement error message is displayed
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Password Change section on the Profile and Settings tab for "<number>"
    And Validate error message when the user enters an invalid "<current password>" and valid "<new password>"
    Examples:

      | username                     | current password | TCName        | number      | name                | new password |
#      | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022       | Invalid Email | 020-8030045 | REV ANJELA NALDRETT | Dummy!2022   |
#      | PreProd-TestUser8@mailinator.com | AutoTest!2022 | Same Password | 020-8099908 | CORETTE SIMANEK | Msd0707 |

  @negative

  Scenario Outline: user checks the user does not achieve the password requirement error message
    Given Launch the url
    Then Click sign in button on home page
    When User enters the Username - "<username>" and Password - "<password>"
    And User clicks signin button
    Then User "<name>" is able to login to the State Savings portal with Phone number "<number>"
    And User navigates to Password Change section on the Profile and Settings tab for "<number>"
    And User is validation on current password null "<password>""<password1>"
    Examples:

      | username                     | password   | TCName        | number      | name                | password1  |
#      | Perf-Test-User-10189@mailinator.com | Dummy!2022 | Basic Login   |020-1234569|CASSEY TRUGGIAN|
      | UAT-WF-User45@mailinator.com | Dummy!2022 | Invalid Email | 020-8030045 | REV ANJELA NALDRETT | Dummy!2022 |

  @negative
    @forgotErrorMessageone
  Scenario Outline: Forgot Password user enters non-registered email address
    Given Launch the url
    Then Click sign in button on home page
    Then User click forgot password button enters email: "<email>" on slider click on next button
    And Validate the error message on non registered email

    Examples:
      | email                          | mobilenumber | password   |
      | Kasi.Karthikeyan@cognizant.com | 020-8030013  | Dummy!2030 |


  @negative
    @forgotErrorMessagetwo
  Scenario Outline: Forgot Password user not gives email address
    Given Launch the url
    Then Click sign in button on home page
    Then User click forgot password button enters email: "<email>" on slider click on next button
    And Validate the error message on null email

    Examples:
      | email | mobilenumber | password   |
      |       | 020-8030013  | Dummy!2030 |

  @negative
    @forgotErrorMessagethree
  Scenario Outline: Forgot Password user gives Invalid email address
    Given Launch the url
    Then Click sign in button on home page
    Then User click forgot password button enters email: "<email>" on slider click on next button
    And Validate the error message on invalid email

    Examples:
      | email         | mobilenumber | password   |
      | UAT-WF-User45 | 020-8030013  | Dummy!2030 |