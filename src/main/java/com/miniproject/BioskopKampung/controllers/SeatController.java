package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.seat.SeatHeaderDTO;
import com.miniproject.BioskopKampung.dtos.seat.SeatInsertDTO;
import com.miniproject.BioskopKampung.dtos.seat.SeatInsertResponseDTO;
import com.miniproject.BioskopKampung.services.SeatService;
import com.miniproject.BioskopKampung.services.SeatServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seats")
public class SeatController {

    private SeatService service;

    @Autowired
    public SeatController(SeatService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<SeatHeaderDTO>>> findAllSeats(){
        int size = service.findAllSeats().size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllSeats(),
                        "Seat berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<RestResponse<SeatInsertResponseDTO>> insertNewSeats(
            @RequestBody SeatInsertDTO seatDTO
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewSeats(seatDTO),
                        "Seat berhasil ditambahkan",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
