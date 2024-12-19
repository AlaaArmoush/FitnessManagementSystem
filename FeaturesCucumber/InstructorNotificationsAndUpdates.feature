Feature: Notifications and Updates for Instructors
  As an instructor,
  I want to send notifications and updates to clients
  So that they are informed about important changes and new opportunities.
 
 
 Scenario: Notify clients about changes to program schedules
 		Given Im logged in as an instructor with id "instructor11" password "123123"
    Given the instructor has a program with id "100001" with enrolled clients
    When the instructor has updated the schedule of the program with id "100001"
    Then all the clients enrolled in the program with id "100001" should be notified with the new schedule in their inbox.
  
   Scenario: Announce a new fitness program
  	Given Im logged in as an instructor with id "instructor11" password "123123"
    When the instructor creates a new program
    Then the clients should be notified about the new program in their inbox.
    
  Scenario: Announce a special offer
    Given Im logged in as an instructor with id "instructor11" password "123123"
    Given the instructor has a program with id "100002" with enrolled clients
    When the instructor has updated the price of the program with id "100002"
    Then all the clients should be notified with the new offer in their inbox.
  
    