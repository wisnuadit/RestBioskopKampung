package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.schedule.ScheduleDeleteResponseDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleHeaderDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleInsertDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleInsertResponseDTO;

import java.util.List;

public interface ScheduleService {

    public List<ScheduleHeaderDTO> findAllSchedules();
    public List<ScheduleInsertResponseDTO> insertNewSchedules(ScheduleInsertDTO scheduleDTO);
    public ScheduleDeleteResponseDTO deleteSchedule(Integer id);
}
