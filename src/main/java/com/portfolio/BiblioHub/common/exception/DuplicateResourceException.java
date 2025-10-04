package com.portfolio.BiblioHub.common.exception;

/**
 * Exception thrown when trying to create a resource that already exists.
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String entityName, String field, Object value) {
        super(entityName + " with " + field + " = '" + value + "' already exists.");
    }
}
