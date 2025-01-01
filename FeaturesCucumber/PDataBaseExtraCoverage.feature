Feature: Program Management

  Scenario: Check if a program already exists when adding a new program
    Given the system has a program with ID "100000"
    When a new program with ID "100000" is added
    Then the system should return "this program already exists"

  Scenario: Check program existence with instructor access
    Given the system has a program with ID "100000" and instructor ID "instructor13"
    When the instructor "instructor13" checks if the program with ID "100000" exists
    Then the system should return "Program exists"

  Scenario: Check program existence without instructor access
    Given the system has a program with ID "100000" and instructor ID "instructor13"
    When the instructor "instructor14" checks if the program with ID "100000" exists
    Then the system should return "you don't have access to this program"

  Scenario: Set price for a program
    Given a program with ID "100000"
    When the price for the program is set to "200NIS"
    Then the system should return "price was set"

  Scenario: Set program created status
    Given the program creation status is false
    When the program creation status is set to true
    Then the program created status should be true

  Scenario: Get all clients for a given instructor
    Given the system has programs for instructor "instructor13"
    When the instructor "instructor13" requests all clients
    Then the system should return a list of enrolled clients for that instructor
