package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer
        > {
}
