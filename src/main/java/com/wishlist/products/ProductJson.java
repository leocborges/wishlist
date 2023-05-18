package com.wishlist.products;

import java.math.BigDecimal;

/**
 * A DTO of {@link Product}.
 * @param pid Product id.
 * @param name Product name.
 * @param price Product price.
 */
public record ProductJson(
    long pid,
    String name,
    BigDecimal price
) {
}
