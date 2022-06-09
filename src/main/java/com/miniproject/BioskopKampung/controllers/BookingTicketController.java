package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketHeaderDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertResponseDTO;
import com.miniproject.BioskopKampung.services.BookingTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking-ticket")
public class BookingTicketController {

    private BookingTicketService service;

    @Autowired
    public BookingTicketController(BookingTicketService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<BookingTicketHeaderDTO>>> findAllBookingTickets(
            @RequestParam(defaultValue = "") String seat
    ){
        int size = service.findAllBookingTickets(seat).size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllBookingTickets(seat),
                        "Ticket berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping("booking")
    public ResponseEntity<RestResponse<BookingTicketInsertResponseDTO>> insertNewBookingTicket(
            @RequestBody BookingTicketInsertDTO bookingTicketDTO, Authentication authentication
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewBooking(bookingTicketDTO, authentication),
                        "Booking berhasil ditambahkan: ",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
