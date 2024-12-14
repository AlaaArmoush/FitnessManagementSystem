Feature: Instructor Progress tracking 
	As an instructor i want to tack my clients progress so i can give them feedbacks and keep them motivated.
	
	
	Scenario: Monitor client progress
		Given Im logged in as an instructor with id "instructor11" password "123123"
		Given the the instructor has a program with id "100001"
		When the instructor wants to see the progress of his clients enrolled in the program with id "100001"
		Then a list of the clients with their progress should be displayed.
		
		
	Scenario: Send a motivational reminder to all clients
		Given Im logged in  as an instructor with id "instructor11" password "123123"
		Given the the instructor has a program with id "100001"
		When the instructor wants to send a motivational reminder "Keep the hard working up!" to all their clients enrolled in program with id "100001"
		Then the clients should recieve the message in their inbox.
