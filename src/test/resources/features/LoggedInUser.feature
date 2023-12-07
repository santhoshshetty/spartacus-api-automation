@Logged
Feature: Logged In User Cart

  @LoggedinUserPLP
  Scenario Outline: Get PLP
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Given I search for a product "<product>" and verify the results

    Examples: 
      | product |
      | 1986316 |

  @LoggedinUserSearch
  Scenario Outline: Keyword Search
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Given I search for a product using keyword "<keyword>" and verify the results

    Examples: 
      | keyword |
      | camera  |

  @LoggedinUserTypeAheadSearch
  Scenario Outline: Type Ahead Search
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Given I search for a product "<product>" using type ahead search and verify the results

    Examples: 
      | product |
      | camer   |

  @LoggedinUserATC
  Scenario Outline: Add items to a cart and fetch cart
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    And I delete the cart

    Examples: 
      | payLoad  |
      | ATC.json |

  @LoggedinUserUpdateCart
  Scenario Outline: Update and Fetch cart
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    And I update a cartID to add items "<updatedPayLoad>" to the cart
    Then I should be able to get the cart details "<updatedPayLoad>"
    And I delete the cart

    Examples: 
      | payLoad  | updatedPayLoad  |
      | ATC.json | UpdateCart.json |

  @LoggedinUserDeleteCart
  Scenario Outline: Add items to a cart and fetch cart
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    And I delete the cart

    Examples: 
      | payLoad  |
      | ATC.json |

  @LoggedinUserAddAddress
  Scenario Outline: Create cart and add address
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    And I delete the cart

    Examples: 
      | payLoad  | address                |
      | ATC.json | AddAccountAddress.json |

  @LoggedinUserUpdateAddress
  Scenario Outline: Create cart and update address
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to add address "<updateAddress>"
    And I delete the cart

    Examples: 
      | payLoad  | address                | updateAddress             |
      | ATC.json | AddAccountAddress.json | updateAccountAddress.json |

  @LoggedinUserDeleteAddress
  Scenario Outline: Create cart and update address
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to delete address
    And I delete the cart

    Examples: 
      | payLoad  | address                |
      | ATC.json | AddAccountAddress.json |

  @LoggedinUserSetDeliveryMode
  Scenario Outline: Set Delivery
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    And I delete the cart

    Examples: 
      | payLoad  | address                | deliveryMode |
      | ATC.json | AddAccountAddress.json | premium-net  |

  @LoggedinUserDeleteDeliveryMode
  Scenario Outline: Set Delivery
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    And I should be able to delete delivery method "<deliveryMode>"
    And I delete the cart

    Examples: 
      | payLoad  | address                | deliveryMode |
      | ATC.json | AddAccountAddress.json | premium-net  |

  @LoggedinUserPayment
  Scenario Outline: Add Payment details
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    Then I should be able to add payment details "<payment>"
    And I delete the cart

    Examples: 
      | payLoad  | address                | deliveryMode | payment         |
      | ATC.json | AddAccountAddress.json | premium-net  | AddPayment.json |

  @LoggedinUserCreateOrder
  Scenario Outline: Create Order
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    Then I should be able to add payment details "<payment>"
    Then I should be able to create order

    Examples: 
      | payLoad  | address                | deliveryMode | payment         |
      | ATC.json | AddAccountAddress.json | premium-net  | AddPayment.json |

  @OrderManagement
  Scenario Outline: Create Order
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    Then I should be able to add address "<address>"
    Then I should be able to set delivery method "<deliveryMode>"
    Then I should be able to add payment details "<payment>"
    Then I should be able to create order
    Then I Get Total Number of Orders
    Then I Get order history details and verify created order
    Then I Get order details for the created order

    Examples: 
      | payLoad  | address                | deliveryMode | payment         |
      | ATC.json | AddAccountAddress.json | premium-net  | AddPayment.json |

  @SetSavedAddressinCheckout
  Scenario Outline: Set saved address
    Given I login to the application
      | client_id     | mobile_android     |
      | client_secret | secret             |
      | grant_type    | password           |
      | username      | demouser@gmail.com |
      | password      | Test@123           |
    Then I delete the cart before scenario exec
    Given I create a cartID to add items to the cart
    And I add an item "<payLoad>" to the cart
    Then I should be able to get the cart details "<payLoad>"
    And I should be able to get customers addresses
    And I should be able to set saved addresses

    Examples: 
      | payLoad  |
      | ATC.json |
