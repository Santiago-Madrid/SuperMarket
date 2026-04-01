package com.Market.ProductosProveedores.Entity;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "products")
public class ProductEntity {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdProduct;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "name")
    private String Name;

    @Column(name = "stock")
    private Long Stock;

    @Column(name = "barcode")
    private String BarCode;

    @Column(name = "sale_price")
    private BigDecimal SalePrice;

    @Column(name = "purchase_price")
    private BigDecimal PurchasePrice;   

    @Column(name = "active")
    private boolean State;

}