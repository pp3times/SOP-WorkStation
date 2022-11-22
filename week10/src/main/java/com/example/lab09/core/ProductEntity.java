package com.example.lab09.core;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class ProductEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -4258156490972698458L;
    @Id
    @Column
    private String productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
