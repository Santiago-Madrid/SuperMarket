package com.Market.ProductosProveedores.Service;

import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.ProductRequestDto;
import com.Market.ProductosProveedores.Dto.ProductResponseDto;
import com.Market.ProductosProveedores.Entity.ProductEntity;
import com.Market.ProductosProveedores.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
   private final ProductRepository productRepository;
    
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        ProductEntity product = new ProductEntity();
        product.setIdProductCategory(productRequestDto.getIdProductCategory());
        product.setName(productRequestDto.getName());
        product.setStock(productRequestDto.getStock());
        product.setBarCode(productRequestDto.getBarCode());
        product.setSalePrice(productRequestDto.getSalePrice());
        product.setPurchasePrice(productRequestDto.getPurchasePrice());
        product.setState(productRequestDto.isState());
        productRepository.save(product);

        ProductResponseDto response = new ProductResponseDto();
        response.setIdProduct(product.getIdProduct());
        response.setIdProductCategory(product.getIdProductCategory());
        response.setName(product.getName());
        response.setStock(product.getStock());
        response.setBarCode(product.getBarCode());
        response.setSalePrice(product.getSalePrice());
        response.setPurchasePrice(product.getPurchasePrice());
        response.setState(product.isState());

        return response;
    } 
}
