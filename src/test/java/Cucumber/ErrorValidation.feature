@tag
Feature: Error validation

  Background:
    Given I landed on Ecommerce page

  @Errorvalidation
  Scenario Outline: Positive Test of Submitting the Order
    Given I landed on Ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username                 |   password     |
      | akshithareddy@gmail.com  |   Abcd1235     |