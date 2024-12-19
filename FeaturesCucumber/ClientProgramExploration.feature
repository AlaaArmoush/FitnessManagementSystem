Feature: Client Program Exploration
as a client i want to filter programs by difficulty level, duration time, enroll in programs and see their schedules 

  Scenario: filter programs by difficulty 
    Given Im logged in as a client id "client123" password "2002" 
    When I want to see programs for difficulty "Beginners" 
    Then All programs of the difficulty "Beginners" should be shown 
 
  Scenario: filter programs by duration time 
    Given Im logged in as a client id "client123" password "2002" 
    When I want to see programs that go up to 30 hours 
    Then All programs of that duration 30 should be shown
    
  Scenario: enroll in programs  
    Given Im logged in as a client id "client123" password "2002" 
    When I want to entroll in program id "100000"
    Then I should be added to that program "100000"

  Scenario: show programs schedules
    Given Im logged in as a client id "client123" password "2002" 
    When I want to see my programs schedule 
    Then I should see the scheduled session for each program im enrolled in
    
    
    
          
    
    
    
          
