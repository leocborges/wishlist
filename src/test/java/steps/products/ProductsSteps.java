package steps.products;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import java.math.BigDecimal;
import mongo.MongoInstance;

/**
 * Product step definitions.
 */
public final class ProductsSteps implements En {

    /**
     * Ctor.
     * @param mongo Mongo instance.
     */
    public ProductsSteps(final MongoInstance mongo) {
        Given(
            "a list of products",
            (DataTable table) -> table.asMaps().forEach(
                entry -> new ProductMongo(mongo).insert(
                    Long.parseLong(entry.get("id")),
                    entry.get("name"),
                    new BigDecimal(entry.get("price"))
                )
            )
        );
    }
}
