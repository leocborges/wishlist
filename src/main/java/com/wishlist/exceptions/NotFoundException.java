package com.wishlist.exceptions;

/**
 *  Indicates an entity was not found (HTTP 404).
 */
public final class NotFoundException extends RuntimeException {

    /**
     * Entity.
     */
    private final Entity entity;

    /**
     * Ctor.
     * @param entity Entity.
     */
    public NotFoundException(Entity entity) {
        this.entity = entity;
    }

    /**
     * Entity.
     * @return Entity.
     */
    public Entity entity() {
        return this.entity;
    }

}
