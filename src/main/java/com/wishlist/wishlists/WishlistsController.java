package com.wishlist.wishlists;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose a wishlist API.
 */
@RestController
@RequestMapping("/wishlists")
public final class WishlistsController {

    private static final String USER_HEADER_KEY = "x-user";

    private final WishlistService wishlists;

    public WishlistsController(final WishlistService wishlists) {
        this.wishlists = wishlists;
    }

    @Operation(summary = "Lista produtos adicionados na wishlist")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> list(
        @RequestHeader(WishlistsController.USER_HEADER_KEY) final String user
    ) {
        return ResponseEntity.ok(this.wishlists.findAllByUser(user));
    }

    @Operation(summary = "Adiciona um produto na wishlist")
    @PutMapping("/{pid}")
    @PostMapping("/{pid}")
    public ResponseEntity<?> add(
        @PathVariable("pid") final Long pid,
        @RequestHeader(WishlistsController.USER_HEADER_KEY) final String user
    ) {
        this.wishlists.add(pid, user);
        return ResponseEntity.noContent().build();
    }

    /**
     * Fetch a product in user's wishlist.
     * @param pid Product id.
     * @param user User.
     * @return json response.
     */
    @Operation(summary = "Verifica se um produto existe na wishlist")
    @GetMapping(value = "/{pid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fetch(
        @PathVariable("pid") final Long pid,
        @RequestHeader(WishlistsController.USER_HEADER_KEY) final String user
    ) {
        final boolean exists = this.wishlists.exists(pid, user);
        return ResponseEntity.ok(
            JsonNodeFactory.instance.objectNode().put("exists", exists)
        );
    }

    @Operation(summary = "Remove um produto da wishlist")
    @DeleteMapping("/{pid}")
    public ResponseEntity<?> remove(
        @PathVariable("pid") final Long pid,
        @RequestHeader(WishlistsController.USER_HEADER_KEY) final String user
    ) {
        this.wishlists.remove(pid, user);
        return ResponseEntity.noContent().build();
    }

}
