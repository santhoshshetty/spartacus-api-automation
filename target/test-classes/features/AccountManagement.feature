@AccountManagement
Feature: AccountManagement

  @UpdateUserDetails @Acct
  Scenario Outline: Change First Name and Last Name of User
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Given I change the First name and Last Name "<user>" of the user

    Examples: 
      | user      |
      | FNLN.json |

  @AcctMgmtAddress @Acct
  Scenario Outline: Verify Address  for a user
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | raja@gmail.com |
      | password      | Test@123                   |
    Then I Add the New Address "<address>"
    Then I update the Address "<updateAddress>"
    Then I delete the Address

    Examples: 
      | address                | updateAddress             |
      | AddAccountAddress.json | UpdateAccountAddress.json |

  @UpdateEmail
  Scenario Outline: Change Email Address of User
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Given I change the Email Address of the user with <newEmail> and <password>

    Examples: 
      | newEmail | password |
      | newEmail | password |

  @UpdatePassword
  Scenario Outline: Update password of User
    Given I login to the application
      | client_id     | mobile_android             |
      | client_secret | secret                     |
      | grant_type    | password                   |
      | username      | santhoshshetty58@gmail.com |
      | password      | Test@123                   |
    Given I update the password of the user with <newPassword> using old <password>

    Examples: 
      | newPassword | password |
      | newPassword | password |
