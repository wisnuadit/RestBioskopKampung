package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.category.CategoryHeaderDTO;
import com.miniproject.BioskopKampung.dtos.category.CategoryInsertDTO;
import com.miniproject.BioskopKampung.dtos.category.CategoryInsertResponseDTO;
import com.miniproject.BioskopKampung.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CategoryHeaderDTO>>> findAll(
            @RequestParam(defaultValue = "") String categoryName
    ){
        int size = service.findAllCategories(categoryName).size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllCategories(categoryName),
                        "Category berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping("insert-new-category")
    public ResponseEntity<RestResponse<CategoryInsertResponseDTO>> insertNewCategory(
            @RequestBody CategoryInsertDTO categoryDTO
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewCategory(categoryDTO),
                        "Category berhasil ditambahkan",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
