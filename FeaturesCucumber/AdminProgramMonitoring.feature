
Feature: Admin Program Monitoring
  As an admin i want to view a ranking of programs by popularity, generate reports on monthly revenue and attendacne, track active and completed programs

  Scenario: View statistics on the most popular programs and their status
  Given I am logged in as an admin id "admin123" password "1001"
  And there are programs with enrolled users
  When I ask for the programs report
  Then a ranking of programs by popularity and their current status should be displayed 

  Scenario: Generate report on revenue.
  Given I am logged in as an admin id "admin123" password "1001"
  When I ask for the monthly revenue report
  Then an estitame of this months revenue, should be created.

  Scenario: Generate reports on client progress
  Given I am logged in as an admin id "admin123" password "1001"
  When I ask for the monthly client progress report
  Then a report should be created.
  
  Scenario: Generate reports on client attendance
  Given I am logged in as an admin id "admin123" password "1001"
  When I ask for the client attendance report
  Then a report of attendance  should be created.
    