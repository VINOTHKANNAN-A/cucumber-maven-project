Feature: Posts API - Demo (Cucumber + Rest Assured)
 
  Scenario: GET a post and validate response
    Given API base URI is "https://jsonplaceholder.typicode.com"
    When I send GET request to "/posts/1"
    Then response status should be 200
    And response header "Content-Type" should contain "application/json"
    And response json path "id" should be "1"
    And response json path "userId" should be "1"
 
  Scenario: POST create a post and validate response
    Given API base URI is "https://jsonplaceholder.typicode.com"
    When I send POST request to "/posts" with json body:
      """
      {
        "title": "foo",
        "body": "bar",
        "userId": 1
      }
      
      """
    Then response status should be 201
    And response json path "title" should be "foo"
    And response json path "body" should be "bar"
    And response json path "userId" should be "1"