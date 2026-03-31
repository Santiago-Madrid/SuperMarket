package com.Market.ProductosProveedores.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long IdProduct;

    private Long IdProductCategory;

    private String Name;

    private Long Stock;

    private String BarCode;

    private BigDecimal SalePrice;

    private BigDecimal PurchasePrice;   

    private boolean State;
}
