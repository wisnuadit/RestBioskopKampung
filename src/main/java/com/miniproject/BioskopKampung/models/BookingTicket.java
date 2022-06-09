package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BookingTickets")
public class BookingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ticketId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ScheduleID")
    private Schedule schedule;

    @Column(name = "FilmName")
    private String filmName;

    @Column(name = "StudioNumber")
    private Integer studioNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "SeatID")
    private Seat seat;

    @Column(name = "BookingDate")
    private LocalDate bookingDate;

    @Column(name = "[Time]")
    private LocalTime time;

    public BookingTicket(Customer customer, Schedule schedule, String filmName, Integer studioNumber,
                         Seat seat, LocalDate bookingDate, LocalTime time) {
        this.customer = customer;
        this.schedule = schedule;
        this.filmName = filmName;
        this.studioNumber = studioNumber;
        this.seat = seat;
        this.bookingDate = bookingDate;
        this.time = time;
    }
}
