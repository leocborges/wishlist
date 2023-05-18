package steps.wishlist;

import io.cucumber.java8.En;
import mongo.MongoInstance;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

/**
 * Wishlist step definitions.
 */
public final class WishlistSteps implements En {

    /**
     * Ctor.
     * @param mongo Mongo instance.
     */
    public WishlistSteps(final MongoInstance mongo) {
        Given(
            "product {int} is in {string} user's wishlist",
            (Integer pid, String user) -> new WishlistMongo(mongo).insert(pid, user)
        );
        Then(
            "product {int} is stored in MongoDB's wishlist collection for user {string}",
            (Integer pid, String user) -> {
                final boolean exists = new WishlistMongo(mongo).exists(pid, user);
                MatcherAssert.assertThat(exists, Matchers.is(true));
            }
        );
    }
}
