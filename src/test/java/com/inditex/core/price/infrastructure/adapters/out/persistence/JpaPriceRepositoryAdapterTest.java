package com.inditex.core.price.infrastructure.adapters.out.persistence;

import com.inditex.core.AbstractApplicationTest;
import com.inditex.core.price.infrastructure.adapters.out.persistence.entity.PriceEntity;
import com.inditex.core.price.infrastructure.adapters.out.persistence.repository.JpaPriceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaPriceRepositoryAdapterTest extends AbstractApplicationTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @InjectMocks
    private JpaPriceRepositoryAdapter priceRepositoryAdapter;

    @Test
    void testFindPriorityPrice_Found() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime applicationDate = LocalDateTime.now();

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(10L);
        priceEntity.setBrandId(brandId);
        priceEntity.setProductId(productId);

        when(jpaPriceRepository.findByBrandAndProductAndApplicationDate(brandId, productId, applicationDate))
                .thenReturn(Optional.of(priceEntity));

        Optional<PriceEntity> result = priceRepositoryAdapter.findPriorityPrice(brandId, productId, applicationDate);

        assertTrue(result.isPresent());
        assertEquals(priceEntity, result.get());
        verify(jpaPriceRepository, times(1))
                .findByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    }

    @Test
    void testFindPriorityPrice_NotFound() {
        Long brandId = 1L;
        Long productId = 100L;
        LocalDateTime applicationDate = LocalDateTime.now();

        when(jpaPriceRepository.findByBrandAndProductAndApplicationDate(brandId, productId, applicationDate))
                .thenReturn(Optional.empty());

        Optional<PriceEntity> result = priceRepositoryAdapter.findPriorityPrice(brandId, productId, applicationDate);

        assertFalse(result.isPresent());
        verify(jpaPriceRepository, times(1))
                .findByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    }

}
