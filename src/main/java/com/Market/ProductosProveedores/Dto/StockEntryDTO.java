package com.Market.ProductosProveedores.Dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class StockEntryDTO {

    @NotNull(message = "El ID del proveedor es obligatorio")
    private Long idSupplier;
    @NotNull(message = "El ID del producto es obligatorio")
    private Long idProduct;
    
    @NotNull(message = "La cantidad del producto es obligatoria")
    @Min(value = 1, message = "La cantidad no puede ser menor a cero")
    private Long amount;

}
