package com.miniproject.BioskopKampung.dtos.seat;

import com.miniproject.BioskopKampung.models.Seat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeatInsertResponseDTO {

    private final List<String> seatNumber;

    public static SeatInsertResponseDTO set(List<Seat> seats){
        List<String> seatRowNumber = new ArrayList<>();
        for (Seat seat : seats){
            seatRowNumber.add(String.format("%s%d", seat.getRow(), seat.getSeatNumber()));
        }

        return new SeatInsertResponseDTO(seatRowNumber);
    }
}
