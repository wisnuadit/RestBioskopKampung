package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "StudioID")
    private Studio studio;

    @ManyToOne
    @JoinColumn(name = "FilmID")
    private Film film;

    @Column(name = "Time")
    private LocalTime time;

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "[Description]")
    private String description;

    @Column(name = "IsScheduled")
    private boolean isScheduled;

    @JsonIgnore
    @OneToMany(mappedBy = "schedule")
    private List<BookingTicket> bookingTickets;

    public Schedule(Studio studio, Film film, LocalTime time, LocalDate date, String description) {
        this.studio = studio;
        this.film = film;
        this.time = time;
        this.date = date;
        this.description = description;
        this.isScheduled = true;
    }
}
