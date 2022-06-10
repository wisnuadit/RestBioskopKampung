package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleDeleteResponseDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleHeaderDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleInsertDTO;
import com.miniproject.BioskopKampung.dtos.schedule.ScheduleInsertResponseDTO;
import com.miniproject.BioskopKampung.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    private ScheduleService service;

    @Autowired
    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<ScheduleHeaderDTO>>> findAllSchedules(
            @RequestParam(defaultValue = "") String filmName,
            @RequestParam(defaultValue = "") String time
    ){
        int size = service.findAllSchedules(filmName, time).size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllSchedules(filmName, time),
                        "Schedule berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping("insert-new-schedule")
    public ResponseEntity<RestResponse<ScheduleInsertResponseDTO>> insertNewSchedules(
            @RequestBody ScheduleInsertDTO scheduleDTO
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewSchedules(scheduleDTO),
                        "Schedule berhasil ditambahkan",
                        "201"),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping
    public ResponseEntity<RestResponse<ScheduleDeleteResponseDTO>> deleteSchedule(Integer id){
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteSchedule(id),
                        "Schedule berhasil dihapus",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
