package com.projectjavaweb.course.repository;

import com.projectjavaweb.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
