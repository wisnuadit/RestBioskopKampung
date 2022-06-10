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
                JOIN Studios stu ON stu.StudioID = s.StudioID
            WHERE stu.StudioNumber LIKE %:studio%
                AND s.Row LIKE %:seat%
            """, nativeQuery = true)
    List<Seat> findAllSeatBySeatRowNumber(@Param("studio") String studio,
                                          @Param("seat") String seat);
}
