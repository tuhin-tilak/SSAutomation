@PPO
Feature: Prize Payment Option

  @K13Regression
    @PPOAutoReinvest
  Scenario Outline: Prize Bond winings payment option <scenario>
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When User navigates to profile and setting
    And User select prize bond settings from profile and settings menu
    Then User decides "<PrizePaymentOption>" for existing winnings
    And User clicks confirm on PPO modal
    Then User verifies the notification banner displayed for PPO settings changed

    Examples:
      | Username                     | Password   | Mobilenumber | PrizePaymentOption | scenario |
      | RebrandAutomationUser13@mailinator.com | Dummy!2024 | 020-8890072  | autoreinvest     |TC 62  |
#      | UAT-WF-User18@mailinator.com | Dummy!2022 | 020-8030018  | autoreinvest       | TC 62    |

  @K13Regression
    @PPOTransfer
  Scenario Outline: Prize Bond winings payment option <scenario>
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When User navigates to profile and setting
    And User select prize bond settings from profile and settings menu
    Then User decides "<PrizePaymentOption>" for existing winnings
    And User clicks confirm on PPO modal
    Then User verifies the notification banner displayed for PPO settings changed

    Examples:
      | Username                     | Password   | Mobilenumber | PrizePaymentOption | scenario |
      | RebrandAutomationUser13@mailinator.com | Dummy!2024 | 020-8890072  | transfer       |TC 63  |
#      | UAT-WF-User18@mailinator.com | Dummy!2022 | 020-8030018  | transfer           | TC 63    |

#  @OptionCheck
#  Scenario Outline: Validate PPO either option is enabled
#    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
#    When User navigates to profile and setting
#    And User select prize bond settings from profile and settings menu
#    Then User decides "<PrizePaymentOption>" for existing winnings
#    And User clicks confirm on PPO modal
#    And User validates if one option is enabled then other option is disabled
#    Examples:
#      | Username                           | Password       | Mobilenumber | PrizePaymentOption |
#      | Perf-Test-User-9951@mailinator.com | MyAccount_2021 | 020-8009951  | Transfer           |
#      | Perf-Test-User-9951@mailinator.com | MyAccount_2021 | 020-8009951  | Autoreinvest       |

  @FirstUserLogin
  Scenario Outline: Validate PPO either option is disabled
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When User navigates to profile and setting
    And User select prize bond settings from profile and settings menu
    Then Validate both PrizePayment option are disabled
    Examples:
      | Username                            | Password   | Mobilenumber |
      | Perf-Test-User-10241@mailinator.com | Dummy!2022 | 020-8010235  |

  @IBANPPo
  Scenario Outline: Validate PPO either option is enabled including add iban
    Given User login using valid credential "<Username>""<Password>""<Mobilenumber>"
    When User navigates to profile and setting
    And User select prize bond settings from profile and settings menu
    Then User decides "<PrizePaymentOption>" for existing winnings
    And User click add bank details on PPO modal
    And User add IBAN according to "<Journey>""<IBAN>""<Mobilenumber>"
    Examples:
      | Username                            | Password   | Mobilenumber | PrizePaymentOption | Journey | IBAN   |
#      |  Perf-Test-User-10241@mailinator.com | Dummy!2022 | 020-8010235   | Transfer           | Blank       |                    |
      | Perf-Test-User-10241@mailinator.com | Dummy!2022 | 020-8010235  | Transfer           | Invalid | 123456 |
#      |  Perf-Test-User-10241@mailinator.com | Dummy!2022 | 020-8010235   | Transfer           | Yes     | IE64IRCE92050112345678 |






