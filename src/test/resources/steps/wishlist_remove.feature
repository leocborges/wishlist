Feature: Wishlist remove

  Scenario: As a user, I can remove a product from my wishlist
    Given a list of products
      | id | name   | price   |
      | 1  | soap   | 1.2     |
      | 2  | bucket | 23.99   |
      | 3  | laptop | 1009.20 |
    And an authenticated user "fdsoiuxo"
    And product 2 is in "fdsoiuxo" user's wishlist
    And product 3 is in "fdsoiuxo" user's wishlist
    Then I call DELETE "/wishlists/2" and it returns status 204
    And I call GET "/wishlists" it returns status 200 with JSON body
    """
    [
      {
        "pid": 3,
        "name": "laptop",
        "price": 1009.20
      }
    ]
    """

  Scenario: As an anonymous user, I CANNOT remove a product from my wishlist

    anonymous = missing x-user in the request header

    Then I call DELETE "/wishlists/999" and it returns status 400 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Bad Request",
      "status": 400,
      "detail": "Required header 'x-user' is not present.",
      "instance": "/api/v1/wishlists/999"
    }
    """

  Scenario: As a user, I CANNOT remove a nonexistent item from my wishlist
    Given an authenticated user "vzcvnwio"
    Then I call DELETE "/wishlists/999" and it returns status 404 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Not Found",
      "status": 404,
      "detail": "Wishlist not found.",
      "instance": "/api/v1/wishlists/999"
    }
    """
