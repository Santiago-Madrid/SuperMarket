package com.Market.ProductosProveedores.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Market.ProductosProveedores.Dto.ProductRequestDto;
import com.Market.ProductosProveedores.Dto.ProductResponseDto;
import com.Market.ProductosProveedores.Exceptions.BadRequestException;
import com.Market.ProductosProveedores.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
        @RequestBody ProductRequestDto productRequestDto) {

        ProductResponseDto response = productService.createProduct(productRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<Optional<ProductResponseDto>> getProduct(@PathVariable(name="idProduct") Long idProduct) {
        try{
            Optional<ProductResponseDto> response = productService.getProduct(idProduct); 
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>>getProducts() {
        try{
            List<ProductResponseDto> response = productService.getProducts(); 
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name="idProduct") Long idProduct) {
        try {
            productService.deleteProduct(idProduct);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();// Retorna 204 NO_CONTENT
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)// Si el producto no existe o ya está eliminado
                .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    @PutMapping("/{idProduct}/restore")
    public ResponseEntity<?> restoreProduct(@PathVariable(name="idProduct") Long idProduct) {
        try {
            productService.restoreProduct(idProduct);
            return ResponseEntity.status(HttpStatus.OK).body("Producto restaurado exitosamente");
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error al restaurar el producto: " + e.getMessage());
        }
    }  
}