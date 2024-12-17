#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Instructor program management 
  I want the instructors to be able to manage their own programs so they can Create, update, or delete fitness programs 
  
 
  
  Scenario: Create new fitness program
    Given Im logged in as an instructor with id "instructor11" password "123123"
    When the instructor wants to create new program with title as "flexibility workouts" the duration time as "8 weeks" the difficulty level as "easy" the goals as "improve flexibility and posture"
    And uploads video titorials, images, or documents about the program
    And sets the price as "300"
    Then the program chould be created and visible in the intructors program list
    
    
  
 
  Scenario: updating an existing program details
  	Given Im logged in as an instructor with id "instructor11" password "123123"
    Given the instructor has a program with id "100001"
    When the instructor wants to update the program details with title to "Total Body Workout" the duration to "4 weeks" the difficulty level to "Intermediate" the goals to "Improve strength and flexibility" to program with id "100001"
    Then the updated details should be visible in the program list.

	Scenario: updating an extisting program attachments
		Given  Im logged in as an instructor with id "instructor11" password "123123"
		Given the instructor has a program with id "100001"
		When the instructor wants to update the attachments of the program with id "100001"
		And adds attachments to the program with id "100001"
		Then the new attachments should be added to the program
		
	Scenario: updating price of an existing program
		Given  Im logged in as an instructor with id "instructor11" password "123123"
  	Given the instructor has a program with id "100001"
	  When the instructor wants to update the price to applicable price of "100"
	  Then the program price should be updated.		
		
	Scenario: fail to update price of an existing program 
		Given  Im logged in as an instructor with id "instructor11" password "123123"
  	Given the instructor has a program with id "100001"
	  When the instructor wants to update the price to unapplicable price of "-10"
	  Then the program price should remain unchanged.
	  
	
	
	Scenario: delete a program
		Given  Im logged in as an instructor with id "instructor11" password "123123"
		Given the instructor has a program with id "100000"
		When the instructor wants to delete the program with id "100000"
		Then the program should no longer appear in the instructors program list
		
		
		
	Scenario: set schedules for group session
		Given  Im logged in as an instructor with id "instructor11" password "123123"
		Given the instructor has a program with id "100002"
		When the instructor wants to set a schedule and chooses session type as "online" schedual as "every Monday and Wednesday at 6:00 PM" for the program with id "100002"
		Then the schedule should be created succesfully and visible to the instructor and clients.
		
		
		
		
		
