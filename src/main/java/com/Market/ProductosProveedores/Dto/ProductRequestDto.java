package com.Market.ProductosProveedores.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequestDto {
    private Long idProduct;
    private Long idProductCategory;
    private String name;
    private Long stock;
    private String barCode;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private boolean state;

}
