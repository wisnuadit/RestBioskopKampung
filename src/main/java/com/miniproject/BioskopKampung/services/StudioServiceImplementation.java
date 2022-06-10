package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.studio.StudioHeaderDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertDTO;
import com.miniproject.BioskopKampung.dtos.studio.StudioInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Studio;
import com.miniproject.BioskopKampung.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class StudioServiceImplementation implements StudioService{

    private StudioRepository studioRepository;

    @Autowired
    public StudioServiceImplementation(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    public List<StudioHeaderDTO> findAllStudios(String studioNumber, String enabled){
        List<Studio> studios = new ArrayList<>();

        Stream<Studio> result = studioRepository.findAllSeatBySeatRowNumber(studioNumber, enabled).stream();
        result.forEach((studio -> {
            studios.add(
                    new Studio(
                            studio.getStudioId(),
                            studio.getStudioNumber(),
                            studio.getDescription(),
                            studio.isEnabled(),
                            studio.getSeats(),
                            studio.getSchedules()
                    )
            );
        }));

        return StudioHeaderDTO.toList(studios);
    }

    public StudioInsertResponseDTO insertNewStudios(StudioInsertDTO studioDTO){
        List<Studio> studios = studioRepository.findAll();
        for (Studio oldStudio : studios){
            if (studioDTO.getStudioNumber() == oldStudio.getStudioNumber()){
                throw new IllegalArgumentException("Studio Number sudah ada");
            }
        }

        Studio studio = new Studio(studioDTO.getStudioNumber(), studioDTO.getDescription());
        studioRepository.save(studio);

        return StudioInsertResponseDTO.set(studio);
    }
}
