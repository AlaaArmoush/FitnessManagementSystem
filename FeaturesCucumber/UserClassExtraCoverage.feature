Feature: User Class Extra Coverage

  Scenario: Test setting ID for a user
    Given a user is created with ID "T001"
    When the user sets their ID to "T002"
    Then the user ID should be "T002"

  Scenario: Test getting body status based on weight
    Given a user has a height of 170 and weight of 90
    Then the user body status should be "Overweight"

    Given a user has a height of 170 and weight of 50
    Then the user body status should be "Underweight"

    Given a user has a height of 170 and weight of 70
    Then the user body status should be "Healthy"

  Scenario: Test showing inbox with messages
    Given a user has inbox items
    When the user shows their inbox
    Then the inbox should not be empty

  Scenario: Test earning badges
    Given a user has earned no badges
    When the user increments their pro count
    Then the user should have earned the "NEWBIE_CHAMP" badge

    When the user increments their pro count again
    Then the user should have earned the "RISING_STAR" badge

 
Scenario: User is marked absent for a program and the absence count updates
Given a user is enrolled in a program with ID "100001" and 0 absences
When the user is marked absent for the program with ID "100000"
Then the absence count for the program with ID "100001" should be 1
