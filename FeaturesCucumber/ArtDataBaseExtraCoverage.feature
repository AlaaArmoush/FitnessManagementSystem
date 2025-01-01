Feature: Articles database operations

  Scenario: Admin approves an article
    Given an article with ID "1" and title "Article 1" is pending approval
    When the admin approves the article with ID "1"
    Then the article should be moved to the approved list

  Scenario: Admin rejects an article
    Given an article with ID "2" and title "Article 2" is pending approval
    When the admin rejects the article with ID "2"
    Then the article should be removed from the pending list

  Scenario: Admin opens a URL for an approved article
    Given an article with ID "3" and title "Article 3" is approved with a valid URL
    When a user opens the URL for the article with ID "3"
    Then the URL should open in the browser

  Scenario: User attempts to open a URL for a non-existing article
    Given an article with ID "4" does not exist
    When a user attempts to open the URL for the article with ID "4"
    Then an error message "Article not found" should be shown

  Scenario: Admin retrieves an existing article
    Given an article with ID "5" and title "Article 5" is approved with a valid URL
    When the admin retrieves the article with ID "5"
    Then the article should be returned

  Scenario: Admin checks if an article exists in the pending list
    Given an article with ID "6" and title "Article 6" is pending approval
    When the admin checks if the article with ID "6" exists in the pending list
    Then the operation should return true for the pending list

  Scenario: Admin checks if an article exists in the approved list
    Given an article with ID "7" and title "Article 7" is approved with a valid URL
    When the admin checks if the article with ID "7" exists in the approved list
    Then the operation should return true for the approved list
