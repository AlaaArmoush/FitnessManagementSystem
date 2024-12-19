
Feature: Instructor Client interaction
  As a fitness instructor,
  I want to interact with my clients
  So that I can communicate effectively and provide them with valuable feedback and progress updates.

	
	
	Scenario: Communicate with enrolled clients via messaging
		Given Im logged in as an instructor with id "instructor11" password "123123"
		Given the the instructor has a program with id "100001"
	  When the instructor with id "instructor11" wants to send a message "Greate job" to client with id "client790" enrolled in program with id "100001"
	  Then the client with id should receive the message in their inbox
			
		
	Scenario: start a disscussion in the forum
		Given Im logged in as an instructor with id "instructor11" password "123123"
		Given the the instructor has a program with id "100001"
    When the instructor with id "instructor11" wants to create a new topic "topic name" with a message "what do you think about the last exercise we did in our last session?" for the program with id "100001"
    Then the topic should be visible to all enrolled clients.
    
	Scenario: provide a feedback to client
		Given Im logged in as an instructor with id "instructor11" password "123123"
		Given the the instructor has a program with id "100001"
	  When the instructor with id "instructor11" wants to send a feedback "make sure your back is straight while doing this exercise" to client with id "client790"  enrolled in program with id "100001"
	  Then the client with id should receive the feedback in their inbox

	Scenario: share a progress report with a client
		Given Im logged in as an instructor with id "instructor11" password "123123"
		Given the the instructor has a program with id "100001"
	  When the instructor with id "instructor11" wants to send a progress report to all clients enrolled in program with id "100001" enrolled in program with id "100001"
	  Then the client with id should receive the report in their inbox
		
		
	
    
		

