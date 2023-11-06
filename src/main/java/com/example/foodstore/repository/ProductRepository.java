package com.example.foodstore.repository;

import com.example.foodstore.model.Category;
import com.example.foodstore.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /* admin */
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByCategory_Id(Long id, Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Page<Product> findAllByCategory_IdAndNameContaining(Long id, String name, Pageable pageable);
    /* user */
    List<Product> findAllByCategory_Id(Long id);
    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByCategory_IdAndNameContaining(Long id, String name);

    List<Product> findTop6ByCategoryOrderByIdDesc(Category category);
}
