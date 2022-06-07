package com.miniproject.BioskopKampung.dtos.film;

import com.miniproject.BioskopKampung.models.Film;
import lombok.Data;

@Data
public class FilmInsertDTO {

    private final Integer categoryId;
    private final String filmName;
    private final String releaseDate;
}
