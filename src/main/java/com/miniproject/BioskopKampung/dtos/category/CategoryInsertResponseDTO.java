package com.miniproject.BioskopKampung.dtos.category;

import com.miniproject.BioskopKampung.models.Category;
import lombok.Data;

@Data
public class CategoryInsertResponseDTO {

    private final String categoryName;

    public static CategoryInsertResponseDTO set(Category category){
        return new CategoryInsertResponseDTO(category.getCategoryName());
    }
}
