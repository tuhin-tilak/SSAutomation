@Title_validation
Feature: Title Validation

  @TitleValidation
  Scenario Outline: TitleValidation
    Given User login with valid credential "TC04"
    When user clicks on View and Manage button to access summary
    Then Validate Title "Standard Account Summary | Ireland State Savings"
    And User click on Repay reinvest link on summary page
    Then Validate Title "Choose Option | Ireland State Savings"
    And User choose "<option>"
    And user selects product from the dropdown list and proceeds to enter the desired amount
    Then Validate Title "Detail | Ireland State Savings"
    And User clicks confirm button to finalize product and amount for reinvestment
    Then Validate Title "Review | Ireland State Savings"
    And Validate the details on the review page
    And Click confirm button on review page and finalize the transaction
    And User enter valid otp on the security code page
    Then Validate Title "Security | Ireland State Savings"
    And Confirm transaction by clicking confirm button on verification code page
    Then Validate Title "Thank You | Ireland State Savings"
    Then validate Thank You Page is displayed
    Then Validate Title "Your Savings | Ireland State Savings"
    And User click on notification tab
    Then Validate Title "Notifications | Ireland State Savings"
    And Click on Profile and Settings
    Then Validate Title "Profile and Settings | Ireland State Savings"
    And User navigates to Change email: number "<Number>" email "<Email>"
    And open Email and click on link "<Email>"
    Then Validate Title "Email address verified | Ireland State Savings"
    And Click on Backtostatesavings
    And Click sign in button on home page
    Given User enters invalid credentials "<Scenario>"
    Then Validate Title "Service temporarily locked | Ireland State Savings"
    And Click on Backtostatesavings
    And Click sign in button on home page
    Given User enters valid credentials "<Scenario>"
    Then Validate Title "Enter Verification Code | Ireland State Savings"
    And open link and validate Title "https://statesavings-qa.dev-anpost.com/news-comm/repay-and-reinvest"
    Then Validate Title "Reinvest & Cash In | Comms | Ireland State Savings"
    And open link and validate Title "https://statesavings-qa.dev-anpost.com/news-comm/your-prize-bond-winnings-payment-options"
    Then Validate Title "Prize Bond Payment Options | Comms | Ireland State Savings"
    And open link and validate Title "https://statesavings-qa.dev-anpost.com/news-comm/state-savings-online"
    Then Validate Title "Registering for State Savings Online | Comms | Ireland State Savings"
    And open link and validate Title "https://statesavings-qa.dev-anpost.com/news-comm/celebrating-100-years"
    Then Validate Title "100 Years of Savings Certificates | Comms | Ireland State Savings"
    And open link and validate Title "https://statesavings-qa.dev-anpost.com/sscn"
    Then Validate Title "State Savings Customer Number | SSCN | Ireland State Savings"
    Then Launch the url
    Given Click buy now for "TC3"
    Then User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Title "Personal Details | Checkout | Ireland State Savings"
    Then User enter personal details on your details page for first applicant
    Then Validate Your Order page
    Then Validate Title "Your Order | Checkout | Ireland State Savings"
    Then Validate product and amount on review page
    Then Validate Title "Review | Checkout | Ireland State Savings"
    Then Enter payment details click pay
    Then Verify Thank You page
    Then Validate Title "Result | Checkout | Ireland State Savings"
    When click on Begin Registration and verify let's get started page
    Then user enter the details for Title "<Scenario>"
    Then Validate Title "Enter your Personal Details | Ireland State Savings"
    And  user click on next button of lets get stared page
    Then Validate Title "Contact details | Ireland State Savings"
    And Click on next button of We Matched your details page
    And Click on consent of check Details and continue page
    Then Validate Title "Review your contact details | Ireland State Savings"
    And click on next button of check Details and continue page
    Then Validate Title "Review your mobile number | Ireland State Savings"
    Then Validate Title "Enter the Verification Code | Ireland State Savings"
    And Click on Confirm button of security code for web Registration
    Then Validate Title "Check your inbox | Ireland State Savings"
    And verify the check your inbox page
    And open Email for web registration
    And verify Email address Verified page
    And Enter password web Registration Title
    Then Validate Title "Create your password | Ireland State Savings"
    And Click on Confirm button of Email address Verified page
    And Verify what happens next page displayed
    Then Validate Title "Next steps | Ireland State Savings"
 # validate check user
    And Click on Backtostatesavings from Thank you
    When click on Begin Registration and verify let's get started page
    Then Validate Title "Enter your Personal Details | Ireland State Savings Online"
    And  user click on next button of lets get stared page
    Then Validate Title "Registration | Ireland State Savings"
 # user not found
    And User click on Backtostatesavings
    When click on Begin Registration and verify let's get started page
    Then user enter invalid details for Title
    Then Validate Title "Enter your Personal Details | Ireland State Savings"
    And  user click on next button of lets get stared page
    Then Validate Title "Details not found | Ireland State Savings"
    Then Launch the url
    Then User click forgot password button enters email: "AMLOnlineUser6@mailinator.com" on slider click on next button
    Then User provides verification code clicks request reset link button for this mobile number: "020-8080047"
    Then Considering the user opens the Mailinator portal and provides the email: "AMLOnlineUser6@mailinator.com"
    Then User clicks password change button present in email message body
    And  User enters new password: "Dummy!2025", clicks next to confirm input
    Then Validate Title "Reset password | Ireland State Savings"
# ECR
    Then Launch the url
    Then click on Register
    When click on Begin Registration and verify let's get started page
    Then Validate Title "Enter your Personal Details | Ireland State Savings"
    Then user enter the details for ECR "<Scenario>"
    And  user click on next button of lets get stared page
    And  Click on I have my PIN button
    And  verify Enter your Email address page
    Then Validate Title "Enter your email address | Ireland State Savings"
    Then Enter and Re enter email for Title
    And check the consent on Email page
    And  Click on next button of email page
    Then enter your PIN "<pin>"
    Then Validate Title "Enter your PIN | Ireland State Savings"
    Then Click on continue button of PIN page
    Then Validate Title "Check your inbox | Ireland State Savings"
    And open Email for ECR
    Then Validate Title "Enter your mobile number | Ireland State Savings"
    And Enter all the details for mobile number
    Then Click on continue button of mobile page
    Then Validate Title "Enter Verification Code | Ireland State Savings"
    Then Click on verify button of security code
    And validate the create password page
    Then Validate Title "Create your password | Ireland State Savings"
    And click on Show password button
    And Click on complete button of password page
    And Validate the congratulations message
    Then Validate Title "Sign in | Ireland State Savings"
    And First time login using ECR User


    Examples:
      | Scenario | pin    | option   | Number      | Email                        |
      | TC04     | 940546 | Reinvest | 020-8030031 | UAT-WF-User82@mailinator.com |

















