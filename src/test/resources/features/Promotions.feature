Feature: Promotions

  @LoggedinUserAddPromotion
  Scenario Outline: Add promotions for a logged in user
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add promotion
      | promotionid | TestPromotion |
    And I should be able to add promotion
      | promotionid | TestPromotion1 |
    And I should be able to get list of promotions
       Examples: 
      | payLoad  |
      | ATC.json |

  @LoggedinUserDeletePromotion
  Scenario Outline: Delete promotion for a logged in user
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    And I should be able to add promotion
      | promotionid | TestPromotion |
    And I should be able to add promotion
      | promotionid | TestPromotion1 |
    And I should be able to get list of promotions
    Then I should be able to delete promotions
      | promotionid | TestPromotion |
         Examples: 
      | payLoad  |
      | ATC.json |
