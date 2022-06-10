package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = """
            SELECT *
            FROM Categories cat
            WHERE cat.CategoryName LIKE %:category%
            """, nativeQuery = true)
    List<Category> findAllByCategoryName(@Param("category") String category);
}
