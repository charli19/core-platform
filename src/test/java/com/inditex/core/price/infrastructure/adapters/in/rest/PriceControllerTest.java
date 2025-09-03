package com.inditex.core.price.infrastructure.adapters.in.rest;

import com.inditex.core.AbstractApplicationTest;
import com.inditex.core.price.application.ports.in.GetPricePort;
import com.inditex.core.price.domain.model.Price;
import com.inditex.core.price.infrastructure.adapters.in.rest.dto.PriceDto;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PriceControllerTest extends AbstractApplicationTest {

    @Mock
    private GetPricePort getPricePort;

    @InjectMocks
    private PriceController priceController;

    @Test
    void testGetPrice_Success() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime applicationDate = LocalDateTime.now();

        Price mockPrice = new Price(
                10L,
                brandId,
                productId,
                1L,
                applicationDate.minusDays(1),
                applicationDate.plusDays(1),
                99.99,
                1,
                "EUR"
        );

        when(getPricePort.getPriorityPrice(brandId, productId, applicationDate))
                .thenReturn(mockPrice);

        PriceDto result = priceController.getPrice(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(brandId, result.brandId());
        assertEquals(productId, result.productId());
        assertEquals(mockPrice.price(), result.price());
        assertEquals(mockPrice.priceList(), result.priceList());
        assertEquals(mockPrice.startDate(), result.startDate());
        assertEquals(mockPrice.endDate(), result.endDate());

        verify(getPricePort, times(1))
                .getPriorityPrice(brandId, productId, applicationDate);
    }

}
