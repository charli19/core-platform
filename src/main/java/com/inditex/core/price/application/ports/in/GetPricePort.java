package com.inditex.core.price.application.ports.in;

import com.inditex.core.price.domain.model.Price;

import java.time.OffsetDateTime;

public interface GetPricePort {
    Price getPriorityPrice(Long brandId, Long productId, OffsetDateTime applicationDate);
}
