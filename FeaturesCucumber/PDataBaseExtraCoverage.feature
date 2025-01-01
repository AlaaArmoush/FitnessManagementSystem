Feature: Program Data Base Extra Coverage

  Scenario: Check for existing program by ID
    Given a program with ID "100001" exists in the database
    When a user attempts to add a program with ID "100001"
    Then the operation should fail with a message "this program already exists"

  Scenario: Verify program existence for a specific instructor
    Given a program with ID "100001" and instructor ID "instructor11" exists in the database
    When a user checks if the program exists for instructor ID "instructor11"
    Then the operation should return true

  Scenario: Verify program existence for a different instructor
    Given a program with ID "100001" and instructor ID "instructor11" exists in the database
    When a user checks if the program exists for instructor ID "instructor12"
    Then the operation should return false with a message "you dont have access to this program"

  Scenario: Retrieve programs for an instructor
    Given multiple programs exist in the database
    And instructor ID "instructor13" is assigned to some programs
    When a user retrieves programs for instructor ID "instructor13"
    Then the returned list should contain only the programs for instructor ID "instructor13"

  Scenario: Check and update program creation status
    Given no program creation status is initially set
    When a user sets the program creation status to true
    Then the "isProgramCreated" method should return true

  Scenario: Retrieve all clients for an instructor
    Given instructor ID "instructor13" has multiple programs with enrolled clients
    When a user retrieves all clients for instructor ID "instructor13"
    Then the returned list should contain all enrolled clients across the programs for instructor ID "instructor13"
