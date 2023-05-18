package com.wishlist.products;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Product dao.
 */
public interface ProductRepository extends MongoRepository<Product, Long> {
}
