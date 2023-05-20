package mongo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Mongo local instance.
 */
public final class MongoInstance {

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

    public <T> T findById(final String entityId, Class<T> entityClass) {
        return this.mongo.findById(entityId, entityClass);
    }

    public <T> void upsert(Query query, Update update, String collection) {
        this.mongo.upsert(query, update, collection);
    }

    public <T> void insert(T json, String collection) {
        this.mongo.insert(json, collection);
    }

    public void cleanup() {
        this.drop("products");
        this.drop("wishlists");
    }

    private void drop(final String collection) {
        if (this.mongo.collectionExists(collection)) {
            this.mongo.dropCollection(collection);
        }
    }

}
