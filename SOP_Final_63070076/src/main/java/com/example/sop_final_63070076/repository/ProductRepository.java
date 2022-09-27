package com.example.sop_final_63070076.repository;

import com.example.sop_final_63070076.pojo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ productName: '?0' }")
    Product findByName(String productName);
}