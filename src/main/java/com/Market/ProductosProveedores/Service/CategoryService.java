package com.Market.ProductosProveedores.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.CategoryResponseDto;
import com.Market.ProductosProveedores.Dto.ProductResponseDto;
import com.Market.ProductosProveedores.Entity.CategoryEntity;
import com.Market.ProductosProveedores.Entity.ProductEntity;
import com.Market.ProductosProveedores.Repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponseDto getCategoryWithProducts(String name) {

        CategoryEntity category = categoryRepository.findByNameWithProducts(name)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());

        List<ProductResponseDto> products = new ArrayList<>();

        for (ProductEntity product : category.getProducts()) {
            ProductResponseDto p = new ProductResponseDto();
            p.setName(product.getName());
            p.setStock(product.getStock());
            p.setBarCode(product.getBarCode());
            p.setSalePrice(product.getSalePrice());
            p.setPurchasePrice(product.getPurchasePrice());
            p.setState(product.isState());
            products.add(p);
        }

        dto.setProducts(products);

        return dto;
    }
}