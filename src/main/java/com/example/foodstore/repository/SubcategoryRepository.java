package com.example.foodstore.repository;

import com.example.foodstore.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    List<Subcategory> findAllByCategoryId(Long id);

    List<Subcategory> findAllByCategoryIdAndIsActive(Long id);
}
