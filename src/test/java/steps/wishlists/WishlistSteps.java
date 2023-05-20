package steps.wishlists;

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
            "product {long} is in {string} user's wishlist",
            (Long pid, String user) -> new WishlistMongo(mongo).insert(pid, user)
        );
        Then(
            "product {long} is stored in MongoDB's wishlist collection for user {string}",
            (Long pid, String user) -> {
                final boolean contains = new WishlistMongo(mongo)
                    .find(user)
                    .getProducts()
                    .contains(pid);
                MatcherAssert.assertThat(contains, Matchers.is(true));
            }
        );
    }
}
