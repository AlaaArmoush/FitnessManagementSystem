Feature: Subscription Management
  As an admin, I want to manage subscription plans so that I can offer tailored options for clients and instructors.

  Scenario: Create a new subscription plan
	  Given I am logged in as an admin id "admin123" password "1001"
    When I create a subscription plan with tier "Premium", price "250ILS", discount "50%", and days per month "30"
    Then the subscription plan should be created successfully

  Scenario: Update an existing subscription plan
    Given a subscription plan with tier "Basic" exists
    When I update the price to "220ILS", discount to "30%", and days per month to "26"
    Then the subscription plan should be updated successfully

  Scenario: Monitor all subscription plans
	  Given I am logged in as an admin id "admin123" password "1001"
    When I view the list of subscription plans
    Then I should see all subscription plans with their details
    
  Scenario: Delete a subscription plan
    Given a subscription plan with tier "Basic" exists
    When I delete the subscription plan
    Then the subscription plan should no longer be available
