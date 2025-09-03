package com.inditex.core.price.infrastructure.adapters.in.rest;

import com.inditex.core.price.application.ports.in.GetPricePort;
import com.inditex.core.price.domain.model.Price;
import com.inditex.core.price.infrastructure.adapters.in.rest.dto.PriceDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

    private final GetPricePort getPricePort;

    public PriceController(GetPricePort getPricePort) {
        this.getPricePort = getPricePort;
    }

    @GetMapping
    @Validated
    @Operation(summary = "Obtain priority price by brand, product and application date")
    public PriceDto getPrice(
            @RequestParam("brandId") @NotNull @Positive Long brandId,
            @RequestParam("productId") @NotNull @Positive Long productId,
            @RequestParam("applicationDate") @NotNull LocalDateTime applicationDate
    ) {
        final Price price = getPricePort.getPriorityPrice(brandId, productId, applicationDate);
        return new PriceDto(
                price.brandId(),
                price.productId(),
                price.startDate(),
                price.endDate(),
                price.price(),
                price.priceList()
        );
    }

}
