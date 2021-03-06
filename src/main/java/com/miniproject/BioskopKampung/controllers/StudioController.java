package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.studio.StudioHeaderDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertResponseDTO;
import com.miniproject.BioskopKampung.services.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("studio")
public class StudioController {

    private StudioService service;

    @Autowired
    public StudioController(StudioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<StudioHeaderDTO>>> findAllStudio(
            @RequestParam(defaultValue = "") String studioNumber,
            @RequestParam(defaultValue = "") String enabled
    ){
        int size = service.findAllStudios(studioNumber, enabled).size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllStudios(studioNumber, enabled),
                        "Studio berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping("insert-new-studio")
    public ResponseEntity<RestResponse<StudioInsertResponseDTO>> insertNewStudio(
            @RequestBody StudioInsertDTO studioDTO
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewStudios(studioDTO),
                        "Studio berhasil ditambahkan",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
