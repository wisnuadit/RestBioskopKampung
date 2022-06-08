package com.miniproject.BioskopKampung.dtos.studio;

import com.miniproject.BioskopKampung.models.Studio;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudioHeaderDTO {

    private final String studioId;
    private final String studioNumber;
    private final String description;
    private final boolean isEnabled;

    public static StudioHeaderDTO set(Studio studio){
        return new StudioHeaderDTO(studio.getStudioId().toString(), studio.getStudioNumber().toString(),
                studio.getDescription(), studio.isEnabled());
    }

    public static List<StudioHeaderDTO> toList(List<Studio> studios){
        List<StudioHeaderDTO> result = new ArrayList<>();
        for (Studio studio : studios){
            result.add(set(studio));
        }
        return result;
    }
}
