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
import java.util.stream.Stream;

@Service
public class SeatServiceImplementation implements SeatService{

    private SeatRepository seatRepository;
    private StudioRepository studioRepository;

    @Autowired
    public SeatServiceImplementation(SeatRepository seatRepository, StudioRepository studioRepository) {
        this.seatRepository = seatRepository;
        this.studioRepository = studioRepository;
    }

    public List<SeatHeaderDTO> findAllSeats(String studio, String seatRow){
        List<Seat> seats = new ArrayList<>();

        Stream<Seat> result = seatRepository.findAllSeatBySeatRowNumber(studio, seatRow).stream();
        result.forEach((seat -> {
            seats.add(
                    new Seat(
                            seat.getSeatId(),
                            seat.getStudio(),
                            seat.getSeatNumber(),
                            seat.getRow(),
                            seat.isBooked(),
                            seat.isEnabled(),
                            seat.getBookingTickets()
                    )
            );
        }));
        return SeatHeaderDTO.toList(seats);
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
                if (seatNumber == oldSeat.getSeatId() && seat.getRow().equals(oldSeat.getRow())
                        && seat.getSeatId() == oldSeat.getSeatId()){
                    throw new IllegalArgumentException(String.format("Seat %s%d sudah ada",
                            seat.getRow(), seat.getSeatNumber()));
                }
            }
        }

        seatRepository.saveAll(seats);

        return SeatInsertResponseDTO.set(seats);
    }
}
