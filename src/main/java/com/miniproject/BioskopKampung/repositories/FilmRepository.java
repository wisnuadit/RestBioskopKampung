package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = """
            SELECT *
            FROM Films fi
            WHERE fi.FilmName LIKE %:name%
            """, nativeQuery = true)
    List<Film> findAllByName(@Param("name") String name);
}
