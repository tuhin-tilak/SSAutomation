Feature: User Notification and Notification setting
  String username, String password, String number)

  @demo_notification
  Scenario Outline: User change profile password and able to see the notification in the notification tab
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When User change the profile password with "<number>" and provide new "<password2>"
    And click on the Notification menu and navigates to the Notification Page
    And User is able to view the Notification DropDown
    Then User selects the "<action>" and able to view the respective notifications
    Examples:
      | Username                         | Password   | Mobilenumber | action |
      | UATAUTO-WF-User39@mailinator.com | Dummy!2022 | 020-8030039  | Alerts |

  @K13Regression
    @demo_notification_alert
  Scenario Outline: Notification Settings Change (Alerts enabled) <scenario>
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User disabled the alerts and able to view the Confirm change page
    And Clicks on the Confirm and is able to view the alert message

    Examples:
      | Username                            | Password   | Mobilenumber | scenario |
#      | Perf-Test-User-10262@mailinator.com | Dummy!2022 | 020-8010252  |TC 66|
      | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218  | TC 66    |

  @K13Regression
    @demo_notification_message
  Scenario Outline: Notification Settings Change (Messages enabled) <scenario>
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User clicks message toggle button and able to view the Confirm change modal
    Then Clicks on the Confirm and is able to view the alert message
    Examples:
      | Username                            | Password   | Mobilenumber | scenario |
#      | Perf-Test-User-10262@mailinator.com | Dummy!2022 | 020-8010252  | TC 65    |
      | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218  | TC 65    |

  @K13Regression
    @demo_notification_alert
  Scenario Outline: Notification Settings Change (Both Alerts and Messages enabled) <scenario>
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    And click on the Notification menu and navigates to the Notification Page
    And clicks on the Notifications and Setting tab and is navigated to notifications and settings page
    Then User disabled the alerts and able to view the Confirm change page
    And Clicks on the Confirm and is able to view the alert message
    Then User clicks message toggle button and able to view the Confirm change modal
    And Clicks on the Confirm and is able to view the alert message

    Examples:
      | Username                            | Password   | Mobilenumber | scenario |
#      | Perf-Test-User-10262@mailinator.com | Dummy!2022 | 020-8010252  | TC 67    |
      | Perf-Test-User-10224@mailinator.com | Dummy!0000 | 020-8010218  | TC 67    |