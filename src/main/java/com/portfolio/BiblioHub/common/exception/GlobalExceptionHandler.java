package com.portfolio.BiblioHub.common.exception;

import com.portfolio.BiblioHub.common.builder.ResponseBuilder;
import com.portfolio.BiblioHub.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles exceptions globally and provides a consistent API response structure.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseBuilder responseBuilder;

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleRecordNotFoundException(RecordNotFoundException ex) {
        log.warn("Record not found: {}", ex.getMessage());
        return responseBuilder.error(HttpStatus.NOT_FOUND, "Resource not found", List.of(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicateResourceException(DuplicateResourceException ex) {
        log.warn("Duplicate resource: {}", ex.getMessage());
        return responseBuilder.error(HttpStatus.CONFLICT, "Duplicate resource", List.of(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());

        log.debug("Validation failed: {}", errors);
        return responseBuilder.error(HttpStatus.BAD_REQUEST, "Validation Failed", errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.warn("Illegal argument: {}", ex.getMessage());
        return responseBuilder.error(HttpStatus.BAD_REQUEST, "Invalid argument", List.of(ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        log.warn("Username not found: {}", ex.getMessage());
        return responseBuilder.error(HttpStatus.NOT_FOUND, "Username not found", List.of(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex) {
        log.error("Unexpected error", ex);
        return responseBuilder.error(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occurred", List.of(ex.getMessage()));
    }
}
