Feature: Book Search

  Scenario: Search for a book by title
    Given the user is on the book search page
    When they enter "T-SQL Fundamentals" in the title field
    And they click the SEARCH button
    Then they should see a list of books with titles matching "T-SQL Fundamentals"
