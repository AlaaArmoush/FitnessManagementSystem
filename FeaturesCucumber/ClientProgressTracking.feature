 Feature: Progress Tracking
  As a client, I want to know my BMI, how much weight I should gain or lose, 
  and view my achievements and badges.

  Scenario Outline: Calculate BMI
    Given I am logged in as a client with ID "<client_id>" and password "<password>"
    When I enter my weight "<weight>" and height "<height>"
    Then I should get a BMI "<bmi>", "<status>" and be told to "<action>"

    Examples:
      | client_id   | password | weight | height | bmi  | status       | action        |
      | client123   | 2002     | 90     | 170    | 31   | Overweight   | Lose: 17 Kg   |
      | client456   | 3003     | 50     | 170    | 17   | Underweight  | Gain: 22 Kg   |
      
   Scenario: Earn a badges
    Given Im logged in as a client id "client123" password "2002" 
    When I finish my second program 
    Then I should gain the "RISING_STAR" badge 
    
		Scenario: See my badges 
    Given Im logged in as a client id "client123" password "2002" 
    When i try to see my badges
    Then my badges should be showcased
   		