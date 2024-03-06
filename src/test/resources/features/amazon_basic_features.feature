Feature: Basic features

  Scenario Outline: Product search
    Given I am on the Amazon website
    When I look for <a product> in the search bar
    Then I should be able to go to the <product page>

    Examples:
      |a product|product page|
      |Bolso    |Amazon.es : Bolso|
      |Cargador |Amazon.es : Cargador|

