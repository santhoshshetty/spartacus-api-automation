Feature: Guest User Cart

  @GuestKeywordSearch
  Scenario Outline: Keyword Search
    Given I search for a product using keyword "<query>" and verify the results

    Examples: 
      | query  |
      | camera |

  @GuestTypeAheadSearch
  Scenario Outline: Type Ahead Search
    Given I search for a product "<term>" using type ahead search and verify the results

    Examples: 
      | term  |
      | camer |

  @GuestUserATC
  Scenario Outline: Add items to a cart and fetch cart
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"

    Examples: 
      | payLoad  |
      | ATC.json |

  @GuestUserUpdateCart
  Scenario Outline: Update and Fetch cart
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I update a cartID to add items "<updatedPayLoad>" to the cart
    Then I should be able to get the cart details "<updatedPayLoad>"

    Examples: 
      | payLoad  | updatedPayLoad  |
      | ATC.json | UpdateCart.json |

  @GuestDeleteCart
  Scenario Outline: Add items to a cart and delete cart
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I delete the cart

    Examples: 
      | payLoad  |
      | ATC.json |

  @GenerateGuestToken
  Scenario: Generate token and assign email to cart
    Given I create a cartID to add items to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |

  @GuestAssignEmailtoCart
  Scenario Outline: Generate token and assign email to cart
    Given I create a cartID to add items to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart

    Examples: 
      | email              |
      | santhosh@gmail.com |

  @GuestAddAddress
  Scenario Outline: Create cart and add address
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"

    Examples: 
      | email              | payLoad  | address                |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json |

  @GuestUpdateAddress
  Scenario Outline: Create cart and update address
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"
    Then I should be able to add address "<updateAddress>"

    Examples: 
      | email              | payLoad  | address                | updateAddress             |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json | updateAccountAddress.json |

  @GuestDeleteAddress
  Scenario Outline: Create cart and update address
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"
    Then I should be able to delete address

    Examples: 
      | email              | payLoad  | address                |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json |

  @GuestSetDeliveryMode
  Scenario Outline: Set Delivery
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"

    Examples: 
      | email              | payLoad  | address                | deliveryMode |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json | premium-net  |

  @GuestDeleteDeliveryMode
  Scenario Outline: Set Delivery
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    And I should be able to delete delivery method "<deliveryMode>"

    Examples: 
      | email              | payLoad  | address                | deliveryMode |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json | premium-net  |

  @GuestUserPayment
  Scenario Outline: Add Payment details
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    Then I should be able to add payment details "<payment>"

    Examples: 
      | email              | payLoad  | address                | deliveryMode | payment         |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json | premium-net  | AddPayment.json |

  @GuestUserCreateOrder
  Scenario Outline: Create Order
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    And I generate a token
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | client_credentials |
      | username      |               1234 |
      | password      | Test@123           |
    Then I should be able assign email "<email>" to the cart
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    Then I should be able to add payment details "<payment>"
    Then I should be able to create order

    Examples: 
      | email              | payLoad  | address                | deliveryMode | payment         |
      | santhosh@gmail.com | ATC.json | AddAccountAddress.json | premium-net  | AddPayment.json |
