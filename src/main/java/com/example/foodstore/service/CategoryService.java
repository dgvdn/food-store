package com.example.foodstore.service;

import com.example.foodstore.dto.CategoryDto;
import com.example.foodstore.model.Category;
import com.example.foodstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    public Category save(CategoryDto category){
        Category category1 = new Category(category.getName());
        return categoryRepository.save(category1);
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public Category update(Category category){
        Category categoryUpdate = null;
        try{
            categoryUpdate = findById(category.getId());
            categoryUpdate.setName(category.getName());
            categoryUpdate.setActive(category.isActive());
            categoryUpdate.setDeleted(category.isDeleted());
            categoryRepository.save(categoryUpdate);
    }   catch (Exception e){
            System.out.println(e);
        }
        return categoryUpdate;
    }
    public void delete(Long id){
        Category category = findById(id);
        category.setDeleted(true);
        category.setActive(false);
        categoryRepository.save(category);
    }
    public void active(Long id){
        Category category = findById(id);
        category.setDeleted(false);
        category.setActive(true);
        categoryRepository.save(category);
    }
    public List<Category> findAllByIsActiveTrueAndIsDeletedFalse(){
        return categoryRepository.findAllByIsActiveTrueAndIsDeletedFalse();
    }
}
