package com.miniproject.BioskopKampung.dtos.film;

import com.miniproject.BioskopKampung.models.Film;
import lombok.Data;

@Data
public class FilmInsertResponseDTO {

    private final String filmName;
    private final String categoryName;

    public static FilmInsertResponseDTO set(Film film){
        return new FilmInsertResponseDTO(film.getFilmName(), film.getCategory().getCategoryName());
    }
}
