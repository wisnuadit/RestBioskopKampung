package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketHeaderDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookingTicketService {

    public List<BookingTicketHeaderDTO> findAllBookingTickets(String customerName, String filmName,
                                                              String studioNumber, String bookingDate,
                                                              String time);
    public BookingTicketInsertResponseDTO insertNewBooking(BookingTicketInsertDTO bookingTicketDTO,
                                                                 Authentication authentication);
}
