Feature: Play Song

  Scenario Outline:

   Given I am logged in ahd on SongPage <email>,<password>
    And User click on AllSongs button
    And Click on the song title <songName>
    And click on the play button

    Then Equalizer button is appeared
    Examples:
      | email               | password       | songName|
      | "galy.o@testpro.io" | "te$t$tudent1" | "Episode 2"|
      | "galy.o@testpro.io" | "te$t$tudent1" | "Pluto"|
      | "galy.o@testpro.io" | "te$t$tudent1" |"Scott Holmes Music"|