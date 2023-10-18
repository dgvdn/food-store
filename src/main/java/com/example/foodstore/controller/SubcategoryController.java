package com.example.foodstore.controller;

import com.example.foodstore.model.Subcategory;
import com.example.foodstore.service.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subcategory")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/all")
    public List<Subcategory> findAll() {
        return subcategoryService.findAll();
    }
    @GetMapping("/all/category")
    public List<Subcategory> findAllByCategoryId(@RequestBody Long id) {
        return subcategoryService.findAllByCategoryId(id);
    }
    @GetMapping("/all/category/active")
    public List<Subcategory> findAllByCategoryIdAndIsActive(@RequestBody Long id) {
        return subcategoryService.findAllByCategoryIdAndIsActive(id);
    }
    @GetMapping("/")
    public Subcategory findById(@RequestBody Long id) {
        return subcategoryService.findById(id);
    }
    @PostMapping("/add")
    public Subcategory save(@RequestBody Subcategory subcategory) {
        return subcategoryService.save(subcategory);
    }
    @PutMapping("/update")
    public Subcategory update(@RequestBody Subcategory subcategory) {
        return subcategoryService.update(subcategory);
    }
    @DeleteMapping("/delete")
    public void deleteById(@RequestBody Long id) {
        subcategoryService.deleteById(id);
    }
    @PutMapping("/active")
    public void active(@RequestBody Long id) {
        subcategoryService.active(id);
    }

}
