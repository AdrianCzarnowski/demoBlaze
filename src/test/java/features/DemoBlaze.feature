Feature:  DemoBlaze Test

  Scenario: Purchase random smartphones with new account created

    Given I have data to create new account with random value and user can be login
    When User should choose product category and random product from category
    And  User should perform add to basket and remove from basket
    Then User should fill order form and see order confirm
