package com.example.foodstore.controller;

import com.example.foodstore.dto.CategoryDto;
import com.example.foodstore.model.Category;
import com.example.foodstore.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(categoryService.findAll());
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveCategory(CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCategory(Category categoryDto){
        return ResponseEntity.ok(categoryService.update(categoryDto));
    }
    @PostMapping("/delete")
    public ResponseEntity<?> deleteCategory(Long id){
        categoryService.delete(id);
        return ResponseEntity.ok("Delete Success");
    }
    @PostMapping("/active")
    public ResponseEntity<?> activeCategory(Long id){
        categoryService.active(id);
        return ResponseEntity.ok("Active Success");
    }
    @GetMapping("/all-active")
    public ResponseEntity<?> getAllCategoryActive(){
        return ResponseEntity.ok(categoryService.findAllByIsActiveTrueAndIsDeletedFalse());
    }
}
