package com.wishlist.wishlists;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a wishlist stored in db.
 */
@Document("wishlists")
public final class Wishlist {

    @Id
    private ObjectId id;
    private Long pid;
    private String user;

    public Wishlist(final Long pid, final String user) {
        this.user = user;
        this.pid = pid;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(final Long pid) {
        this.pid = pid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

}
