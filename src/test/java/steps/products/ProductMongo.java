package steps.products;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import java.math.BigDecimal;
import mongo.MongoInstance;

/**
 * Product Mongo helper class.
 */
public class ProductMongo {

    private final MongoInstance mongo;

    public ProductMongo(final MongoInstance mongo) {
        this.mongo = mongo;
    }

    /**
     * Inserts a new product.
     * @param id Product id.
     * @param name Product name.
     * @param price Product price.
     */
    public void insert(final long id, final String name, final BigDecimal price) {
        this.mongo.insert(
            JsonNodeFactory.instance.objectNode()
                .put("_id", id)
                .put("name", name)
                .put("price", price)
                .toString(),
            "products"
        );
    }

}
