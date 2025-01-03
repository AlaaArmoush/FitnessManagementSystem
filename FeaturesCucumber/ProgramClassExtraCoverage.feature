Feature: Program Class Extra Coverage
  Ensure all functionalities of the Program class are tested effectively.

  Scenario: Creating a program with specific details
    Given a program with title "Fitness Bootcamp", duration time "12 hours", difficulty level "Intermediate", goals "Weight Loss", and instructor ID "instructor11"
    When the program ID is set to "P12345"
    Then the program ID should be "P12345"

  Scenario: Adding a new client to the program
    Given a program with title "Yoga Class", duration time "10 hours", difficulty level "Beginner", goals "Flexibility", and instructor ID "instructor11"
    When the instructor adds a new client with ID "C123"
    Then the client should be added to the program

  Scenario: Checking if a client exists in the program
    Given a program with title "Strength Training", duration time "15 hours", difficulty level "Advanced", goals "Muscle Gain", and instructor ID "instructor11"
    When the program checks if a client with ID "C999" exists
    Then the operation should return false for the client

  Scenario: Displaying clients' progress in the program
    Given a program with title "Cardio Blast", duration time "8 hours", difficulty level "Intermediate", goals "Endurance", and instructor ID "instructor11"
    When the program shows the clients' progress
    Then the client progress details should be printed

  Scenario: Setting the sessions count for the program
    Given a program with title "HIIT Workout", duration time "5 hours", difficulty level "Advanced", goals "Fat Burn", and instructor ID "instructor11"
    When the program sets the sessions count to 10
    Then the sessions count should be 10

  Scenario: Setting the targeted hours for the program
    Given a program with title "Meditation Workshop", duration time "3 hours", difficulty level "Beginner", goals "Stress Relief", and instructor ID "instructor11"
    When the program sets targeted hours to 3
    Then the targeted hours should be 3

  Scenario: Notifying clients about a new schedule
    Given a program with title "Pilates Class", duration time "6 hours", difficulty level "Intermediate", goals "Core Strength", and instructor ID "instructor11"
    When the program notifies clients about the new schedule
    Then the notification should be sent successfully
