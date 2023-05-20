package com.wishlist.wishlists;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a wishlist stored in db.
 */
@Document("wishlists")
public final class Wishlist {

    @Id
    private String user;
    private List<Long> products = new ArrayList<>();

    public Wishlist(final String user) {
        this.user = user;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public List<Long> getProducts() {
        return this.products;
    }

    public void setProducts(final List<Long> products) {
        this.products = products;
    }

}
