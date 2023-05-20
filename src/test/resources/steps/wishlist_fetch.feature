Feature: Wishlist fetch

  Scenario: As a user, I can check a product within the wishlist
    Given a list of products
      | id    | name       | price   |
      | 38219 | smart tv   | 4800.99 |
      | 78924 | smartphone | 3680.00 |
    And an authenticated user "basdesgw"
    And product 38219 is in "basdesgw" user's wishlist
    Then I call GET "/wishlists/38219" it returns status 200 with JSON body
    """
    {
      "exists": true
    }
    """
    * I call GET "/wishlists/78924" it returns status 200 with JSON body
    """
    {
      "exists": false
    }
    """

  Scenario: As an anonymous user, I CANNOT check if a product within wishlist

    anonymous = missing x-user in the request header

    Given a list of products
      | id    | name     | price |
      | 83196 | mousepad | 50.99  |
    And product 38219 is in "basdesgw" user's wishlist
    Then I call GET "/wishlists/83196" it returns status 400 with JSON body
    """
    {
      "type": "about:blank",
      "title": "Bad Request",
      "status": 400,
      "detail": "Required header 'x-user' is not present.",
      "instance": "/api/v1/wishlists/83196"
    }
    """

  Scenario: As a user, a nonexistent product within the wishlist returns false
    Given an authenticated user "basdesgw"
    Then I call GET "/wishlists/999" it returns status 200 with JSON body
    """
    {
      "exists": false
    }
    """
