Feature: Wishlist list

  Scenario: As a user, I can query for all products within the wishlist
    Given a list of products
      | id | name       | price   |
      | 1  | soap       | 1.2     |
      | 2  | bucket     | 23.99   |
      | 3  | laptop     | 1009.20 |
      | 4  | light bulb | 5.15    |
      | 5  | mouse      | 200.00  |
    When an authenticated user "zasdfoxq"
    And product 2 is in "zasdfoxq" user's wishlist
    And product 4 is in "zasdfoxq" user's wishlist
    And product 5 is in "zasdfoxq" user's wishlist
    Then I call GET "/wishlists" it returns status 200 with JSON body
    """
    [
      {
        "pid": 2,
        "name": "bucket",
        "price": 23.99
      },
      {
        "pid": 4,
        "name": "light bulb",
        "price": 5.15
      },
      {
        "pid": 5,
        "name": "mouse",
        "price": 200.00
      }
    ]
    """

  Scenario: As a user, I get an empty array if I don't have products in my wishlist
    When an authenticated user "czqowiuz"
    Then I call GET "/wishlists" it returns status 200 with JSON body
    """
    []
    """

  Scenario: As an anonymous user, I CANNOT list products of my wishlist

    anonymous = missing x-user in the request header

    Then I call GET "/wishlists" it returns status 400 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Bad Request",
      "status": 400,
      "detail": "Required header 'x-user' is not present.",
      "instance": "/api/v1/wishlists"
    }
    """
