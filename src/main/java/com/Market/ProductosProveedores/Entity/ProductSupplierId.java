package com.Market.ProductosProveedores.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Embeddable
@NoArgsConstructor
@AllArgsConstructor 
public class ProductSupplierId implements Serializable {

    private Long productId;
    private Long supplierId; 
    
}