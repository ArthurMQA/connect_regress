Feature: As user I want to log into the ADX Connect service and start the conference

  Scenario: I want to log into ADX Connect service and start the conference
    Given I go to the ADX login page
    And I see login page
    When I type my credentials and click Login button
    And I log into the ADX Sales service
    And I move on the ADX Connect service
    And I see ADX Connect dashboard
    And I move to My meeting spaces page
    And I start new Conference
    Then I see that new Conference room is successfully started
    And I can chat in the Conference room
    And I can to upload PDF files in the Conference room
    And I can end the Conference
