Feature: Purchase process

  Scenario: Add a product to the cart
    Given I am on the Amazon website
    When I look for chair in the search bar
    And add a random product to the cart
    Then I should see the product in the cart
