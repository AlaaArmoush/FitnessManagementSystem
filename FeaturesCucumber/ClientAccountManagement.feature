Feature: Client Account Management
  As a client, i want to be able to create a profile and customise it

  Scenario: creating a new profile
    Given Im logged in as a client id "client123" password "2002" 
    When I create a profile with age "20" goal "weight loss" diet "LOW_CARB"
    Then a profile for that user should be created

	Scenario: updating a profile info
		Given Im logged in as a client id "client123" password "2002" 
		When I update my profile with age "21" goal "building muscle" diet "LOW_FAT"
		Then a profile for that user should be updated
		
	Scenario: deleting a profile 
		Given Im logged in as a client id "client123" password "2002" 
		When I delete the profile
		Then the profile should be deleted
		
	

