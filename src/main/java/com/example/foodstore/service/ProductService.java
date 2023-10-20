package com.example.foodstore.service;

import com.example.foodstore.model.Product;
import com.example.foodstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory_Id(Long id) {
        return productRepository.findAllByCategory_Id(id);
    }
    public Product save(Product product) {
        return productRepository.save(product);
    }
    public Product update(Product product) {
        Product product1 = productRepository.findById(product.getId()).get();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(product.getCategory());
        product1.setSubCategory(product.getSubCategory());
        product1.setDescription(product.getDescription());
        product1.setImg(product.getImg());
        return productRepository.save(product1);
    }
    public void deleteById(Long id) {
        Product product = productRepository.findById(id).get();
        product.setDeleted(true);
        product.setAvailable(false);
        productRepository.save(product);
    }
    public void enableById(Long id) {
        Product product = productRepository.findById(id).get();
        product.setDeleted(false);
        product.setAvailable(true);
        productRepository.save(product);
    }
    public Page<Product> pageProduct(int page) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, 10);
        return productRepository.findAll(pageable);
    }
    public Page<Product> findAllByCategory_Id(Long id, int page) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, 10);
        return productRepository.findAllByCategory_Id(id, pageable);
    }
    public Page<Product> findAllByNameContaining(String name, int page) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, 10);
        return productRepository.findAllByNameContaining(name, pageable);
    }
    public Page<Product> findAllByCategory_IdAndNameContaining(Long id, String name, int page) {
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, 10);
        return productRepository.findAllByCategory_IdAndNameContaining(id, name, pageable);
    }
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }
    public List<Product> findAllByCategory_IdAndNameContaining(Long id, String name) {
        return productRepository.findAllByCategory_IdAndNameContaining(id, name);
    }
    public List<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    public Page<Product> findAllSortedByPrice(int page, String sort) {
        Sort.Direction direction = "asc".equals(sort) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, 10, Sort.by(direction, "price"));
        return productRepository.findAll(pageable);
    }

}
