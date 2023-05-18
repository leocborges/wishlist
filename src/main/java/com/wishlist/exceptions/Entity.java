package com.wishlist.exceptions;

/**
 * Represents an entity in the app.
 */
public enum Entity {

    /**
     * Wishlist.
     */
    WISHLIST("Wishlist"),

    /**
     * Product.
     */
    PRODUCT("Product");

    /**
     * The readable, formatted entity exposed in response.
     */
    private final String readable;

    /**
     * Ctor.
     * @param readable The readable, formatted entity exposed in response.
     */
    Entity(final String readable) {
        this.readable = readable;
    }

    /**
     * The readable, formatted entity exposed in response.
     * @return Entity.
     */
    public String readable() {
        return this.readable;
    }

}
