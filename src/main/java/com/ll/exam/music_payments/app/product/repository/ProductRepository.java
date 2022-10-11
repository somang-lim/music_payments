package com.ll.exam.music_payments.app.product.repository;

import com.ll.exam.music_payments.app.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
