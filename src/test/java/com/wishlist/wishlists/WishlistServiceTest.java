package com.wishlist.wishlists;

import com.wishlist.products.ProductJson;
import com.wishlist.products.ProductService;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Unit tests for {@link WishlistService}.
 */
public final class WishlistServiceTest {

    private AutoCloseable closeable;

    @Mock
    private WishlistRepository dao;

    @Mock
    private ProductService productService;

    /**
     * System under test.
     */
    private WishlistService sut;

    @Test
    public void findAllByUser() {
        final String user = "fixopqlxb";
        final ProductJson expected = new ProductJson(1L, "mouse", BigDecimal.ONE);
        Mockito
            .when(this.dao.findById(Mockito.anyString()))
            .thenReturn(Optional.of(new Wishlist(user)));
        Mockito
            .when(this.productService.findAll(Mockito.any()))
            .thenReturn(List.of(expected));
        final Collection<ProductJson> products = this.sut.findAllByUser(user);
        Mockito.verify(this.dao, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(this.productService, Mockito.times(1)).findAll(Mockito.anyCollection());
        MatcherAssert.assertThat(products.size(), Matchers.is(1));
        MatcherAssert.assertThat(
            products.stream().findFirst().orElseThrow(), Matchers.is(expected)
        );
    }

    @BeforeEach
    public void openMocks() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.sut = new WishlistService(this.dao, this.productService);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        this.closeable.close();
    }

}