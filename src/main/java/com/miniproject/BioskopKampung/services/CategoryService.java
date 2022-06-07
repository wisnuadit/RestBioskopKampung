package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.category.CategoryHeaderDTO;
import com.miniproject.BioskopKampung.dtos.category.CategoryInsertDTO;
import com.miniproject.BioskopKampung.dtos.category.CategoryInsertResponseDTO;

import java.util.List;

public interface CategoryService {

    public List<CategoryHeaderDTO> findAllCategories();
    public CategoryInsertResponseDTO insertNewCategory(CategoryInsertDTO categoryDTO);
}
