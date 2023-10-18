package com.example.foodstore.controller;

import com.example.foodstore.model.Subcategory;
import com.example.foodstore.service.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/subcategory")
public class SubcategoryController {
    @Autowired
    private SubcategoryService subcategoryService;

    @GetMapping("/all")
    public List<Subcategory> findAll() {
        return subcategoryService.findAll();
    }
    @GetMapping("/category")
    public ResponseEntity<?> findAllByCategoryId(@RequestParam Long id) {
        List<Subcategory> subcategories = subcategoryService.findAllByCategoryId(id);
        return ResponseEntity.ok(subcategories);
    }
    @GetMapping("/category/active")
    public ResponseEntity<?> findAllByCategoryIdAndActive(@RequestParam Long id) {
        List<Subcategory> activeSubcategories = subcategoryService.findAllByCategoryIdAndIsActive(id);
        return ResponseEntity.ok(activeSubcategories);
    }
    @GetMapping("/{id}")
    public Subcategory findById(@PathVariable Long id) {
        return subcategoryService.findById(id);
    }
    @PostMapping("/add")
    public Subcategory save(@RequestBody Subcategory subcategory) {
        return subcategoryService.save(subcategory);
    }
    @PostMapping("/update")
    public Subcategory update( @RequestBody Subcategory subcategory) {
        return subcategoryService.update(subcategory);
    }

    @PostMapping("/delete")
    public void deleteById(@RequestBody Long id) {
        subcategoryService.deleteById(id);
    }
    @PostMapping("/active")
    public void active(@RequestBody Long id) {
        subcategoryService.active(id);
    }

}
