package mongo;

import org.picocontainer.Disposable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Mongo local instance.
 */
public final class MongoInstance implements Disposable {

    private final MongoTemplate mongo;

    /**
     * Ctor.
     */
    public MongoInstance() {
        this.mongo = new MongoTemplate(
            new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/wishlist")
        );
        this.cleanup();
    }

    public void insert(String json, String collection) {
        this.mongo.insert(json, collection);
    }

    public boolean exists(final Query query, final String collection) {
        return this.mongo.exists(query, null, collection);
    }

    public void cleanup() {
        this.drop("products");
        this.drop("wishlists");
    }

    @Override
    public void dispose() {
    }

    private void drop(final String collection) {
        if (this.mongo.collectionExists(collection)) {
            this.mongo.dropCollection(collection);
        }
    }
}
