package com.inditex.core.price.application.usecase;

import com.inditex.core.AbstractApplicationTest;
import com.inditex.core.price.application.ports.out.PriceRepositoryPort;
import com.inditex.core.price.domain.exception.PriceNotFoundException;
import com.inditex.core.price.domain.model.Price;
import com.inditex.core.price.infrastructure.adapters.out.persistence.entity.PriceEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPriceUseCaseTest extends AbstractApplicationTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @Test
    void testGetPriorityPrice_Success() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime applicationDate = LocalDateTime.now();

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(10L);
        priceEntity.setBrandId(brandId);
        priceEntity.setProductId(productId);
        priceEntity.setPriceList(1L);
        priceEntity.setStartDate(applicationDate.minusDays(1));
        priceEntity.setEndDate(applicationDate.plusDays(1));
        priceEntity.setPrice(BigDecimal.valueOf(99.99));
        priceEntity.setPriority(1);
        priceEntity.setCurrency("EUR");

        when(priceRepositoryPort.findPriorityPrice(brandId, productId, applicationDate))
                .thenReturn(Optional.of(priceEntity));

        Price result = getPriceUseCase.getPriorityPrice(brandId, productId, applicationDate);

        assertNotNull(result);
        assertEquals(priceEntity.getId(), result.id());
        assertEquals(priceEntity.getPrice(), result.price());
        assertEquals(priceEntity.getCurrency(), result.currency());
        assertEquals(priceEntity.getStartDate(), result.startDate());
        assertEquals(priceEntity.getEndDate(), result.endDate());
        assertEquals(priceEntity.getPriority(), result.priority());
        assertEquals(priceEntity.getBrandId(), result.brandId());
        assertEquals(priceEntity.getProductId(), result.productId());
        assertEquals(priceEntity.getPriceList(), result.priceList());

        verify(priceRepositoryPort, times(1)).findPriorityPrice(brandId, productId, applicationDate);
    }

    @Test
    void testGetPriorityPrice_NotFound() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(priceRepositoryPort.findPriorityPrice(brandId, productId, applicationDate))
                .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () ->
                getPriceUseCase.getPriorityPrice(brandId, productId, applicationDate));

        verify(priceRepositoryPort, times(1)).findPriorityPrice(brandId, productId, applicationDate);
    }
}