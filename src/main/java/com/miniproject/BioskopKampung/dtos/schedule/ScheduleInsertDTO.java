package com.miniproject.BioskopKampung.dtos.schedule;

import lombok.Data;

@Data
public class ScheduleInsertDTO {

    private final Integer studioId;
    private final Integer filmId;
    private final String time;
    private final String date;
    private final String description;
}
