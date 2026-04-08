package com.Market.ProductosProveedores.Dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryRequestDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductResponseDto> products;
}
