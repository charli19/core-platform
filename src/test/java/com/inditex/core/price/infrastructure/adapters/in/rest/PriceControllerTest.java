package com.inditex.core.price.infrastructure.adapters.in.rest;

import com.inditex.core.AbstractApplicationTest;
import com.inditex.core.infrastructure.adapters.in.rest.dto.spec.PriceDto;
import com.inditex.core.price.application.ports.in.GetPricePort;
import com.inditex.core.price.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PriceControllerTest extends AbstractApplicationTest {

    @Mock
    private GetPricePort getPricePort;

    @InjectMocks
    private PriceController priceController;

    @Test
    void priceGet_shouldReturnPriceDto() {
        Long brandId = 1L;
        Long productId = 100L;
        OffsetDateTime applicationDate = OffsetDateTime.now();

        Price mockPrice = new Price(
                10L,
                brandId,
                productId,
                1L,
                OffsetDateTime.parse("2025-09-01T00:00:00+00:00"),
                OffsetDateTime.parse("2025-09-30T23:59:59+00:00"),
                new BigDecimal("99.99"),
                1,
                "EUR"
        );

        when(getPricePort.getPriorityPrice(brandId, productId, applicationDate)).thenReturn(mockPrice);

        ResponseEntity<PriceDto> response = priceController.getPrice(brandId, productId, applicationDate);

        assertNotNull(response.getBody());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
        assertEquals(mockPrice.startDate(), response.getBody().getStartDate());
        assertEquals(mockPrice.endDate(), response.getBody().getEndDate());
        assertEquals(mockPrice.price(), response.getBody().getPrice());
        assertEquals(mockPrice.priceList(), response.getBody().getPriceList());
    }

}