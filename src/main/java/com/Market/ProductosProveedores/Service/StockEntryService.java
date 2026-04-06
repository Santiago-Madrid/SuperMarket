package com.Market.ProductosProveedores.Service;



import org.springframework.stereotype.Service;

import com.Market.ProductosProveedores.Dto.ProductRequestDto;
import com.Market.ProductosProveedores.Dto.StockEntryDTO;
import com.Market.ProductosProveedores.Entity.ProductEntity;
import com.Market.ProductosProveedores.Exceptions.BadRequestException;
import com.Market.ProductosProveedores.Repository.ProductRepository;
import com.Market.ProductosProveedores.Repository.ProductSupplierRepository;
import com.Market.ProductosProveedores.Repository.SupplierRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockEntryService {
    
    private final ProductRepository productRepository ;
    private final SupplierRepository supplierRepository;
    private final ProductSupplierRepository productSupplierRepository;  
    
    @Transactional
    public ProductRequestDto updateStockEntry(StockEntryDTO stockEntryDTO) {
        ProductEntity product = productRepository.findById(stockEntryDTO.getIdProduct()).orElseThrow(() -> new BadRequestException("Error: El producto con ID " + stockEntryDTO.getIdProduct() + " no existe."));

        if (!supplierRepository.existsById(stockEntryDTO.getIdSupplier())) {
            throw new BadRequestException("Error: El proveedor con ID " + stockEntryDTO.getIdSupplier() + " no está registrado.");
        }

        if (!(productSupplierRepository.existsByIdProductIdAndIdSupplierId(stockEntryDTO.getIdProduct(), stockEntryDTO.getIdSupplier()))) {
            throw new BadRequestException("Error: El producto con ID " + stockEntryDTO.getIdProduct() + " no está registrado para el proveedor con ID " + stockEntryDTO.getIdSupplier() + ".");
        }
        product.setStock(product.getStock() + stockEntryDTO.getAmount());
        productRepository.save(product);

        ProductRequestDto productRequesDto = new ProductRequestDto();
        productRequesDto.setIdProduct(product.getIdProduct());
        productRequesDto.setIdProductCategory(product.getCategory().getId());
        productRequesDto.setName(product.getName());
        productRequesDto.setStock(product.getStock());
        productRequesDto.setBarCode(product.getBarCode());
        productRequesDto.setSalePrice(product.getSalePrice());
        productRequesDto.setPurchasePrice(product.getPurchasePrice());
        productRequesDto.setState(product.isState());
        return productRequesDto;
    }   

}
