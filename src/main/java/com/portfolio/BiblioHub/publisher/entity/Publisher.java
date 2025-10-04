package com.portfolio.BiblioHub.publisher.entity;

import com.portfolio.BiblioHub.book.entity.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherId;

    @Column(name = "publisher_name", nullable = false, unique = true, length = 50)
    private String publisherName;

    @Column(name = "publisher_address", length = 200)
    private String publisherAddress;

    @Column(name = "publisher_contact_info", length = 200)
    private String publisherContactInfo;
    @OneToMany(mappedBy = "publisher")

    private List<Book> books;
}
