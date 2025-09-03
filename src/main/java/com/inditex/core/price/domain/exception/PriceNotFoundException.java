package com.inditex.core.price.domain.exception;

public final class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException() {
        super("Price not found");
    }

}
