package com.inditex.core.price.infrastructure.adapters.in.rest;


import com.inditex.core.infrastructure.adapters.in.rest.spec.PricesApi;
import com.inditex.core.infrastructure.adapters.in.rest.dto.spec.PriceDto;
import com.inditex.core.price.application.ports.in.GetPricePort;
import com.inditex.core.price.domain.model.Price;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PriceController implements PricesApi {

    private final GetPricePort getPricePort;

    public PriceController(GetPricePort getPricePort) {
        this.getPricePort = getPricePort;
    }

    @Override
    public ResponseEntity<PriceDto> getPrices(Long brandId, Long productId, OffsetDateTime applicationDate) {
        final Price price = getPricePort.getPriorityPrice(brandId, productId, applicationDate);

        PriceDto priceDto = new PriceDto();
        priceDto.setBrandId(price.brandId());
        priceDto.setProductId(price.productId());
        priceDto.setStartDate(price.startDate());
        priceDto.setEndDate(price.endDate());
        priceDto.setPrice(price.price());
        priceDto.setPriceList(price.priceList());

        return ResponseEntity.ok(priceDto);
    }
}
