Feature: Inspecting proposals
Scenario: Selecting a proposal on the list

    Given a list of proposals:
      | name    							| id |
      | Liberate snakes through the city    | 1  |
      | Another proposal					| 2	 |
      | Yet another proposal more 			| 3  |
    When I click on the hyperlink of the "Liberate snakes through the city" with id "1"
    Then I am redirected to its proposal view page
    And  I can see the "Content"
    But I cannot see other proposals