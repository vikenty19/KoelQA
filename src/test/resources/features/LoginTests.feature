Feature: login tests
  Background:
   Given I open browser
    When I open login page
    @login
  Scenario Outline: login success

    And  I enter valid email <email>
    And I enter valid password <password>
    And  I click Submit
    Then I am logged in
   Examples:
    |email                  |   password|
    |"galy.o@testpro.io"|"te$t$tudent1"|

@login
  Scenario Outline:LoginWrongEmail

    And I enter wrong email <email>
    And I enter valid password <password>
    And I click Submit
    Then I am not logged in
    Examples:
      |   password|email|
      |"MEGAdelta123@"|"vicplach13@gmail.com"|


