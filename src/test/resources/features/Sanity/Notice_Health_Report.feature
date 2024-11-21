@Notice_Health_Report
Feature: Check Notice for all the products

  @Notice_Inst_Child
  Scenario Outline: Content Validation for installment and Childcare
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User clicks on manage button and select monthly instalment savings
    And User opens matured holding page selects method type
    Examples:
      | Scenario                       |
      | Notice_UAT_Child_Donor         |
      | Notice_UAT_Child_Matured       |
      | Notice_UAT_Child_Non_Matured   |
      | Notice_UAT_Install_Donor       |
      | Notice_UAT_Install_Matured     |
      | Notice_UAT_Install_Non_Matured |
      | Notice_UAT_Install_Flushed     |


  @Notice_FT
  Scenario Outline: Content Validation For FT
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    And User opens matured holding page selects method type
    Examples:
      | Scenario                                |
      | Notice_UAT_NSB_4_Matured                |
      | Notice_UAT_NSB_4_Non_Matured            |
      | Notice_UAT_NSB_4_Flushed                |
      | Notice_UAT_NSB_10_Matured               |
      | Notice_UAT_NSB_10_Non_Matured           |
      | Notice_UAT_NSB_10_Flushed               |
      | Notice_UAT_NSB_10_Non_Matured_Other     |
      | Notice_Saving_Bond_Matured              |
      | Notice_Saving_Bond_Non_Matured          |
      | Notice_Savings_Certificate_Matured      |
      | Notice_Savings_Ceritificate_Non_Matured |
      | Notice_SSA                              |

  @JointContentcheck
  Scenario Outline: Reinvest and Cashin Content Validation
    Given User login using valid credentials for "<Scenario>"
    When Validate IBAN status is displayed
    When User select State Savings Product and clicks on manage button
    Then User clicks on joint button
    And User selects joint holding with mentioned "<name>"
    Then User verify notice button with mentioned "<Holding Id>"
    Then User clicks on button and verifies the modal content
    When User click on more details and verifies button present is same as on holdings page
    Examples:
      | Scenario | name          | Holding Id|
      | TC_84    | REV HIDDERLEY |            |

