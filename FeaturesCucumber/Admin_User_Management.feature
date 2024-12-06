Feature: User Management in Fitness Management System
  As an admin,
  I want to manage user accounts (instructors and clients) and monitor activity,
  So that I can ensure proper functionality and engagement.

	Scenario: Add a new user account id
	  Given I am logged in as an admin id "admin123" password "1001"
	  When I add a new user account with valid details
	  Then the user account should be created successfully

  Scenario: Update user account information
	  Given I am logged in as an admin id "admin123" password "1001"
    And a user account exists
    When I update the users account details
    Then the account information should be updated successfully

  Scenario: Deactivate a user account
	  Given I am logged in as an admin id "admin123" password "1001"
    And a user account exists
    When I deactivate the users account
    Then the account status should be updated to "Deactivated"

  Scenario: Approve new instructor registrations
	  Given I am logged in as an admin id "admin123" password "1001"
    And there are pending instructor registration requests
    When I approve a request
    Then the instructor account should be "Activated"

  Scenario: Monitor user activity statistics
	  Given I am logged in as an admin id "admin123" password "1001"
    When I view user activity and engagement statistics
    Then I should see detailed metrics for both clients and instructors
