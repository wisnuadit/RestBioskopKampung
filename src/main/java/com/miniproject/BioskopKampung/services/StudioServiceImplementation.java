package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.studio.StudioHeaderDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Studio;
import com.miniproject.BioskopKampung.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioServiceImplementation implements StudioService{

    private StudioRepository studioRepository;

    @Autowired
    public StudioServiceImplementation(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<StudioHeaderDTO> findAllStudios(){
        return StudioHeaderDTO.toList(studioRepository.findAll());
    }

    public StudioInsertResponseDTO insertNewStudios(StudioInsertDTO studioDTO){
        Studio studio = new Studio(studioDTO.getStudioNumber(), studioDTO.getDescription());
        studioRepository.save(studio);

        return StudioInsertResponseDTO.set(studio);
    }
}
