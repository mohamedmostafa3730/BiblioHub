package com.portfolio.BiblioHub.common.exception;

/**
 * Exception thrown when a resource cannot be found by its identifier.
 */
public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String entityName, Object id) {
        super(entityName + " with id " + id + " not found.");
    }
}
