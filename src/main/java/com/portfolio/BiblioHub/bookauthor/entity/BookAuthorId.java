package com.portfolio.BiblioHub.bookauthor.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class BookAuthorId implements Serializable {
    private Long bookId;
    private Long authorId;
}
