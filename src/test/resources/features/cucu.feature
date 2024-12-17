Feature: Play Song
  Scenario:
    Given I open browser
    When I open login page

When i enter valid email "galy.o@testpro.io"
And I enter valid password "te$t$tudent1"
And  I click Submit
# And I am logged in
#Examples:
#|email                  |   password|
#|"galy.o@testpro.io"|"te$t$tudent1"|
    And User click on AllSongs button
    And Click on the song title <SongName>
    And click on the play button
    Then Song is reproduced
    And Equalizer button is appeared
