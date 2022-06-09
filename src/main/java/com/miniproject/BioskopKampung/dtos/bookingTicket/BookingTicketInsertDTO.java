package com.miniproject.BioskopKampung.dtos.bookingTicket;

import lombok.Data;

import java.util.List;

@Data
public class BookingTicketInsertDTO {

    private final Integer scheduleId;
    private final Integer seatId;
}
