package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketHeaderDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertDTO;
import com.miniproject.BioskopKampung.dtos.bookingTicket.BookingTicketInsertResponseDTO;
import com.miniproject.BioskopKampung.models.*;
import com.miniproject.BioskopKampung.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class BookingTicketServiceImplementation implements BookingTicketService{

    private BookingTicketRepository bookingTicketRepository;
    private ScheduleRepository scheduleRepository;
    private CustomerRepository customerRepository;
    private SeatRepository seatRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingTicketServiceImplementation(BookingTicketRepository bookingTicketRepository,
                                              ScheduleRepository scheduleRepository,
                                              CustomerRepository customerRepository,
                                              SeatRepository seatRepository, UserRepository userRepository) {
        this.bookingTicketRepository = bookingTicketRepository;
        this.scheduleRepository = scheduleRepository;
        this.customerRepository = customerRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }

    public List<BookingTicketHeaderDTO> findAllBookingTickets(String customerName, String filmName,
                                                              String studioNumber, String bookingDate,
                                                              String time){
        List<BookingTicket> bookingTickets = new ArrayList<>();

        Stream<BookingTicket> result = bookingTicketRepository.findAllByKey(customerName, filmName,
                studioNumber, bookingDate, time).stream();
        result.forEach(
                (bookingTicket -> {
                    bookingTickets.add(
                            new BookingTicket(
                                    bookingTicket.getTicketId(),
                                    bookingTicket.getCustomer(),
                                    bookingTicket.getSchedule(),
                                    bookingTicket.getFilmName(),
                                    bookingTicket.getStudioNumber(),
                                    bookingTicket.getSeat(),
                                    bookingTicket.getBookingDate(),
                                    bookingTicket.getTime()
                            )
                    );
                })
        );
        return BookingTicketHeaderDTO.toList(bookingTickets);
    }

    public BookingTicketInsertResponseDTO insertNewBooking(BookingTicketInsertDTO bookingTicketDTO,
                                                           Authentication authentication){
        User user = userRepository.findByUsername(authentication.getName()).get();

        Customer customer = customerRepository.findById(user.getUserId()).get();

        Schedule schedule = scheduleRepository.findById(bookingTicketDTO.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("Schedule tidak ditemukan"));

        if (!schedule.isScheduled()){
            throw new IllegalArgumentException("Schedule sudah tidak aktif");
        }

        Seat seat = seatRepository.findById(bookingTicketDTO.getSeatId())
                .orElseThrow(() -> new IllegalArgumentException("Seat tidak ditemukan"));

        if (seat.isBooked()){
            throw new IllegalArgumentException(String.format("Seat %d sudah dibooking", seat.getSeatId()));
        }

        BookingTicket bookingTicket = new BookingTicket(
                customer, schedule, schedule.getFilm().getFilmName(), schedule.getStudio().getStudioNumber(),
                seat, LocalDate.now(), schedule.getTime());

        seat.setBooked(true);

        bookingTicketRepository.save(bookingTicket);
        seatRepository.save(seat);

        return BookingTicketInsertResponseDTO.set(bookingTicket);
    }
}
