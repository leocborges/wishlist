package com.wishlist.wishlists;

import com.wishlist.exceptions.BadRequestException;
import com.wishlist.exceptions.Entity;
import com.wishlist.exceptions.NotFoundException;
import com.wishlist.products.ProductJson;
import com.wishlist.products.ProductService;
import java.util.Collection;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.UntypedExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * Wishlist service.
 */
@Service
public final class WishlistService {

    /**
     * Maximum number of products in a user's wishlist.
     */
    private static final int WISHLIST_MAX_SIZE = 20;

    /**
     * Wishlist dao.
     */
    private final WishlistRepository wishlists;

    /**
     * Product service.
     */
    private final ProductService products;

    /**
     * Ctor.
     * @param wishlists Wishlist dao.
     * @param products Product service.
     */
    public WishlistService(
        final WishlistRepository wishlists,
        final ProductService products
    ) {
        this.wishlists = wishlists;
        this.products = products;
    }

    /**
     * Adds a product to the user's wishlist.
     * @param pid Product id.
     * @param user User.
     * @return Resulting id.
     */
    public ObjectId add(final long pid, final String user) {
        this.checkLimit(user);
        this.checkProductExists(pid);
        return this.wishlists.insert(new Wishlist(pid, user)).getId();
    }

    /**
     * Checks if a product exists in a user's wishlist.
     * @param pid Product id.
     * @param user User.
     * @return True if product is in user's wishlist, false otherwise.
     */
    public boolean exists(final long pid, final String user) {
        return this.wishlists.exists(
            Example.of(
                new Wishlist(pid, user),
                UntypedExampleMatcher.matching().withIgnoreNullValues()
            )
        );
    }

    /**
     * Finds all products by a given {@code user}.
     * @param user User.
     * @return Products.
     */
    public Collection<ProductJson> findAllByUser(final String user) {
        final Collection<Long> products = this.wishlists.findAll(
            Example.of(
                new Wishlist(null, user),
                UntypedExampleMatcher.matching().withIgnoreNullValues()
            )
        ).stream().mapToLong(Wishlist::getPid).boxed().toList();
        return this.products.findAll(products);
    }

    /**
     * Removes a product from {@code user} wishlist.
     * @param pid Product id.
     * @param user User.
     */
    public void remove(final Long pid, final String user) {
        final Wishlist wishlist = this.wishlists.findOne(
            Example.of(
                new Wishlist(pid, user),
                UntypedExampleMatcher.matching().withIgnoreNullValues()
            )
        ).orElseThrow(() -> new NotFoundException(Entity.WISHLIST));
        this.wishlists.delete(wishlist);
    }

    private void checkLimit(final String user) {
        final long count = this.wishlists.count(
            Example.of(
                new Wishlist(null, user),
                UntypedExampleMatcher.matching().withIgnoreNullValues()
            )
        );
        if (count >= WishlistService.WISHLIST_MAX_SIZE) {
            throw new BadRequestException(
                "Max number of products reached (%d)."
                    .formatted(WishlistService.WISHLIST_MAX_SIZE)
            );
        }
    }

    private void checkProductExists(final long pid) {
        if (!this.products.exists(pid)) {
            throw new NotFoundException(Entity.PRODUCT);
        }
    }

}
