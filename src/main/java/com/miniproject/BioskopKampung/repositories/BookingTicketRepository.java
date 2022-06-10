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
                JOIN Customers cus ON cus.CustomerID = bt.CustomerID
                JOIN Schedules sch ON sch.ID = bt.ScheduleID
                    JOIN Films fi ON fi.FilmID = sch.FilmID
                    JOIN Studios stu ON stu.StudioID = sch.StudioID
                JOIN Seats s ON s.SeatID = bt.SeatID
            WHERE CONCAT(cus.FirstName, ' ', cus.LastName) LIKE %:customerName%
                AND fi.FilmName LIKE %:filmName%
                AND stu.StudioNumber LIKE %:studioNumber%
                AND bt.BookingDate LIKE %:bookingDate%
                AND bt.[Time] LIKE %:time%
            """, nativeQuery = true)
    List<BookingTicket> findAllByKey(@Param("customerName") String customerName,
                                     @Param("filmName") String filmName,
                                     @Param("studioNumber") String studioNumber,
                                     @Param("bookingDate") String bookingDate,
                                     @Param("time") String time);
}
