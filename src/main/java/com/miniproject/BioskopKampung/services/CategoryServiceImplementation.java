package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.category.CategoryHeaderDTO;
import com.miniproject.BioskopKampung.dtos.category.CategoryInsertDTO;
import com.miniproject.BioskopKampung.dtos.category.CategoryInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Category;
import com.miniproject.BioskopKampung.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements  CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryHeaderDTO> findAllCategories(){
        return CategoryHeaderDTO.toList(categoryRepository.findAll());
    }

    public CategoryInsertResponseDTO insertNewCategory(CategoryInsertDTO categoryDTO){
        Category newCategory = new Category(categoryDTO.getCategoryName(), categoryDTO.getDescription());
        categoryRepository.save(newCategory);

        return CategoryInsertResponseDTO.set(newCategory);
    }
}
