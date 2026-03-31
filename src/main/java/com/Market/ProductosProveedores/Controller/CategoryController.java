package com.Market.ProductosProveedores.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Market.ProductosProveedores.Dto.CategoryResponseDto;
import com.Market.ProductosProveedores.Service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getCategoryWithProducts(@PathVariable String name) {
        try {
            CategoryResponseDto response = categoryService.getCategoryWithProducts(name);

            return ResponseEntity.status(HttpStatus.FOUND).body(response);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
    }
}