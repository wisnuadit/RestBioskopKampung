package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SeatID")
    private Integer seatId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "StudioID")
    private Studio studio;

    @Column(name = "SeatNumber")
    private Integer seatNumber;

    @Column(name = "Row")
    private String row;

    @Column(name = "IsBooked")
    private boolean isBooked;

    @Column(name = "IsEnabled")
    private boolean isEnabled;

    @JsonIgnore
    @OneToMany(mappedBy = "seat")
    private List<BookingTicket> bookingTickets;

    public Seat(Studio studio, Integer seatNumber, String row) {
        this.studio = studio;
        this.seatNumber = seatNumber;
        this.row = row;
        this.isEnabled = true;
    }

    public String seatRowNumber(){
        return String.format("%s%d", this.row, this.seatNumber);
    }
}
