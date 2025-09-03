package com.inditex.core.price.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
        Long id,
        Long brandId,
        Long productId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        Integer priority,
        String currency
) {
}
