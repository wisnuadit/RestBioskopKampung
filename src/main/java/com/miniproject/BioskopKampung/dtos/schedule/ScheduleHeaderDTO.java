package com.miniproject.BioskopKampung.dtos.schedule;

import com.miniproject.BioskopKampung.models.Schedule;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class ScheduleHeaderDTO {

    private final String scheduleId;
    private final String studioNumber;
    private final String filmName;
    private final String time;
    private final String date;
    private final String description;
    private final boolean isScheduled;

    public static ScheduleHeaderDTO set(Schedule schedule){
        Locale indo = new Locale("id","ID");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMMM yyyy", indo);
        String time = schedule.getTime().toString();
        String date = schedule.getDate().toString();

        return new ScheduleHeaderDTO(schedule.getScheduleId().toString(),
                schedule.getStudio().getStudioNumber().toString(), schedule.getFilm().getFilmName(),
                time, date, schedule.getDescription(), schedule.isScheduled());

    }

    public static List<ScheduleHeaderDTO> toList(List<Schedule> schedules){
        List<ScheduleHeaderDTO> result = new ArrayList<>();
        for (Schedule schedule : schedules){
            result.add(set(schedule));
        }
        return result;
    }
}
