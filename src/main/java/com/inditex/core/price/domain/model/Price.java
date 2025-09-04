package com.inditex.core.price.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Price(
        Long id,
        Long brandId,
        Long productId,
        Long priceList,
        OffsetDateTime startDate,
        OffsetDateTime endDate,
        BigDecimal price,
        Integer priority,
        String currency
) {
}
