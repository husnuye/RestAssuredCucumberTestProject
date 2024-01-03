Feature: I can create a hotel reservation as a user.
  Scenario: User can create and delete a hotel reservation.
    Given User creates a new hotel reservation
    And User provides information required for hotel reservation
    When User creates hotel reservation
    Then The reservation was created successfully
    And User cancels the reservation created
