package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.film.FilmHeaderDTO;
import com.miniproject.BioskopKampung.dtos.film.FilmInsertDTO;
import com.miniproject.BioskopKampung.dtos.film.FilmInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Category;
import com.miniproject.BioskopKampung.models.Film;
import com.miniproject.BioskopKampung.repositories.CategoryRepository;
import com.miniproject.BioskopKampung.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FilmSeviceImplementation implements FilmService{

    private FilmRepository filmRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public FilmSeviceImplementation(FilmRepository filmRepository,
                                    CategoryRepository categoryRepository) {
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<FilmHeaderDTO> findAllFilms(){
        return FilmHeaderDTO.toList(filmRepository.findAll());
    }

    public FilmInsertResponseDTO insertNewFilm(FilmInsertDTO filmDTO){
        Category category = categoryRepository.findById(filmDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category tidak ditemukan"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate releaseDate = LocalDate.parse(filmDTO.getReleaseDate(), formatter);
        LocalDate endDate = releaseDate.plusMonths(1);

        Film film = new Film(category, filmDTO.getFilmName(), releaseDate, endDate);
        filmRepository.save(film);

        return FilmInsertResponseDTO.set(film);
    }
}
