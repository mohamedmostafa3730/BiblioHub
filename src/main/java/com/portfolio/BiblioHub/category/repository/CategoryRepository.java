package com.portfolio.BiblioHub.category.repository;

import com.portfolio.BiblioHub.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Prevent duplicate category names
    Optional<Category> findByCategoryName(String categoryName);

}
