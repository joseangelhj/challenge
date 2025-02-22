package com.jahj.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jahj.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
