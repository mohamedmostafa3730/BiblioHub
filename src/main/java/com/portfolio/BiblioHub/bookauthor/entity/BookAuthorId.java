package com.portfolio.BiblioHub.bookauthor.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookAuthorId implements Serializable {
    private Long bookId;
    private Long authorId;
}
