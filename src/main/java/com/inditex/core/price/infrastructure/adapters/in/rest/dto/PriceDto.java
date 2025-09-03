package com.inditex.core.price.infrastructure.adapters.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDto(
        Long brandId,
        Long productId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        BigDecimal price,
        Long priceList
) {
}
