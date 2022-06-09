package com.miniproject.BioskopKampung.dtos.bookingTicket;

import com.miniproject.BioskopKampung.models.BookingTicket;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class BookingTicketHeaderDTO {

    private final String ticketId;
    private final String customerName;
    private final String filmName;
    private final String studioNumber;
    private final String seat;
    private final String bookingDate;
    private final String time;

    public static BookingTicketHeaderDTO set(BookingTicket bookingTicket){
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String time = formatterTime.format(bookingTicket.getTime());
        String date = formatterDate.format(bookingTicket.getBookingDate());

        return new BookingTicketHeaderDTO(
                bookingTicket.getTicketId().toString(), bookingTicket.getCustomer().fetchFullname(),
                bookingTicket.getFilmName(), bookingTicket.getStudioNumber().toString(),
                bookingTicket.getSeat().seatRowNumber(), date, time
        );
    }

    public static List<BookingTicketHeaderDTO> toList(List<BookingTicket> bookingTickets){
        List<BookingTicketHeaderDTO> result = new ArrayList<>();
        for (BookingTicket bookingTicket : bookingTickets){
            result.add(set(bookingTicket));
        }
        return result;
    }
}
