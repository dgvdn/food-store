package com.example.foodstore.service;

import com.example.foodstore.model.Subcategory;
import com.example.foodstore.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository subcategoryRepository;

    public SubcategoryService(SubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }
    public List<Subcategory> findAll() {
        return subcategoryRepository.findAll();
    }
    public Subcategory findById(Long id) {
        return subcategoryRepository.findById(id).orElse(null);
    }
    public Subcategory save(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }
    public Subcategory update(Subcategory subcategory) {
        Subcategory subcategoryUpdate = null;
        try {
            subcategoryUpdate = findById(subcategory.getId());
            subcategoryUpdate.setName(subcategory.getName());
            subcategoryUpdate.setActive(subcategory.isActive());
            subcategoryUpdate.setDeleted(subcategory.isDeleted());
            subcategoryRepository.save(subcategoryUpdate);
        } catch (Exception e) {
            System.out.println(e);
        }
        return subcategoryUpdate;
    }
    public void deleteById(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id).orElse(null);
        if (subcategory != null) {
            subcategory.setDeleted(true);
            subcategoryRepository.save(subcategory);
        }
    }

    public List<Subcategory> findAllByCategoryId(Long id) {
        return subcategoryRepository.findAllByCategoryId(id);
    }
    public List<Subcategory> findAllByCategoryIdAndIsActive(Long id) {
        return subcategoryRepository.findAllByCategoryIdAndIsActiveTrue(id);
    }
    public void active(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id).orElse(null);
        if (subcategory != null) {
            subcategory.setDeleted(false);
            subcategory.setActive(true);
            subcategoryRepository.save(subcategory);
        }
    }
}
