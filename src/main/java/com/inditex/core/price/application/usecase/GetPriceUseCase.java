package com.inditex.core.price.application.usecase;


import com.inditex.core.price.application.ports.in.GetPricePort;
import com.inditex.core.price.application.ports.out.PriceRepositoryPort;
import com.inditex.core.price.domain.exception.PriceNotFoundException;
import com.inditex.core.price.domain.model.Price;
import com.inditex.core.price.infrastructure.adapters.out.persistence.entity.PriceEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPriceUseCase implements GetPricePort {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price getPriorityPrice(
            Long brandId,
            Long productId,
            LocalDateTime applicationDate
    ) {
        PriceEntity priceEntity = priceRepositoryPort.findPriorityPrice(
                brandId,
                productId,
                applicationDate
        ).orElseThrow(PriceNotFoundException::new);

        return new Price(
                priceEntity.getId(),
                priceEntity.getBrandId(),
                priceEntity.getProductId(),
                priceEntity.getPriceList(),
                priceEntity.getStartDate(),
                priceEntity.getEndDate(),
                priceEntity.getPrice(),
                priceEntity.getPriority(),
                priceEntity.getCurrency()
        );
    }
}
