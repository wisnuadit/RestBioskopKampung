package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.film.FilmHeaderDTO;
import com.miniproject.BioskopKampung.dtos.film.FilmInsertDTO;
import com.miniproject.BioskopKampung.dtos.film.FilmInsertResponseDTO;
import com.miniproject.BioskopKampung.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("film")
public class FilmController {

    private FilmService service;

    @Autowired
    public FilmController(FilmService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<FilmHeaderDTO>>> findAll(
            @RequestParam(defaultValue = "") String filmName
    ){
        int size = service.findAllFilms(filmName).size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllFilms(filmName),
                        "Film berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping("insert-new-film")
    public ResponseEntity<RestResponse<FilmInsertResponseDTO>> insertNewFilm(
            @RequestBody FilmInsertDTO filmDTO
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewFilm(filmDTO),
                        "Film berhasil ditambahkan",
                        "201"),
                HttpStatus.OK
        );
    }
}
