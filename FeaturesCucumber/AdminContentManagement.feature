Feature: Admin Content Management 
  As an admin, I want to approve or reject articles, show them, and go over users feedback and complaints 

  Scenario: Approve an article 
	  Given I am logged in as an admin id "admin123" password "1001"
	  And a new article was added id "a00" link "https://pmc.ncbi.nlm.nih.gov/articles/PMC7071223/"
    When I approve a pending article
    Then that article should be moved from the pending list to the articles list 

  Scenario: Reject an article 
	  Given I am logged in as an admin id "admin123" password "1001"
	  And an article with id "d00" link "https://time.com/7202466/best-supplements-to-take-vitamins/" pending
    When I reject a pending article 
    Then that article should be removed from the pending list

  Scenario: Explore articles 
	  Given I am logged in as an admin id "admin123" password "1001"
    When I want to see the available articles 
    Then all approved articles linked should be shown 

  Scenario: Read Clients Feedback 
	  Given I am logged in as an admin id "admin123" password "1001"
    When I want to see all clients feedback 
    Then all clients feedback should be shown
    
  Scenario: Read Clients Reviews  
	  Given I am logged in as an admin id "admin123" password "1001"
    When I want to see all clients programs reviews  
    Then all clients reviews should be shown
          
   

