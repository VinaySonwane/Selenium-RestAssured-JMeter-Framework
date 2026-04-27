Feature: E-commerce Checkout Flow

  Scenario: Verify user can add items to cart and complete checkout
    Given I am on the SauceDemo login page
    When I login with "standard_user" and "secret_sauce"
    And I add "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    And I navigate to the checkout page
    And I enter checkout details with "Vinay" "Sonwane" and "440010"
    Then the total price should correctly include tax and shipping
    And I should see the order confirmation message "Thank you for your order!"