package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketHeaderDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertResponseDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BookingTicketService {

    public List<BookingTicketHeaderDTO> findAllBookingTickets(String seat);
    public BookingTicketInsertResponseDTO insertNewBooking(BookingTicketInsertDTO bookingTicketDTO,
                                                                 Authentication authentication);
}
