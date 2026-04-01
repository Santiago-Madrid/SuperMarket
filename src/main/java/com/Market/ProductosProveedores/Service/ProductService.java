package com.Market.ProductosProveedores.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.ProductRequestDto;
import com.Market.ProductosProveedores.Dto.ProductResponseDto;
import com.Market.ProductosProveedores.Entity.CategoryEntity;
import com.Market.ProductosProveedores.Entity.ProductEntity;
import com.Market.ProductosProveedores.Exceptions.BadRequestException;
import com.Market.ProductosProveedores.Repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto dto) {

    if (dto.getName() == null ||
        dto.getIdProductCategory() == null ||
        dto.getStock() == null ||
        dto.getBarCode() == null ||
        dto.getSalePrice() == null ||
        dto.getPurchasePrice() == null) {

        throw new BadRequestException("Todos los campos son obligatorios");
    }

    ProductEntity product = new ProductEntity();

    // 🔥 SOLO CREAS UNA REFERENCIA (NO CONSULTA)
    CategoryEntity category = new CategoryEntity(); 
    category.setId(dto.getIdProductCategory());

    product.setCategory(category);
    product.setName(dto.getName());
    product.setStock(dto.getStock());
    product.setBarCode(dto.getBarCode());
    product.setSalePrice(dto.getSalePrice());
    product.setPurchasePrice(dto.getPurchasePrice());
    product.setState(dto.isState());

    productRepository.save(product);

    ProductResponseDto response = new ProductResponseDto();
    response.setIdProduct(product.getIdProduct());
    response.setIdProductCategory(product.getCategory().getId());
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
            ProductEntity product = optionalProduct.get();
            ProductResponseDto response = new ProductResponseDto();
            response.setIdProduct(product.getIdProduct());
            response.setIdProductCategory(product.getCategory().getId());
            response.setCategoryName(product.getCategory().getName());
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
        for (ProductEntity product : Products) {
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setIdProduct(product.getIdProduct());
            productResponseDto.setIdProductCategory(product.getCategory().getId());
            productResponseDto.setCategoryName(product.getCategory().getName());
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
