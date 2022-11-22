package com.sop.chapter9.productservice.core.data;

import com.sop.chapter9.productservice.core.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId, String title);
}
