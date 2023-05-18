package com.wishlist.products;

import java.util.Collection;
import org.springframework.stereotype.Service;

/**
 * Product service.
 */
@Service
public final class ProductService {

    private final ProductRepository products;

    public ProductService(final ProductRepository products) {
        this.products = products;
    }

    public boolean exists(final long pid) {
        return this.products.existsById(pid);
    }

    /**
     * Finds all products.
     * @param pids Product ids to look for.
     * @return Products.
     */
    public Collection<ProductJson> findAll(final Collection<Long> pids) {
        return this.products.findAllById(pids).stream().map(
            product -> new ProductJson(
                product.getId(), product.getName(), product.getPrice()
            )
        ).toList();
    }
}
