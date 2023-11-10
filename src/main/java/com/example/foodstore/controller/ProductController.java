package com.example.foodstore.controller;

import com.example.foodstore.dto.CategoryDto;
import com.example.foodstore.model.Product;
import com.example.foodstore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @GetMapping("/page")
    public Page<Product> pageProduct(@RequestParam int page) {
        return productService.pageProduct(page);
    }
    @GetMapping("/category")
    public Page<Product> findAllByCategory_Id(@RequestParam Long id, @RequestParam int page) {
        return productService.findAllByCategory_Id(id, page);
    }
    @GetMapping("/search")
    public Page<Product> findAllByNameContaining(@RequestParam String name, @RequestParam int page) {
        return productService.findAllByNameContaining(name, page);
    }
    @GetMapping("/category/search")
    public Page<Product> findAllByCategory_IdAndNameContaining(@RequestParam Long id, @RequestParam String name, @RequestParam int page) {
        return productService.findAllByCategory_IdAndNameContaining(id, name, page);
    }
    @PostMapping("/create")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }
    @PostMapping("/update")
    public Product update(@RequestBody Product product) {
        return productService.update(product);
    }
    @PostMapping("/delete")
    public void deleteById(@RequestParam Long id) {
        productService.deleteById(id);
    }
    @PostMapping("/enable")
    public void enableById(@RequestParam Long id) {
        productService.enableById(id);
    }
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }
    @GetMapping("/sorted-by-price")
    public Page<Product> findAllSortedByPrice(@RequestParam int page, @RequestParam String sort) {
        return productService.findAllSortedByPrice(page, sort);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = productService.getCategoriesWithProducts();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    // get products by category name
    @GetMapping("/category/{name}")
    public List<Product> findAllByCategory_Name(@PathVariable String name) {
        return productService.getProductsByCategoryName(name);
    }

    // sort products by price in a category or subcategory
    @GetMapping("/category/{name}/sorted-by-price")
    public List<Product> findAllByCategory_NameSortedByPrice(@PathVariable String name, @RequestParam String sort) {
        if (name.equals("all"))
            return productService.getProductsSortedByPrice(sort);

        return productService.getProductsByCategoryNameSortedByPrice(name, sort);
    }
}
