Feature: book search

  Scenario: Display an error for an empty search
    Given the user is on the book search page
    When they click the search button without entering any data
    Then they should see a message saying "No books found"