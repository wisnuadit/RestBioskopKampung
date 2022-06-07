package com.miniproject.BioskopKampung.dtos.film;

import com.miniproject.BioskopKampung.models.Film;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class FilmHeaderDTO {

    private final String filmId;
    private final String filmName;
    private final String categoryName;
    private final String releaseDate;
    private final String endDate;

    public static FilmHeaderDTO set(Film film){
        Locale indo = new Locale("id","ID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", indo);

        return new FilmHeaderDTO(film.getFilmId().toString(), film.getFilmName(),
                film.getCategory().getCategoryName(), formatter.format(film.getReleaseDate()),
                formatter.format(film.getEndDate()));
    }

    public static List<FilmHeaderDTO> toList(List<Film> films){
        List<FilmHeaderDTO> result = new ArrayList<>();
        for (Film film : films){
            result.add(set(film));
        }
        return result;
    }
}
