package com.portfolio.BiblioHub.visitor.repository;

import com.portfolio.BiblioHub.visitor.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findByVisitorEmail(String visitorEmail);
}
