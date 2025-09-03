package com.inditex.core.price.domain.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String MESSAGE = "message";
    private static final String ERROR = "error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllExceptions(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        log.error(ex);
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, "Internal server error");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlePriceNotFound(PriceNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        log.error(ex);
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleHandlerMethodValidation(HandlerMethodValidationException ex) {
        Map<String, String> errors = ex.getParameterValidationResults().stream()
                .flatMap(paramResult -> paramResult.getResolvableErrors().stream()
                        .map(error -> Map.entry(
                                Objects.requireNonNull(paramResult.getMethodParameter().getParameterName()),
                                Objects.requireNonNull(error.getDefaultMessage())
                        ))
                )
                .collect(HashMap::new,
                        (map, entry) -> map.merge(entry.getKey(), entry.getValue(), (oldVal, newVal) -> oldVal + "; " + newVal),
                        HashMap::putAll);

        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, "Validation failed");
        body.put(ERROR, errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getName(), buildTypeMismatchMessage(ex));

        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, "Parameter type mismatch");
        body.put(ERROR, errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    private String buildTypeMismatchMessage(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        Object invalidValue = ex.getValue();
        Class<?> requiredType = ex.getRequiredType();

        if (requiredType != null) {
            return String.format(
                    "Invalid value '%s' for parameter '%s'. Expected type: %s",
                    invalidValue, paramName, requiredType.getSimpleName()
            );
        } else {
            return String.format(
                    "Invalid value '%s' for parameter '%s'.",
                    invalidValue, paramName
            );
        }
    }


}
