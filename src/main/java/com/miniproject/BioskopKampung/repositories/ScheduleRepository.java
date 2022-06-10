package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = """
            SELECT *
            FROM Schedules sch
                JOIN Films fi ON fi.FilmID = sch.FilmID
            WHERE fi.FilmName LIKE %:name%
                AND sch.[Time] LIKE %:time%
            """, nativeQuery = true)
    List<Schedule> findAllByName(@Param("name") String name,
                                 @Param("time") String time);
}
