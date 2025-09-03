package com.inditex.core.price.domain.model;

import java.time.LocalDateTime;

public record Price(
        Long id,
        Long brandId,
        Long productId,
        Long priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Double price,
        Integer priority,
        String currency
) {
}
