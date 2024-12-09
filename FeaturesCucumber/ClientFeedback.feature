
Feature: Feedback and Reviews 
	as a client, I want to be able to provide reviews for any completed program, and provide feedback 
	
  Scenario: Reviewing and Rating
    Given Im logged in as a client id "client123" password "2002" 
    When I rate program "program123" with rating "9" and review "Strongly recommend for anyone that wants to lose weight"
    Then that review should be created and added to the reviews data base 

  Scenario: Giving Feedback
    Given Im logged in as a client id "client123" password "2002" 
    When I write the feedback "the gym could use more dumbbels"
    Then that feedback should be created and added to the feedbacks data base 

