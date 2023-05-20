package steps.wishlists;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.wishlist.wishlists.Wishlist;
import mongo.MongoInstance;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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
     * Finds a user's wishlist.
     * @param user User.
     * @return Wishlist.
     */
    public Wishlist find(final String user) {
        return this.mongo.findById(user, Wishlist.class);
    }

    /**
     * Inserts a product in user's wishlist.
     * @param pid Product id.
     * @param user User.
     */
    public void insert(Long pid, String user) {
        final Query query = new Query().addCriteria(Criteria.where("_id").is(user));
        final Update update = new Update().addToSet("products", pid);
        this.mongo.upsert(query, update, WishlistMongo.COLLECTION);
    }

    /**
     * Register a new user in a wishlist without any product.
     * @param user User.
     */
    public void register(final String user) {
        this.mongo.insert(JsonNodeFactory.instance.objectNode()
                .put("_id", user)
                .toString(),
            WishlistMongo.COLLECTION);
    }
}
