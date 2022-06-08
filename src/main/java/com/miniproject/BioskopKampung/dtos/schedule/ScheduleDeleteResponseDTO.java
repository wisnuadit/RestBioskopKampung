package com.miniproject.BioskopKampung.dtos.schedule;

import com.miniproject.BioskopKampung.models.Schedule;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class ScheduleDeleteResponseDTO {

    private final String studioNumber;
    private final String filmName;
    private final String time;
    private final String date;

    public static ScheduleDeleteResponseDTO set(Schedule schedule){
        Locale indo = new Locale("id","ID");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", indo);
        String time = schedule.getTime().toString();
        String date = schedule.getDate().toString();

        return new ScheduleDeleteResponseDTO(schedule.getStudio().getStudioNumber().toString(),
                schedule.getFilm().getFilmName(), time, date);
    }
}
