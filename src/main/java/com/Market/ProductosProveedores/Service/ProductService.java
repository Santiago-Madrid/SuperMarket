package com.Market.ProductosProveedores.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<ProductResponseDto> getProduct(Long id) {
        System.out.println("----------------------------ID recibido: " + id);
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductEntity  product = optionalProduct.get();
            ProductResponseDto response = new ProductResponseDto();
            response.setIdProduct(product.getIdProduct());
            response.setIdProductCategory(product.getIdProductCategory());
            response.setName(product.getName());
            response.setStock(product.getStock());
            response.setBarCode(product.getBarCode());
            response.setSalePrice(product.getSalePrice());
            response.setPurchasePrice(product.getPurchasePrice());
            response.setState(product.isState());
    
            return Optional.of(response);
        } else {
            return Optional.empty();
        }
    }

    public List<ProductResponseDto> getProducts() {
        List<ProductEntity> Products = productRepository.findAll();
        List<ProductResponseDto> listProduct = new ArrayList<>();
         for( ProductEntity product: Products){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setIdProduct(product.getIdProduct());
            productResponseDto.setIdProductCategory(product.getIdProductCategory());
            productResponseDto.setName(product.getName());
            productResponseDto.setStock(product.getStock());
            productResponseDto.setBarCode(product.getBarCode());
            productResponseDto.setSalePrice(product.getSalePrice());
            productResponseDto.setPurchasePrice(product.getPurchasePrice());
            productResponseDto.setState(product.isState());
            listProduct.add(productResponseDto);
        } 
        return listProduct;
    }

    
}
