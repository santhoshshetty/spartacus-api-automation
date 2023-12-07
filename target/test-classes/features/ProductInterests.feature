@Logged
Feature: Wishlist

  @CreateWishlist
  Scenario: Create Wishlist
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
   # And I add an item to the cart
   # And I add an item to the wishlist
     | productcode | code |
   # Then I should be able to get wishlist

  @DeleteWishlist
  Scenario: Delete Wishlist
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
   # And I add an item to the cart
   # And I add an item to the wishlist
      | productcode | code |
   # And I should be able to get wishlist
   # Then I should be able to delete wishlist
