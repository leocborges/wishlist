package com.wishlist.wishlists;

import com.wishlist.exceptions.BadRequestException;
import com.wishlist.exceptions.Entity;
import com.wishlist.exceptions.NotFoundException;
import com.wishlist.products.ProductJson;
import com.wishlist.products.ProductService;
import java.util.Collection;
import java.util.Optional;
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
     */
    public void add(final long pid, final String user) {
        this.checkLimit(user);
        this.checkProductExists(pid);
        final Wishlist wishlist = this.findOpt(user).orElse(new Wishlist(user));
        wishlist.getProducts().add(pid);
        this.wishlists.save(wishlist);
    }

    /**
     * Checks if a product exists in a user's wishlist.
     * @param pid Product id.
     * @param user User.
     * @return True if product is in user's wishlist, false otherwise.
     */
    public boolean exists(final long pid, final String user) {
        return this.find(user).getProducts().contains(pid);
    }

    /**
     * Finds all products by a given {@code user}.
     * @param user User.
     * @return Products.
     */
    public Collection<ProductJson> findAllByUser(final String user) {
        return this.products.findAll(
            this.find(user).getProducts().stream().toList()
        );
    }

    /**
     * Removes a product from {@code user} wishlist.
     * @param pid Product id.
     * @param user User.
     */
    public void remove(final Long pid, final String user) {
        final Wishlist wishlist = this.find(user);
        if (!wishlist.getProducts().remove(pid)) {
            throw new NotFoundException(Entity.PRODUCT);
        }
        this.wishlists.save(wishlist);
    }

    private Optional<Wishlist> findOpt(final String user) {
        return this.wishlists.findById(user);
    }

    private Wishlist find(final String user) {
        return this.findOpt(user)
            .orElseThrow(() -> new NotFoundException(Entity.WISHLIST));
    }

    private void checkLimit(final String user) {
        this.findOpt(user).ifPresent(
            wishlist -> {
                final long count = wishlist.getProducts().size();
                if (count >= WishlistService.WISHLIST_MAX_SIZE) {
                    throw new BadRequestException(
                        "Max number of products reached (%d)."
                            .formatted(WishlistService.WISHLIST_MAX_SIZE)
                    );
                }
            }
        );
    }

    private void checkProductExists(final long pid) {
        if (!this.products.exists(pid)) {
            throw new NotFoundException(Entity.PRODUCT);
        }
    }

}
