package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.schedule.ScheduleDeleteResponseDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleHeaderDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleInsertDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Film;
import com.miniproject.BioskopKampung.models.Schedule;
import com.miniproject.BioskopKampung.models.Studio;
import com.miniproject.BioskopKampung.repositories.FilmRepository;
import com.miniproject.BioskopKampung.repositories.ScheduleRepository;
import com.miniproject.BioskopKampung.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ScheduleServiceImplementation implements ScheduleService {

    private ScheduleRepository scheduleRepository;
    private StudioRepository studioRepository;
    private FilmRepository filmRepository;

    @Autowired
    public ScheduleServiceImplementation(ScheduleRepository scheduleRepository,
                                         StudioRepository studioRepository,
                                         FilmRepository filmRepository) {
        this.scheduleRepository = scheduleRepository;
        this.studioRepository = studioRepository;
        this.filmRepository = filmRepository;
    }

    public List<ScheduleHeaderDTO> findAllSchedules(String name, String time){
        List<Schedule> schedules = new ArrayList<>();

        Stream<Schedule> result = scheduleRepository.findAllByName(name, time).stream();
        result.forEach((schedule -> {
            schedules.add(
                    new Schedule(
                            schedule.getScheduleId(),
                            schedule.getStudio(),
                            schedule.getFilm(),
                            schedule.getTime(),
                            schedule.getDate(),
                            schedule.getDescription(),
                            schedule.isScheduled(),
                            schedule.getBookingTickets()
                    )
            );
        }));

        return ScheduleHeaderDTO.toList(schedules);
    }

    public ScheduleInsertResponseDTO insertNewSchedules(ScheduleInsertDTO scheduleDTO){
        Studio studio = studioRepository.findById(scheduleDTO.getStudioId())
                .orElseThrow(() -> new IllegalArgumentException("Studio tidak ditemukan"));

        Film film = filmRepository.findById(scheduleDTO.getFilmId())
                .orElseThrow(() -> new IllegalArgumentException("Film tidak ditemukan"));

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalTime time = LocalTime.parse(scheduleDTO.getTime());
        LocalDate date = LocalDate.parse(scheduleDTO.getDate(), formatterDate);

        Schedule schedule = new Schedule(studio, film, time, date,
                    scheduleDTO.getDescription());

        scheduleRepository.save(schedule);

        return ScheduleInsertResponseDTO.set(schedule);
    }

    public ScheduleDeleteResponseDTO deleteSchedule(Integer id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule tidak ditemukan"));

        schedule.setScheduled(false);

        scheduleRepository.save(schedule);

        return ScheduleDeleteResponseDTO.set(schedule);
    }
}
