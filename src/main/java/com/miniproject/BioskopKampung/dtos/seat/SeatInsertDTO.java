package com.miniproject.BioskopKampung.dtos.seat;

import lombok.Data;

import java.util.List;

@Data
public class SeatInsertDTO {

    private final Integer studioId;
    private final List<Integer> seatNumbers;
    private final String row;
}
