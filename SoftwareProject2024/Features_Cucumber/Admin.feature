
Feature: Admin Features in the Fitness Management System
  As an admin,
  I want to manage users, monitor programs, handle content, and manage subscriptions,
  So that I can efficiently oversee the system's operations.

  # User Management Scenarios
  Scenario: Add a new instructor account
    Given I am logged in as an admin
    When I add a new instructor account with valid details
    Then the instructor account should be created successfully

  Scenario: Update client account information
    Given I am logged in as an admin
    And a client account exists
    When I update the client's account details
    Then the account information should be updated successfully

  Scenario: Deactivate a user account
    Given I am logged in as an admin
    And a user account exists
    When I deactivate the user's account
    Then the account status should be updated to "deactivated"

  Scenario: Approve new instructor registrations
    Given I am logged in as an admin
    And there are pending instructor registration requests
    When I approve a request
    Then the instructor account should be activated

  Scenario: Monitor user activity statistics
    Given I am logged in as an admin
    When I view user activity and engagement statistics
    Then I should see detailed metrics on user activity

  # Program Monitoring Scenarios
  Scenario: View most popular programs
    Given I am logged in as an admin
    When I view program statistics
    Then I should see the most popular programs based on enrollment

  Scenario: Generate reports on revenue and attendance
    Given I am logged in as an admin
    When I generate reports on revenue and attendance
    Then the reports should display the required data

  Scenario: Track active and completed programs
    Given I am logged in as an admin
    When I track active and completed programs
    Then I should see a categorized list of programs

  # Content Management Scenarios
  Scenario: Approve a wellness article
    Given I am logged in as an admin
    And there are pending articles shared by instructors
    When I approve an article
    Then the article should be published on the platform

  Scenario: Reject a wellness tip
    Given I am logged in as an admin
    And there are pending wellness tips shared by users
    When I reject a tip
    Then the tip should be marked as "rejected" with feedback provided

  Scenario: Handle user feedback or complaints
    Given I am logged in as an admin
    And there are pending user feedback or complaints
    When I address a complaint
    Then the complaint should be marked as resolved

  # Subscription Management Scenarios
  Scenario: Add a new subscription plan
    Given I am logged in as an admin
    When I create a new subscription plan with valid details
    Then the plan should be added successfully

  Scenario: Update an existing subscription plan
    Given I am logged in as an admin
    And a subscription plan exists
    When I update the subscription plan details
    Then the plan should reflect the updated information

  Scenario: Manage user subscriptions
    Given I am logged in as an admin
    And users have active subscriptions
    When I view user subscription details
    Then I should be able to modify or deactivate subscriptions
