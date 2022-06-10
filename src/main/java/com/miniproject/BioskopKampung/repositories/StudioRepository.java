package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Integer> {

    @Query(value = """
            SELECT * 
            FROM Studios stu
            WHERE stu.StudioNumber LIKE %:studio%
                AND stu.IsEnabled LIKE %:enabled%
            """, nativeQuery = true)
    List<Studio> findAllSeatBySeatRowNumber(@Param("studio") String studio,
                                          @Param("enabled") String seat);
}
