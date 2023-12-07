Feature: Vouchers

  @LoggedinUserAddVoucher
  Scenario Outline: Add Voucher for a logged in user
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
    Then I should be able to add voucher
      | promotionid | TestPromotion |
    And I should be able to add voucher
      | promotionid | TestPromotion1 |
    And I should be able to get list of vouchers
     Examples: 
      | payLoad  |
      | ATC.json |

  @LoggedinUserDeleteVoucher
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
    And I should be able to add voucher
      | voucherid | TestVoucher |
    And I should be able to add voucher
      | voucherid | TestVoucher1 |
    And I should be able to get list of promotions
    Then I should be able to delete vouchers
      | voucherid | TestVoucher1 |
      Examples: 
      | payLoad  |
      | ATC.json |
