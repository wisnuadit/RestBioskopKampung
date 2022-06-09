package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query(value = """
            SELECT * 
            FROM Seats s
            WHERE CONCAT(s.Row, s.SeatNumber) = :seatRowNumber
            """, nativeQuery = true)
    Seat findSeatBySeatRowNumber(@Param("seatRowNumber") String seatRowNumber);
}
