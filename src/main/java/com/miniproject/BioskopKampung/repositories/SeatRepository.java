package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
