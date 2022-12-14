package com.example.sop_final_63070076.Service;

import com.example.sop_final_63070076.pojo.Product;
import com.example.sop_final_63070076.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public boolean addProduct(Product product) {
        try {
            product.set_id(null);
            repository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        try {
            repository.save(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProduct(Product product) {
        try {
            repository.delete(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> getAllProduct() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Product getProductByName(String name) {
        try {
            return repository.findByName(name);
        } catch (Exception e) {
            return null;
        }
    }
}