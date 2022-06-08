package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.seat.SeatHeaderDTO;
import com.miniproject.BioskopKampung.dtos.seat.SeatInsertDTO;
import com.miniproject.BioskopKampung.dtos.seat.SeatInsertResponseDTO;

import java.util.List;

public interface SeatService {

    public List<SeatHeaderDTO> findAllSeats();
    public SeatInsertResponseDTO insertNewSeats(SeatInsertDTO seatDTO);
}
