package steps.wishlist;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import mongo.MongoInstance;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Wishlist Mongo helper class.
 */
public final class WishlistMongo {

    private static final String COLLECTION = "wishlists";

    private final MongoInstance mongo;

    /**
     * Ctor.
     * @param mongo Mongo instance.
     */
    public WishlistMongo(final MongoInstance mongo) {
        this.mongo = mongo;
    }

    /**
     * Checks if a product exists in user's wishlist.
     * @param pid Product id.
     * @param user User.
     * @return True if product is in user's wishlist, false otherwise.
     */
    public boolean exists(final long pid, final String user) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("pid").is(pid));
        query.addCriteria(Criteria.where("user").is(user));
        return this.mongo.exists(query, WishlistMongo.COLLECTION);
    }

    /**
     * Inserts a product in user's wishlist.
     * @param pid Product id.
     * @param user User.
     */
    public void insert(Integer pid, String user) {
        this.mongo.insert(
            JsonNodeFactory.instance.objectNode()
                .put("pid", pid)
                .put("user", user)
                .toString(),
            WishlistMongo.COLLECTION
        );
    }

}
