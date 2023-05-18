Feature: Wishlist add

  Scenario: As a user, I can add a product to the wishlist
    Given a list of products
      | id | name   | price   |
      | 1  | soap   | 1.2     |
      | 2  | basket | 23.99   |
      | 3  | laptop | 1009.20 |
    And an authenticated user "poizxcvi"
    When I call PUT "/wishlists/2" it returns status 204
    Then product 2 is stored in MongoDB's wishlist collection for user "poizxcvi"

  Scenario: As a user, I can add maximum of 20 products to the wishlist
    Given a list of products
      | id | name   | price   |
      | 1  | soap   | 1.2     |
      | 2  | basket | 23.99   |
      | 3  | laptop | 1009.20 |
      | 4  | laptop | 1009.20 |
      | 5  | laptop | 1009.20 |
      | 6  | laptop | 1009.20 |
      | 7  | laptop | 1009.20 |
      | 8  | laptop | 1009.20 |
      | 9  | laptop | 1009.20 |
      | 10 | laptop | 1009.20 |
      | 11 | soap   | 1.2     |
      | 12 | basket | 23.99   |
      | 13 | laptop | 1009.20 |
      | 14 | laptop | 1009.20 |
      | 15 | laptop | 1009.20 |
      | 16 | laptop | 1009.20 |
      | 17 | laptop | 1009.20 |
      | 18 | laptop | 1009.20 |
      | 19 | laptop | 1009.20 |
      | 20 | laptop | 1009.20 |
      | 21 | laptop | 1009.20 |
    And an authenticated user "zxcvoiqx"
    And product 1 is in "zxcvoiqx" user's wishlist
    And product 2 is in "zxcvoiqx" user's wishlist
    And product 3 is in "zxcvoiqx" user's wishlist
    And product 4 is in "zxcvoiqx" user's wishlist
    And product 5 is in "zxcvoiqx" user's wishlist
    And product 6 is in "zxcvoiqx" user's wishlist
    And product 7 is in "zxcvoiqx" user's wishlist
    And product 8 is in "zxcvoiqx" user's wishlist
    And product 9 is in "zxcvoiqx" user's wishlist
    And product 10 is in "zxcvoiqx" user's wishlist
    And product 11 is in "zxcvoiqx" user's wishlist
    And product 12 is in "zxcvoiqx" user's wishlist
    And product 13 is in "zxcvoiqx" user's wishlist
    And product 14 is in "zxcvoiqx" user's wishlist
    And product 15 is in "zxcvoiqx" user's wishlist
    And product 16 is in "zxcvoiqx" user's wishlist
    And product 17 is in "zxcvoiqx" user's wishlist
    And product 18 is in "zxcvoiqx" user's wishlist
    And product 19 is in "zxcvoiqx" user's wishlist
    And product 20 is in "zxcvoiqx" user's wishlist
    Then I call PUT "/wishlists/21" it returns status 400 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Bad Request",
      "status": 400,
      "detail": "Max number of products reached (20).",
      "instance": "/api/v1/wishlists/21"
    }
    """

  Scenario: As an anonymous user, I CANNOT add a product to the wishlist

    anonymous = missing x-user in the request header

    Then I call PUT "/wishlists/1" it returns status 400 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Bad Request",
      "status": 400,
      "detail": "Required header 'x-user' is not present.",
      "instance": "/api/v1/wishlists/1"
    }
    """

  Scenario: As a user, I CANNOT add an nonexistent product to the wishlist
    Given an authenticated user "fxqcqapc"
    Then I call PUT "/wishlists/999" it returns status 404 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Not Found",
      "status": 404,
      "detail": "Product not found.",
      "instance": "/api/v1/wishlists/999"
    }
    """
