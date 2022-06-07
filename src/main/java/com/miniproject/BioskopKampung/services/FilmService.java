package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.film.FilmHeaderDTO;
import com.miniproject.BioskopKampung.dtos.film.FilmInsertDTO;
import com.miniproject.BioskopKampung.dtos.film.FilmInsertResponseDTO;

import java.util.List;

public interface FilmService {

    public List<FilmHeaderDTO> findAllFilms();
    public FilmInsertResponseDTO insertNewFilm(FilmInsertDTO filmDTO);
}
