package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FilmID")
    private Integer filmId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @Column(name = "FilmName")
    private String filmName;

    @Column(name = "ReleaseDate")
    private LocalDate releaseDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    public Film(Category category, String filmName, LocalDate releaseDate, LocalDate endDate) {
        this.category = category;
        this.filmName = filmName;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
    }
}
