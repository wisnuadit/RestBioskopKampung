package com.miniproject.BioskopKampung.dtos.bookingTicket;

import com.miniproject.BioskopKampung.models.BookingTicket;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookingTicketInsertResponseDTO {

    private final String studioNumber;
    private final String filmName;
    private final String time;
    private final String seat;

    public static BookingTicketInsertResponseDTO set(BookingTicket bookingTicket){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        String time = formatter.format(bookingTicket.getSchedule().getTime());

        return new BookingTicketInsertResponseDTO(bookingTicket.getSchedule().getStudio().getStudioNumber().toString(),
                bookingTicket.getSchedule().getFilm().getFilmName(), time, bookingTicket.getSeat().seatRowNumber());
    }

    public static List<BookingTicketInsertResponseDTO> toList(List<BookingTicket> bookingTickets){
        List<BookingTicketInsertResponseDTO> result = new ArrayList<>();
        for (BookingTicket bookingTicket : bookingTickets){
            result.add(set(bookingTicket));
        }
        return result;
    }
}
