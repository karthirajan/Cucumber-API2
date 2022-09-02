#Author: your.email@your.domain.com
Feature: Google API

  Scenario: Add google Location
    Given I want to add payload
    When user submit "POST" api
    Then user validate the status code is 200
