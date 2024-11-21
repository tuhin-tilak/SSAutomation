@BuyNowRegression
Feature: Buy Now

  @GuestSole
  Scenario Outline: Guest sole purchase journey <TestCaseName>
    Given Launch the url
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
#    Then Enter payment details click pay
#    Then Verify Thank You page
    Examples:
      | TestCaseName |
#      | TC1          |
#      | TC2          |
#      | TC3          |
#      | TC4          |
#      | TC6          |
#      | TC7          |
      | TC8          |
#      | TC10         |
#      | TC11         |
#      | TC12         |
#      | TC13         |
#      | TC14         |
#      | TC15         |
#      | TC17         |
#      | TC18         |
#      | TC19         |
#      | TC21         |
#      | TC22         |

  @GuestPBGift
  Scenario Outline: Guest sole purchase PB Gift <TestCaseName>
    Given Launch the url
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters person details Prize Bond Gift
    Then User enters Prize Bond holder details
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC5          |
      | TC9          |
      | TC16         |
      | TC20         |
      | TC193        |
      | TC194        |
      | TC195        |
      | TC196        |
      | TC197        |
      | TC198        |
      | TC199        |
      | TC200        |
      | TC201        |
      | TC202        |
      | TC203        |
      | TC204        |
      | TC205        |
      | TC206        |
      | TC207        |
      | TC208        |
      | TC209        |
      | TC210        |


  Scenario Outline: Sign in sole purchase journey <TestCaseName>
    Given Launch the url
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC23         |
      | TC24         |
      | TC25         |
      | TC26         |
      | TC28         |
      | TC29         |
      | TC30         |
      | TC32         |
      | TC33         |
      | TC34         |
      | TC35         |
      | TC36         |
      | TC37         |
      | TC39         |
      | TC40         |
      | TC41         |
      | TC43         |
      | TC44         |

  
    @BuyNowRegression
    @SignIn_Joint
  Scenario Outline: Sign in joint purchase journey <TestCaseName>
    Given Launch the url
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC45         |
      | TC46         |
      | TC47         |
      | TC48         |
      | TC49         |
      | TC50         |
      | TC51         |
      | TC52         |
      | TC53         |
      | TC54         |
      | TC55         |
      | TC56         |
      | TC57         |
      | TC58         |
      | TC59         |
      | TC60         |
      | TC61         |
      | TC62         |
      | TC63         |
      | TC64         |
      | TC65         |
      | TC66         |
      | TC67         |
      | TC68         |
      | TC69         |
      | TC70         |
      | TC71         |
      | TC72         |
      | TC73         |
      | TC74         |
      | TC75         |
      | TC76         |
      | TC77         |
      | TC78         |
      | TC79         |
      | TC80         |


  @BuyNowRegression
    @SignIn_PBGift
  Scenario Outline: Sign in sole purchase PB Gift journey <TestCaseName>
    Given Launch the url
    Then Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters Prize Bond holder details
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC27         |
      | TC31         |
      | TC38         |
      | TC42         |
      | TC175        |
      | TC176        |
      | TC177        |
      | TC178        |
      | TC179        |
      | TC180        |
      | TC181        |
      | TC182        |
      | TC183        |
      | TC184        |
      | TC185        |
      | TC186        |
      | TC187        |
      | TC188        |
      | TC189        |
      | TC190        |
      | TC191        |
      | TC192        |


  @BuyNowRegression
    @Guest_Joint
  Scenario Outline: Guest joint purchase journey <TestCaseName>
    Given Launch the url
    Given Click buy now for "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC81         |
      | TC82         |
      | TC83         |
      | TC84         |
      | TC85         |
      | TC86         |
      | TC87         |
      | TC88         |
      | TC89         |
      | TC90         |
      | TC91         |
      | TC92         |
      | TC93         |
      | TC94         |
      | TC95         |
      | TC96         |
      | TC97         |
      | TC98         |
      | TC99         |
      | TC100        |
      | TC101        |
      | TC102        |
      | TC103        |
      | TC104        |
      | TC105        |
      | TC106        |
      | TC107        |
      | TC108        |
      | TC109        |
      | TC110        |
      | TC111        |
      | TC112        |
      | TC113        |
      | TC114        |
      | TC115        |
      | TC116        |


  Scenario Outline: Telesales Guest sole purchase journey <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC117        |
      | TC118        |
      | TC119        |
      | TC120        |
      | TC122        |
      | TC123        |
      | TC124        |
      | TC126        |
      | TC127        |
      | TC128        |
      | TC129        |
      | TC130        |
      | TC131        |
      | TC133        |
      | TC134        |
      | TC135        |
      | TC137        |
      | TC138        |

  @BuyNowRegression
    @Guest_PBGift_SoleHoldertelesales
  Scenario Outline: Telesales Guest sole purchase PB Gift <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then User enters person details Prize Bond Gift
    Then User enters Prize Bond holder details
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page
    Examples:
      | TestCaseName |
      | TC121        |
      | TC125        |
      | TC132        |
      | TC136        |

  @Rerun_Purchase
    @BuyNowRegression
    @Guest_Joint_Telesales
  Scenario Outline: Telesales Guest joint purchase journey <TestCaseName>
    Given Navigate to telesales "<TestCaseName>"
    When User select product for purchase
    Then User can continue as guest or sign-in during the journey
    Then Validate Your Order page
    Then Validate product and amount on review page
    Then User selects customer level critical data elements
    Then Enter payment details click pay
    Then Verify Thank You page

    Examples:
      | TestCaseName |
      | TC139        |
      | TC140        |
      | TC141        |
      | TC142        |
      | TC143        |
      | TC144        |
      | TC145        |
      | TC146        |
      | TC147        |
      | TC148        |
      | TC149        |
      | TC150        |
      | TC151        |
      | TC152        |
      | TC153        |
      | TC154        |
      | TC155        |
      | TC156        |
      | TC157        |
      | TC158        |
      | TC159        |
      | TC160        |
      | TC161        |
      | TC162        |
      | TC163        |
      | TC164        |
      | TC165        |
      | TC166        |
      | TC167        |
      | TC168        |
      | TC169        |
      | TC170        |
      | TC171        |
      | TC172        |
      | TC173        |
      | TC174        |
