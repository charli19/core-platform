package com.inditex.core.price.infrastructure.adapters.out.persistence;

import com.inditex.core.price.application.ports.out.PriceRepositoryPort;
import com.inditex.core.price.infrastructure.adapters.out.persistence.entity.PriceEntity;
import com.inditex.core.price.infrastructure.adapters.out.persistence.repository.JpaPriceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class JpaPriceRepositoryAdapter implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;

    public JpaPriceRepositoryAdapter(JpaPriceRepository jpaPriceRepository) {
        this.jpaPriceRepository = jpaPriceRepository;
    }

    @Override
    public Optional<PriceEntity> findPriorityPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findByBrandAndProductAndApplicationDate(brandId, productId, applicationDate);
    }

}
