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

    public List<ScheduleHeaderDTO> findAllSchedules(){
        return ScheduleHeaderDTO.toList(scheduleRepository.findAll());
    }

    public List<ScheduleInsertResponseDTO> insertNewSchedules(ScheduleInsertDTO scheduleDTO){
        Studio studio = studioRepository.findById(scheduleDTO.getStudioId())
                .orElseThrow(() -> new IllegalArgumentException("Studio tidak ditemukan"));

        Film film = filmRepository.findById(scheduleDTO.getFilmId())
                .orElseThrow(() -> new IllegalArgumentException("Film tidak ditemukan"));

        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalTime time = LocalTime.parse(scheduleDTO.getTime());
        LocalDate date = LocalDate.parse(scheduleDTO.getDate(), formatterDate);

        List<Schedule> newSchedules = new ArrayList<>();
        for (int i = 1; i <= 7; i++){
            newSchedules.add(new Schedule(studio, film, time, date.plusDays(i - 1),
                    scheduleDTO.getDescription()));
        }

        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule schedule : schedules){
            if (schedule.getScheduleId() == scheduleDTO.getStudioId() && schedule.getTime() == time){
                throw new IllegalArgumentException("Untuk Studio tersebut tidak boleh memiliki jam yang sama");
            }
        }

        scheduleRepository.saveAll(newSchedules);

        return ScheduleInsertResponseDTO.toList(newSchedules);
    }

    public ScheduleDeleteResponseDTO deleteSchedule(Integer id){
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Schedule tidak ditemukan"));

        schedule.setScheduled(false);

        scheduleRepository.save(schedule);

        return ScheduleDeleteResponseDTO.set(schedule);
    }
}
