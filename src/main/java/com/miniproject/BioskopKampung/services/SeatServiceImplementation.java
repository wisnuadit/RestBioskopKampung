package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.seat.SeatHeaderDTO;
import com.miniproject.BioskopKampung.dtos.seat.SeatInsertDTO;
import com.miniproject.BioskopKampung.dtos.seat.SeatInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Seat;
import com.miniproject.BioskopKampung.models.Studio;
import com.miniproject.BioskopKampung.repositories.SeatRepository;
import com.miniproject.BioskopKampung.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatServiceImplementation implements SeatService{

    private SeatRepository seatRepository;
    private StudioRepository studioRepository;

    @Autowired
    public SeatServiceImplementation(SeatRepository seatRepository, StudioRepository studioRepository) {
        this.seatRepository = seatRepository;
        this.studioRepository = studioRepository;
    }

    public List<SeatHeaderDTO> findAllSeats(){
        return SeatHeaderDTO.toList(seatRepository.findAll());
    }

    public SeatInsertResponseDTO insertNewSeats(SeatInsertDTO seatDTO){
        Studio studio = studioRepository.findById(seatDTO.getStudioId())
                .orElseThrow(() -> new IllegalArgumentException("Studio tidak ditemukan"));

        Seat seat = new Seat();
        List<Seat> oldSeats = seatRepository.findAll();

        List<Seat> seats = new ArrayList<>();
        List<Integer> seatNumbers = seatDTO.getSeatNumbers();
        for (Integer seatNumber : seatNumbers){
            seat = new Seat(studio, seatNumber, seatDTO.getRow());
            seats.add(seat);

            for (Seat oldSeat : oldSeats){
                if (seatNumber == oldSeat.getSeatId() && seat.getRow().equals(oldSeat.getRow())){
                    throw new IllegalArgumentException(String.format("Seat %s%d sudah ada",
                            seat.getRow(), seat.getSeatId()));
                }
            }
        }

        seatRepository.saveAll(seats);

        return SeatInsertResponseDTO.set(seats);
    }
}
