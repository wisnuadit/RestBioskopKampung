package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {
}
