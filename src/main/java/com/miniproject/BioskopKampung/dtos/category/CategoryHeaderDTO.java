package com.miniproject.BioskopKampung.dtos.category;

import com.miniproject.BioskopKampung.models.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryHeaderDTO {
    
    private final Integer categoryId;
    private final String categoryName;
    private final String description;

    public static CategoryHeaderDTO set(Category category){
        return new CategoryHeaderDTO(category.getCategoryId(), category.getCategoryName(),
                category.getDescription());
    }

    public static List<CategoryHeaderDTO> toList(List<Category> categories){
        List<CategoryHeaderDTO> result = new ArrayList<>();
        for (Category category : categories){
            result.add(set(category));
        }
        return result;
    }
}
