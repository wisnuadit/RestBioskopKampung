package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.studio.StudioHeaderDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertResponseDTO;

import java.util.List;

public interface StudioService {

    public List<StudioHeaderDTO> findAllStudios();
    public StudioInsertResponseDTO insertNewStudios(StudioInsertDTO studioDTO);
}
