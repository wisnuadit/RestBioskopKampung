package com.miniproject.BioskopKampung.dtos.seat;

import com.miniproject.BioskopKampung.models.Seat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeatHeaderDTO {

    private final String seatId;
    private final String seatRowNumber;
    private final boolean isBooked;
    private final boolean isEnabled;

    public static SeatHeaderDTO set(Seat seat){
        String seatRowNumber = String.format("%s%d", seat.getRow(), seat.getSeatNumber());
        return new SeatHeaderDTO(seat.getSeatId().toString(), seatRowNumber,
                seat.isBooked(), seat.isEnabled());
    }

    public static List<SeatHeaderDTO> toList(List<Seat> seats){
        List<SeatHeaderDTO> result = new ArrayList<>();
        for (Seat seat : seats){
            result.add(set(seat));
        }
        return result;
    }
}
