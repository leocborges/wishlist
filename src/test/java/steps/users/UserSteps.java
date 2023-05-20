package steps.users;

import http.RequestHeaders;
import io.cucumber.java8.En;
import mongo.MongoInstance;
import steps.wishlists.WishlistMongo;

/**
 * User step definitions.
 */
public class UserSteps implements En {

    /**
     * Ctor.
     * @param mongo Mongo instance.
     * @param headers Http headers instance.
     */
    public UserSteps(final MongoInstance mongo, final RequestHeaders headers) {
        And(
            "an authenticated user {string}",
            (String user) -> {
                new WishlistMongo(mongo).register(user);
                headers.headers().put("x-user", user);
            }
        );
    }
}
