package com.Market.ProductosProveedores.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.CategoryRequestDto;
import com.Market.ProductosProveedores.Dto.CategoryResponseDto;
import com.Market.ProductosProveedores.Dto.ProductResponseDto;
import com.Market.ProductosProveedores.Entity.CategoryEntity;
import com.Market.ProductosProveedores.Entity.ProductEntity;
import com.Market.ProductosProveedores.Repository.CategoryRepository;
import com.Market.ProductosProveedores.Repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryResponseDto getCategoryWithProducts(String name) {

        CategoryEntity category = categoryRepository.findByNameWithProducts(name)
        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

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

    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto){
       CategoryEntity category = new CategoryEntity();

       category.setName(categoryRequestDto.getName());
       category.setDescription(categoryRequestDto.getDescription());
       categoryRepository.save(category);
       
       CategoryResponseDto response = new CategoryResponseDto();
        response.setId(category.getId());
        response.setName(category.getName()); 
        response.setDescription(category.getDescription());
        return response;

    }

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        CategoryEntity category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    
        if (categoryRequestDto.getName() != null) {
            category.setName(categoryRequestDto.getName());
        }
    
        if (categoryRequestDto.getDescription() != null) {
            category.setDescription(categoryRequestDto.getDescription());
        }
    
        categoryRepository.save(category);
    
        CategoryResponseDto response = new CategoryResponseDto(); 
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());
    
        return response;
    }

    @Transactional
    public void deleteCategory(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    
        category.setState(false);
        categoryRepository.save(category);
    
        productRepository.deactivateByCategoryId(id);
        System.out.println("Categoría desactivada con ID: " + id + " y sus productos");
    }

    @Transactional
    public void restoreCategory(Long id) {
        CategoryEntity category = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + id));
    
        if (category.isState()) {
            throw new RuntimeException("La categoría ya está activa");
        }
        category.setState(true);
        categoryRepository.save(category);
    
        productRepository.activateByCategoryId(id);
        System.out.println("Categoría restaurada con ID: " + id + " y sus productos");
    }
}
