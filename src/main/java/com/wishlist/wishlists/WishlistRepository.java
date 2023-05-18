package com.wishlist.wishlists;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Wishlist dao.
 */
public interface WishlistRepository extends MongoRepository<Wishlist, String> {
}
