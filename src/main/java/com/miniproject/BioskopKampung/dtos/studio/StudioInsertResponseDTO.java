package com.miniproject.BioskopKampung.dtos.studio;

import com.miniproject.BioskopKampung.models.Studio;
import lombok.Data;

@Data
public class StudioInsertResponseDTO {

    private final String studioNumber;

    public static StudioInsertResponseDTO set(Studio studio){
        return new StudioInsertResponseDTO(studio.getStudioNumber().toString());
    }
}
