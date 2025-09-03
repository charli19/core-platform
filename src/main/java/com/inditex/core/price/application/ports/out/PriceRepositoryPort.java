package com.inditex.core.price.application.ports.out;


import com.inditex.core.price.infrastructure.adapters.out.persistence.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<PriceEntity> findPriorityPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}
