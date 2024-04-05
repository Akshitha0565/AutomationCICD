@tag
  Feature: Purchase the order from Ecommerce website

    Background:
      Given I landed on Ecommerce page

    @Regression
    Scenario Outline: Positive Test of Submitting the Order

      Given Logged in with username <username> and password <password>
      When I add the product <productName> to the Cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage
      Examples:
        | username                 |   password     | productName   |
        | akshithareddy@gmail.com  |   Abcd1234     | ZARA COAT 3   |


