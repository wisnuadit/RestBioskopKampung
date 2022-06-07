package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "CategoryName")
    private String categoryName;

    @Column(name = "[Description]")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Film> films;

    public Category(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }
}
