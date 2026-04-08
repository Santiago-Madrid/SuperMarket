package com.Market.ProductosProveedores.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Market.ProductosProveedores.Dto.ProductRequestDto;
import com.Market.ProductosProveedores.Dto.StockEntryDTO;

import com.Market.ProductosProveedores.Service.StockEntryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
    
    private final StockEntryService stockEntryService;

    @PatchMapping
    public ResponseEntity<ProductRequestDto> EntryStock(@RequestBody @Valid StockEntryDTO stockEntryDTO) {
        ProductRequestDto response = stockEntryService.updateStockEntry(stockEntryDTO);;
        return ResponseEntity.ok(response);
    }

}
