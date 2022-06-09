package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.BookingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface BookingTicketRepository extends JpaRepository<BookingTicket, Integer> {

    @Query(value = """
            SELECT *
            FROM BookingTickets bt
            WHERE bt.SeatID LIKE %:seat%
            """, nativeQuery = true)
    List<BookingTicket> findAllByKey(@Param("seat") String seat);
}
