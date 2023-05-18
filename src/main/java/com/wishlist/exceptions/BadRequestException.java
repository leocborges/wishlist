package com.wishlist.exceptions;

/**
 * Indicates a bad request (HTTP 400).
 */
public final class BadRequestException extends RuntimeException {

    /**
     * Ctor.
     * @param message Cause.
     */
    public BadRequestException(final String message) {
        super(message);
    }

}
